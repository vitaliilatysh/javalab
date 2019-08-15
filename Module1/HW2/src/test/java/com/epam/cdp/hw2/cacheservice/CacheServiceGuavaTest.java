package com.epam.cdp.hw2.cacheservice;

import org.junit.AfterClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CacheServiceGuavaTest {

    private static CacheServiceGuava cacheServiceGuava = new CacheServiceGuava();

    @AfterClass
    public static void showTotalEvictions() {
        cacheServiceGuava.showStatistics();
    }

    @Test
    public void shouldReturnTrueAfterAddingEntryToCache() {
        assertTrue(cacheServiceGuava.put("3", "guava3"));
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
        cacheServiceGuava.put("1", "guava1");

        assertEquals("guava1", cacheServiceGuava.get("1"));
    }

    @Test
    public void shouldNotRemoveFromCacheIfTTLisLessThenExpirationLimit() throws InterruptedException {
        cacheServiceGuava.put("1", "guava1");

        TimeUnit.SECONDS.sleep(4);

        assertEquals("guava1", cacheServiceGuava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheIfTTLisMoreThenExpirationLimit() throws InterruptedException {
        cacheServiceGuava.put("1", "guava1");

        TimeUnit.SECONDS.sleep(5);

        assertNull(cacheServiceGuava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheLeastRecentAccessedAmongSeveralItems() throws InterruptedException {
        cacheServiceGuava.put("1", "guava1");

        TimeUnit.SECONDS.sleep(7);

        cacheServiceGuava.put("2", "guava2");
        cacheServiceGuava.put("3", "guava3");

        TimeUnit.SECONDS.sleep(1);

        assertEquals("guava2", cacheServiceGuava.get("2"));
        assertEquals("guava3", cacheServiceGuava.get("3"));
        assertNull(cacheServiceGuava.get("1"));

    }
}