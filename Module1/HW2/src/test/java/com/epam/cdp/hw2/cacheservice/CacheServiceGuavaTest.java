package com.epam.cdp.hw2.cacheservice;

import org.junit.AfterClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CacheServiceGuavaTest {

    private static CacheEntry cacheEntry1 = new CacheEntry("guava1");
    private static CacheEntry cacheEntry2 = new CacheEntry("guava2");
    private static CacheEntry cacheEntry3 = new CacheEntry("guava3");

    private static CacheServiceGuava cacheServiceGuava = new CacheServiceGuava();

    static {
        cacheServiceGuava.getStorage().put("1", cacheEntry1);
        cacheServiceGuava.getStorage().put("2", cacheEntry2);
        cacheServiceGuava.getStorage().put("3", cacheEntry3);
    }

    @AfterClass
    public static void showTotalEvictions() {
        cacheServiceGuava.showStatistics();
    }

    @Test
    public void shouldReturnTrueAfterAddingEntryToCache() {
        assertTrue(cacheServiceGuava.put("1", cacheEntry1));
    }

    @Test
    public void shouldReturnFalseAfterAddingNullEntry() {
        assertFalse(cacheServiceGuava.put("1", null));
    }

    @Test
    public void shouldReturnFalseAfterAddingNullKeyAndEntry() {
        assertFalse(cacheServiceGuava.put(null, null));
    }

    @Test
    public void shouldReturnEntryWhenGettingFromCache() {
        cacheServiceGuava.put("1", cacheEntry1);

        assertEquals(cacheEntry1, cacheServiceGuava.get("1"));
    }

    @Test
    public void shouldNotRemoveFromCacheIfTTLisLessThenExpirationLimit() throws InterruptedException {
        cacheServiceGuava.put("1", cacheEntry1);

        TimeUnit.SECONDS.sleep(4);

        assertEquals(cacheEntry1, cacheServiceGuava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheIfTTLisMoreThenExpirationLimit() throws InterruptedException {
        cacheServiceGuava.put("1", cacheEntry1);

        TimeUnit.SECONDS.sleep(5);

        assertNull(cacheServiceGuava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheLeastRecentAccessedAmongSeveralItems() throws InterruptedException {
        cacheServiceGuava.put("1", cacheEntry1);

        TimeUnit.SECONDS.sleep(7);

        cacheServiceGuava.put("2", cacheEntry2);
        cacheServiceGuava.put("3", cacheEntry3);

        TimeUnit.SECONDS.sleep(1);

        assertEquals(cacheEntry2, cacheServiceGuava.get("2"));
        assertEquals(cacheEntry3, cacheServiceGuava.get("3"));
        assertNull(cacheServiceGuava.get("1"));

    }
}