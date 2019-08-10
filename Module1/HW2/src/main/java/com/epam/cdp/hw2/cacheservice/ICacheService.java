package com.epam.cdp.hw2.cacheservice;

import com.epam.cdp.hw2.utils.Statistics;

public interface ICacheService {

    int MAX_CACHE_SIZE = 100_000;
    int EXPIRE_AFTER_ACCESS = 5;

    /**
     * Get cache entry
     * @param cacheEntry represent the object to get from cache
     * @return return cache entry, if no such entry, null is returned
     */
    CacheEntry get(CacheEntry cacheEntry);

    /**
     * Put cache entry into cache
     * @param cacheEntry represent the object to put to the cache
     * @return return true, if entry has been put, otherwise false
     */
    boolean put(CacheEntry cacheEntry);

    /**
     * Getting Statistics object with average put time and total evictions for for cache
     * @return statistics object
     */
    Statistics getStatistics();
}
