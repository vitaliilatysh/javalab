package com.epam.cdp.hw2.cacheservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CacheServiceGuava extends StatsClass implements ICacheService {
    
    private CacheLoader<CacheEntry, String> loader;
    private LoadingCache<CacheEntry, String> cache;

    CacheServiceGuava() {
        loader = new CacheLoader<CacheEntry, String>() {
            @Override
            public String load(CacheEntry entry) {
                return entry.toString();
            }
        };

        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(EXPIRE_AFTER_ACCESS, TimeUnit.MILLISECONDS)
                .build(loader);

    }

    @Override
    public CacheEntry get(CacheEntry cacheEntry) {
        if (cache.getIfPresent(cacheEntry) == null) {
            return null;
        }
        return cacheEntry;
    }

    @Override
    public boolean put(CacheEntry cacheEntry) {

        if (cacheEntry == null) {
            return false;
        }

        if (cache.size() < MAX_CACHE_SIZE) {
            cache.getUnchecked(cacheEntry);
            return true;
        }

        if (cache.size() == MAX_CACHE_SIZE) {
            return false;
        }

        return false;
    }
}
