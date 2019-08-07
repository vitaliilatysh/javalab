package com.epam.cdp.hw2;

import java.util.Map;

public class CacheService {

    private static final int MAX_CACHE_SIZE = 100_000;

    private Map<CacheEntry, Long> values;

    public CacheEntry get(CacheEntry cacheEntry) {
        if (values.get(cacheEntry) != null){
            return cacheEntry;
        }
        return null;
    }

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
