package org.example.multithread;

import org.example.multithread.Cache;

public interface GetAnswer {
    @Cache(CacheTime = 1000)
    public double getCourse(String currency);
}
