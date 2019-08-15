package com.epam.cdp.hw2.cacheservice;

//Section 4. Item 22: Use interfaces only to define types

public interface ICacheService {
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
