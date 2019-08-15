package com.epam.cdp.hw2.cacheservice;

import com.epam.cdp.hw2.utils.Statistics;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.epam.cdp.hw2.utils.Constants.EXPIRE_AFTER_ACCESS;
import static com.epam.cdp.hw2.utils.Constants.MAX_CACHE_SIZE;

public class CacheServiceGuava extends Statistics implements ICacheService {

    private static final Logger logger = Logger.getLogger(CacheServiceGuava.class);

    private CacheLoader<String, CacheEntry> loader;
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
            public CacheEntry load(String entryKey) throws ExecutionException {
                return cache.get(entryKey);
            }
        };

        cache = CacheBuilder.newBuilder()
                .maximumSize(MAX_CACHE_SIZE)
                .removalListener(listener)
                .expireAfterAccess(EXPIRE_AFTER_ACCESS, TimeUnit.SECONDS)
                .build(loader);

        statistics = new Statistics();
    }

    @Override
    public String get(String entryKey) {
        CacheEntry cacheEntry = cache.getIfPresent(entryKey);
        return cacheEntry == null ? null : cacheEntry.getValue();
    }

    @Override
    public boolean put(String entryKey, String entryValue) {

        if (entryValue == null || entryKey == null) {
            return false;
        }

        CacheEntry cacheEntry = cache.getIfPresent(entryKey);

        if(cacheEntry == null) {
            cacheEntry = new CacheEntry(entryValue);
            long startPutTime = System.currentTimeMillis();
            cache.put(entryKey, cacheEntry);
            long finishPutTime = System.currentTimeMillis();

            long putTime = finishPutTime - startPutTime;
            statistics.getAllPutTimes().add(putTime);
            logger.info("Added: " + cacheEntry + " in " + putTime + " ms.");
            return true;
        }
        logger.info("Already in cache: " + cacheEntry);

        return false;
    }
}
