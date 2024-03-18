package org.example.multithread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class CachingHandler<T> implements InvocationHandler {
    static class CacheKey {
        Object[] arguments;
        public record CacheObject(Object obj, long timeExpired) { };

        public CacheKey(Object[] arguments) {
            this.arguments = arguments;
        }

        // TODO: clone non immutable arguments

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

    private final ConcurrentHashMap<Method, ConcurrentHashMap<CacheKey, CacheKey.CacheObject>>
            store = new ConcurrentHashMap<>();

    private T object; // текущий объект, который хотим посчитать
    private Clock clock;
    public CachingHandler(T object, Clock clock) {
        this.object = object;
        this.clock = clock;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         //Arrays.stream(args).forEach(System.out::println); // для теста - смотрим аргументы
        var cacheKey = new CacheKey(args);

        Object resultObject;
        var currentTime = clock.currentMillis();
        if (method.isAnnotationPresent(Cache.class))
        {
            this.store.putIfAbsent(method, new ConcurrentHashMap<>());   // положили метод в хранилище, если его не было
            var cacheParams = (Cache) method.getAnnotation(Cache.class); // вытащили параметры аннотации
            var newExpTime = currentTime + cacheParams.CacheTime();      // получили время кэширования
            var objectsMap  = this.store.get(method);                    // получили кэшированные значения

            // если в кэше есть актуальная запись
            if (objectsMap.containsKey(cacheKey)) {
                var cacheObject = objectsMap.get(cacheKey);
                if (currentTime <= cacheObject.timeExpired) {
                    resultObject = cacheObject.obj;
                    objectsMap.put(cacheKey, new CacheKey.CacheObject(resultObject, newExpTime));
                    return resultObject;
                }
            }
            // если нет в кэше или протухла
            resultObject = method.invoke(object, args);
            objectsMap.put(cacheKey, new CacheKey.CacheObject(resultObject, newExpTime));
            return resultObject;
        }
        return method.invoke(object, args);

    }
}
