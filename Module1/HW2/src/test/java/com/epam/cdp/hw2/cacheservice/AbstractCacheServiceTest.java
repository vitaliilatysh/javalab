package com.epam.cdp.hw2.cacheservice;

abstract class AbstractCacheServiceTest {
    static CacheEntry cacheEntry1 = new CacheEntry("1");
    static CacheEntry cacheEntry2 = new CacheEntry("2");
    static CacheEntry cacheEntry3 = new CacheEntry("3");

    CacheServiceJava cacheServiceJava = new CacheServiceJava();
    CacheServiceGuava cacheServiceGuava = new CacheServiceGuava();
}
