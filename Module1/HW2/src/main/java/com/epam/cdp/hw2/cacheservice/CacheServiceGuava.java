package com.epam.cdp.hw2.cacheservice;

import com.epam.cdp.hw2.utils.Statistics;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheServiceGuava extends Statistics implements ICacheService<CacheEntry> {

    private static final Logger logger = Logger.getLogger(CacheServiceGuava.class);

    private CacheLoader<String, CacheEntry> loader;
    private Map<String, CacheEntry> storage = new HashMap<>();
    private LoadingCache<String, CacheEntry> cache;
    private Statistics statistics;
    private RemovalListener<String, CacheEntry> listener = entry -> {
        if (entry.wasEvicted()) {
            logger.info("Removed: " + entry.getValue());
            statistics.addEvictionToStats();
        }
    };

    CacheServiceGuava() {
        loader = new CacheLoader<String, CacheEntry>() {
            @Override
            public CacheEntry load(String entryKey) {
                return storage.get(entryKey);
            }
        };

        cache = CacheBuilder.newBuilder()
                .maximumSize(MAX_CACHE_SIZE)
                .removalListener(listener)
                .expireAfterAccess(EXPIRE_AFTER_ACCESS, TimeUnit.SECONDS)
                .build(loader);

        statistics = new Statistics();
    }

    Map<String, CacheEntry> getStorage() {
        return storage;
    }

    @Override
    public CacheEntry get(String entryKey) {
        return cache.getIfPresent(entryKey);
    }

    @Override
    public boolean put(String entryKey, CacheEntry cacheEntry) {

        if (cacheEntry == null || entryKey == null) {
            return false;
        }

        long startPutTime = System.currentTimeMillis();
        cache.getUnchecked(entryKey);
        long finishPutTime = System.currentTimeMillis();

        long putTime = finishPutTime - startPutTime;
        statistics.getAllPutTimes().add(putTime);
        logger.info("Added: " + cacheEntry + " in " + putTime + " ms.");
        return true;
    }
}
