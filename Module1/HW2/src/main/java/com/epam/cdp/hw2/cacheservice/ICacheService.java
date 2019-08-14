package com.epam.cdp.hw2.cacheservice;

public interface ICacheService<T> {

    int MAX_CACHE_SIZE = 100_000;
    int EXPIRE_AFTER_ACCESS = 5;

    /**
     * Get cache entry
     * @param key represent the object key to get from cache
     * @return return cache entry, if no such entry, null is returned
     */
    T get(String key);

    /**
     * Put cache entry into cache
     * @param entryKey represent the object to put to the cache
     * @param object object to store in cache
     * @return return true, if entry has been put, otherwise false
     */
    boolean put(String entryKey, T object);

}
