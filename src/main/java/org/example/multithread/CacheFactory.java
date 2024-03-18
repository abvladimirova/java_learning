package org.example.multithread;

import java.lang.reflect.Proxy;
public class CacheFactory {
    private final Clock myClock;
    public CacheFactory(Clock myClock) {
        this.myClock = myClock;
    }
    public CacheFactory() {
        this(System::currentTimeMillis);
    }

    public <T> T makeCachable(Object object){
        ClassLoader instanceClassLoader = object.getClass().getClassLoader();
        Class[] interfaces = object.getClass().getInterfaces();
        Object proxyObject = Proxy.newProxyInstance(instanceClassLoader, interfaces, new CachingHandler(object, myClock));
        return (T)proxyObject;
    }
}
