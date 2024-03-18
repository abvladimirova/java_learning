package org.example.cache;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class CachingHandler<T> implements InvocationHandler {

    static class CacheKey {
        Object[] arguments;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return Arrays.equals(arguments, cacheKey.arguments);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arguments);
        }
    }

    private final ConcurrentHashMap<Method, ConcurrentHashMap<CacheKey, Object>>
        store = new ConcurrentHashMap<>();


    private T object;
    private Object cacheValue;
    private boolean caching;

    public CachingHandler(T object) {
        this.object = object;
        this.caching = false;
    }

    public static class CacheFactory {
        public static <T> T makeCachable(Object object){
            ClassLoader instanceClassLoader = object.getClass().getClassLoader();
            Class[] interfaces = object.getClass().getInterfaces();
            Object proxyObject = Proxy.newProxyInstance(instanceClassLoader, interfaces, new CachingHandler(object));
            return (T)proxyObject;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Mutator.class))
        {
            this.caching = false;
            return method.invoke(object, args);
        }
        else if (method.isAnnotationPresent(Cache.class))
        {
            if (this.caching) {
                return cacheValue;
            }
            else {
                cacheValue = method.invoke(object, args);
                this.caching = true;
                return cacheValue;
            }
        }
        return cacheValue; //method.invoke(object, args);
    }
}