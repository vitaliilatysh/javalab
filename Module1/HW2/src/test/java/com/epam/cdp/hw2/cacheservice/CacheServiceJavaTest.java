package com.epam.cdp.hw2.cacheservice;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CacheServiceJavaTest {

    private static CacheEntry cacheEntry1 = new CacheEntry("java1");
    private static CacheEntry cacheEntry2 = new CacheEntry("java2");
    private static CacheEntry cacheEntry3 = new CacheEntry("java3");

    private static CacheServiceJava cacheServiceJava = new CacheServiceJava();

    @AfterClass
    public static void showTotalEvictions() {
        cacheServiceJava.showStatistics();
    }

    @Test
    public void shouldReturnTrueAfterFirstAddingEntryToCache() {
        assertTrue(cacheServiceJava.put("3", cacheEntry1));
    }

    @Test
    public void shouldReturnFalseAfterAddingTheSameEntryAgainToCache() {
        cacheServiceJava.put("1", cacheEntry1);
        assertFalse(cacheServiceJava.put("1", cacheEntry1));
    }

    @Test
    public void shouldReturnFalseAfterAddingNullEntry() {
        assertFalse(cacheServiceJava.put("1", null));
    }

    @Test
    public void shouldReturnFalseAfterAddingNullKeyAndEntry() {
        assertFalse(cacheServiceJava.put(null, null));
    }

    @Test
    public void shouldReturnEntryWhenGettingFromCache() {
        cacheServiceJava.put("1", cacheEntry1);

        assertEquals(cacheEntry1, cacheServiceJava.get("1"));
    }

    @Test
    public void shouldNotRemoveFromCacheIfTTLisLessThenExpirationLimit() throws InterruptedException {
        cacheServiceJava.put("1", cacheEntry1);

        TimeUnit.SECONDS.sleep(4);

        assertEquals(cacheEntry1, cacheServiceJava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheIfTTLisMoreThenExpirationLimit() throws InterruptedException {
        cacheServiceJava.put("1", cacheEntry1);

        TimeUnit.SECONDS.sleep(6);

        assertNull(cacheServiceJava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheLeastFrequentAccessedAmongSeveralItems() throws InterruptedException {
        cacheServiceJava.put("2", cacheEntry2);
        cacheServiceJava.put("3", cacheEntry3);
        cacheServiceJava.put("3", cacheEntry3);

        TimeUnit.SECONDS.sleep(6);

        cacheServiceJava.put("1", cacheEntry1);

        assertEquals(cacheEntry1, cacheServiceJava.get("1"));
        assertEquals(cacheEntry3, cacheServiceJava.get("3"));
        assertNull(cacheServiceJava.get("2"));

    }

}
