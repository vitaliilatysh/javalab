package com.epam.cdp.hw2.cacheservice;

import com.epam.cdp.hw2.utils.Statistics;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CacheServiceJava extends Statistics implements ICacheService<CacheEntry> {

    private static final Logger logger = Logger.getLogger(CacheServiceJava.class);

    private ScheduledExecutorService scheduledExecutorService;
    private Map<String, CacheEntry> values;
    private Statistics statistics;

    CacheServiceJava() {
        values = new ConcurrentHashMap<>();
        statistics = new Statistics();
        scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable removeTask = () -> {
            Map.Entry<String, CacheEntry> entryToRemove = values.entrySet().stream()
                    .min(Comparator.comparing(entry -> entry.getValue().getCounter()))
                    .get();
            values.remove(entryToRemove.getKey());
            statistics.addEvictionToStats();
            logger.info("Removed: " + entryToRemove.getValue());
        };

        scheduledExecutorService.scheduleAtFixedRate(removeTask, EXPIRE_AFTER_ACCESS, EXPIRE_AFTER_ACCESS, TimeUnit.SECONDS);
    }

    @Override
    public CacheEntry get(String entryKey) {
        CacheEntry cacheEntry = values.get(entryKey);

        if (cacheEntry == null) {
            return null;
        }

        cacheEntry.incrementCounter();
        return cacheEntry;
    }

    @Override
    public boolean put(String entryKey, CacheEntry cacheEntry) {

        if (cacheEntry == null || entryKey == null) {
            return false;
        }

        if (values.size() < MAX_CACHE_SIZE) {
            cacheEntry.incrementCounter();

            if (values.get(entryKey) != null) {
                logger.info("Already in cache: " + cacheEntry);
            } else {
                long startPutTime = System.currentTimeMillis();
                values.put(entryKey, cacheEntry);
                long finishPutTime = System.currentTimeMillis();

                long timeToPut = finishPutTime - startPutTime;
                statistics.getAllPutTimes().add(timeToPut);
                logger.info("Added: " + cacheEntry + " in " + timeToPut + " ms.");
                return true;
            }
        }

        return false;
    }
}
