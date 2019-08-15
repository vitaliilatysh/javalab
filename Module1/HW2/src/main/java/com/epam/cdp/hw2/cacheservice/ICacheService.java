package com.epam.cdp.hw2.cacheservice;

public interface ICacheService {

    int MAX_CACHE_SIZE = 100_000;
    int EXPIRE_AFTER_ACCESS = 5;

    /**
     * Get cache entry
     * @param key represent the object key to get from cache
     * @return return the entry value, if no such entry, null is returned
     */
    String get(String key);

    /**
     * Put cache entry into cache
     * @param entryKey represent the object to put to the cache
     * @param entryValue object value to store in cache
     * @return return true, if entry has been put, otherwise false
     */
    boolean put(String entryKey, String entryValue);

}
