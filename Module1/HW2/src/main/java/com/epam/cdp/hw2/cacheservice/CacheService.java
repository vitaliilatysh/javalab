package com.epam.cdp.hw2.cacheservice;

import java.util.HashMap;
import java.util.Map;

public class CacheService {

    private static final int MAX_CACHE_SIZE = 2;

    private Map<CacheEntry, Integer> values;

    CacheService(){
        values = new HashMap<>();
    }

    /**
     * Get cache entry
     * @param cacheEntry represent the object to get from cache
     * @return return cache entry, if no such entry, null is returned
     */
    public CacheEntry get(CacheEntry cacheEntry) {
        if (values.get(cacheEntry) != null) {
            values.put(cacheEntry, values.get(cacheEntry) + 1);
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

        if(cacheEntry == null){
            return false;
        }

        if (values.size() < MAX_CACHE_SIZE) {
            values.merge(cacheEntry, 1, (oldValue, newValue) -> oldValue + newValue);
            return true;
        }

        if (values.size() == MAX_CACHE_SIZE) {
            Map.Entry<CacheEntry, Integer> cacheEntry1 = values.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .get();
            values.remove(cacheEntry1.getKey());
            values.merge(cacheEntry, 1, (oldValue, newValue) -> oldValue + newValue);
            return true;
        }

        return false;
    }
}
