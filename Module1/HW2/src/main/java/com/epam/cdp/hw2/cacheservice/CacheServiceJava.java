package com.epam.cdp.hw2.cacheservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheServiceJava extends StatsClass implements ICacheService{

    private Map<CacheEntry, Integer> values;

    CacheServiceJava(){
        values = new ConcurrentHashMap<>();
    }

    @Override
    public CacheEntry get(CacheEntry cacheEntry) {
        if (values.get(cacheEntry) != null) {
            values.put(cacheEntry, values.get(cacheEntry) + 1);
            return cacheEntry;
        }
        return null;
    }

    @Override
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
