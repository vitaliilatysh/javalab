package com.epam.cdp.hw2.cacheservice;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CacheServiceJavaTest {

    private static CacheServiceJava cacheServiceJava = new CacheServiceJava();

    @AfterClass
    public static void showTotalEvictions() {
        cacheServiceJava.showStatistics();
    }

    @Test
    public void shouldReturnTrueAfterFirstAddingEntryToCache() {
        assertTrue(cacheServiceJava.put("3", "java3"));
    }

    @Test
    public void shouldReturnFalseAfterAddingTheSameEntryAgainToCache() {
        cacheServiceJava.put("1", "java1");
        assertFalse(cacheServiceJava.put("1", "java1"));
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
        cacheServiceJava.put("1", "java1");

        assertEquals("java1", cacheServiceJava.get("1"));
    }

    @Test
    public void shouldNotRemoveFromCacheIfTTLisLessThenExpirationLimit() throws InterruptedException {
        cacheServiceJava.put("1", "java1");

        TimeUnit.SECONDS.sleep(4);

        assertEquals("java1", cacheServiceJava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheIfTTLisMoreThenExpirationLimit() throws InterruptedException {
        cacheServiceJava.put("1", "java1");

        TimeUnit.SECONDS.sleep(6);

        assertNull(cacheServiceJava.get("1"));
    }

    @Test
    public void shouldRemoveFromCacheLeastFrequentAccessedAmongSeveralItems() throws InterruptedException {
        cacheServiceJava.put("2", "java2");
        cacheServiceJava.put("3", "java3");
        cacheServiceJava.put("3", "java3");

        TimeUnit.SECONDS.sleep(6);

        cacheServiceJava.put("1", "java1");

        assertEquals("java1", cacheServiceJava.get("1"));
        assertEquals("java3", cacheServiceJava.get("3"));
        assertNull(cacheServiceJava.get("2"));

    }

}
