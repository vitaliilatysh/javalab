package com.epam.cdp.hw2.cacheservice;

import org.junit.AfterClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.epam.cdp.hw2.utils.Statistics.showStatistics;
import static org.junit.Assert.*;

public class CacheServiceGuavaTest {

    private static CacheEntry cacheEntry1 = new CacheEntry("guava1");
    private static CacheEntry cacheEntry2 = new CacheEntry("guava2");
    private static CacheEntry cacheEntry3 = new CacheEntry("guava3");

    private static CacheServiceGuava cacheServiceGuava = new CacheServiceGuava();

    @AfterClass
    public static void showTotalEvictions() {
        showStatistics(cacheServiceGuava);
    }

    @Test
    public void shouldReturnTrueAfterAddingEntryToCache() {
        assertTrue(cacheServiceGuava.put(cacheEntry1));
    }

    @Test
    public void shouldReturnFalseAfterAddingNullEntry() {
        assertFalse(cacheServiceGuava.put(null));
    }

    @Test
    public void shouldReturnEntryWhenGettingFromCache() {
        cacheServiceGuava.put(cacheEntry1);

        assertEquals(cacheEntry1, cacheServiceGuava.get(cacheEntry1));
    }

    @Test
    public void shouldNotRemoveFromCacheIfTTLisLessThenExpirationLimit() throws InterruptedException {
        cacheServiceGuava.put(cacheEntry1);

        TimeUnit.SECONDS.sleep(4);

        assertEquals(cacheEntry1, cacheServiceGuava.get(cacheEntry1));
    }

    @Test
    public void shouldRemoveFromCacheIfTTLisMoreThenExpirationLimit() throws InterruptedException {
        cacheServiceGuava.put(cacheEntry1);

        TimeUnit.SECONDS.sleep(5);

        assertNull(cacheServiceGuava.get(cacheEntry1));
    }

    @Test
    public void shouldRemoveFromCacheLeastRecentAccessedAmongSeveralItems() throws InterruptedException {
        cacheServiceGuava.put(cacheEntry1);

        TimeUnit.SECONDS.sleep(7);

        cacheServiceGuava.put(cacheEntry2);
        cacheServiceGuava.put(cacheEntry3);

        TimeUnit.SECONDS.sleep(1);

        assertEquals(cacheEntry2, cacheServiceGuava.get(cacheEntry2));
        assertEquals(cacheEntry3, cacheServiceGuava.get(cacheEntry3));
        assertNull(cacheServiceGuava.get(cacheEntry1));

    }
}