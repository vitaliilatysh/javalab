package com.epam.cdp.hw2.cacheservice;

import com.epam.cdp.hw2.utils.Statistics;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CacheServiceJava extends Statistics implements ICacheService {

    private static final Logger logger = Logger.getLogger(CacheServiceJava.class);

    private ScheduledExecutorService scheduledExecutorService;
    private Map<CacheEntry, Integer> values;
    private Statistics statistics;

    CacheServiceJava() {
        values = new ConcurrentHashMap<>();
        statistics = new Statistics();
        scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable removeTask = () -> {
            Map.Entry<CacheEntry, Integer> entryToRemove = values.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .get();
            values.remove(entryToRemove.getKey());
            statistics.addEvictionToStats();
            logger.info("Removed: " + entryToRemove.getKey());
        };

        scheduledExecutorService.scheduleAtFixedRate(removeTask, EXPIRE_AFTER_ACCESS, EXPIRE_AFTER_ACCESS, TimeUnit.SECONDS);
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public CacheEntry get(CacheEntry cacheEntry) {
        if (values.get(cacheEntry) == null) {
            return null;
        }

        values.put(cacheEntry, values.get(cacheEntry) + 1);
        logger.info("Get: " + cacheEntry);
        return cacheEntry;
    }

    @Override
    public boolean put(CacheEntry cacheEntry) {

        if (cacheEntry == null) {
            return false;
        }

        if (values.size() < MAX_CACHE_SIZE) {

            if (values.get(cacheEntry) != null) {
                logger.info("Already in cache: " + cacheEntry);
            } else {
                long startPutTime = System.currentTimeMillis();
                values.put(cacheEntry, 1);
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
