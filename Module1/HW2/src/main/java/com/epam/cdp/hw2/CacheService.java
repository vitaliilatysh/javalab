package com.epam.cdp.hw2;

import java.util.Map;

/**
 * Java implementation of LFU
 */
public class CacheService {

    private static final int MAX_CACHE_SIZE = 100_000;

    private Map<CacheEntry, Long> values;

    /**
     * Get cache entry
     * @param cacheEntry represent the object to get from cache
     * @return return cache entry, if no such entry, null is returned
     */
    public CacheEntry get(CacheEntry cacheEntry) {
        if (values.get(cacheEntry) != null){
            return cacheEntry;
        }
        return null;
    }

    /**
     * Put cache entry into cache
     * @param cacheEntry represent the object to put to the cache
     * @return return true, if entry has been put, otherwise false
     */
    public boolean put(CacheEntry cacheEntry) {

        if(values.size() < MAX_CACHE_SIZE) {
            long count = values.get(cacheEntry);

            if (values.get(cacheEntry) != 0) {
                values.put(cacheEntry, ++count);
            } else {
                values.put(cacheEntry, 1L);
            }
            return true;
        }
        return false;
    }
}
