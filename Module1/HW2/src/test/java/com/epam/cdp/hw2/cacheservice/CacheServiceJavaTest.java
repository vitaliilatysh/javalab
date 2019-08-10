package com.epam.cdp.hw2.cacheservice;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import static com.epam.cdp.hw2.utils.Statistics.showStatistics;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CacheServiceJavaTest {

    private static CacheEntry cacheEntry1 = new CacheEntry("java1");
    private static CacheEntry cacheEntry2 = new CacheEntry("java2");
    private static CacheEntry cacheEntry3 = new CacheEntry("java3");

    private static CacheServiceJava cacheServiceJava;

    @AfterClass
    public static void showTotalEvictions() {
        showStatistics(cacheServiceJava);
    }

    @Before
    public void init() {
        cacheServiceJava = new CacheServiceJava();
    }

    @Test
    public void shouldReturnTrueAfterAddingEntryToCache() {
        assertTrue(cacheServiceJava.put(cacheEntry1));
    }

    @Test
    public void shouldReturnFalseAfterAddingNullEntry() {
        assertFalse(cacheServiceJava.put(null));
    }

    @Test
    public void shouldReturnEntryWhenGettingFromCache() {
        cacheServiceJava.put(cacheEntry1);

        assertEquals(cacheEntry1, cacheServiceJava.get(cacheEntry1));
    }

    @Test
    public void shouldNotRemoveFromCacheIfTTLisLessThenExpirationLimit() throws InterruptedException {
        cacheServiceJava.put(cacheEntry1);

        TimeUnit.SECONDS.sleep(4);

        assertEquals(cacheEntry1, cacheServiceJava.get(cacheEntry1));
    }

    @Test
    public void shouldRemoveFromCacheIfTTLisMoreThenExpirationLimit() throws InterruptedException {
        cacheServiceJava.put(cacheEntry1);

        TimeUnit.SECONDS.sleep(6);

        assertNull(cacheServiceJava.get(cacheEntry1));
    }

    @Test
    public void shouldRemoveFromCacheLeastFrequentAccessedAmongSeveralItems() throws InterruptedException {
        cacheServiceJava.put(cacheEntry2);
        cacheServiceJava.put(cacheEntry3);

        TimeUnit.SECONDS.sleep(8);

        cacheServiceJava.put(cacheEntry1);

        assertEquals(cacheEntry1, cacheServiceJava.get(cacheEntry1));
        assertEquals(cacheEntry2, cacheServiceJava.get(cacheEntry2));
        assertNull(cacheServiceJava.get(cacheEntry3));

    }

}
