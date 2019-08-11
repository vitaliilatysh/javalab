package com.epam.cdp.hw2.cacheservice;

import com.epam.cdp.hw2.utils.Statistics;
import com.google.common.cache.*;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class CacheServiceGuava extends Statistics implements ICacheService {

    private static final Logger logger = Logger.getLogger(CacheServiceGuava.class);
    private CacheLoader<CacheEntry, String> loader;
    private LoadingCache<CacheEntry, String> cache;
    private Statistics statistics;

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    CacheServiceGuava() {
        loader = new CacheLoader<CacheEntry, String>() {
            @Override
            public String load(CacheEntry entry) {
                return entry.toString();
            }
        };

        cache = CacheBuilder.newBuilder()
                .removalListener(listener)
                .expireAfterAccess(EXPIRE_AFTER_ACCESS, TimeUnit.SECONDS)
                .build(loader);

        statistics = new Statistics();
    }

    private RemovalListener<CacheEntry, String> listener = entry -> {
        if (entry.wasEvicted()) {
            logger.info("Removed: " + entry.getKey());
            statistics.addEvictionToStats();
        }
    };

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
            long startPutTime = System.currentTimeMillis();
            cache.getUnchecked(cacheEntry);
            long finishPutTime = System.currentTimeMillis();

            long putTime = finishPutTime - startPutTime;
            statistics.getAllPutTimes().add(putTime);
            logger.info("Added " + cacheEntry + " in " + putTime + " ms.");
            return true;
        }

        return false;
    }
}
