package com.epam.cdp.hw2.cacheservice;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CacheServiceGuavaTest extends AbstractCacheServiceTest {

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
    public void shouldReturnFalseIfAddingMoreThenMaxCacheSize() {
        cacheServiceGuava.put(cacheEntry1);
        cacheServiceGuava.put(cacheEntry2);
        cacheServiceGuava.put(cacheEntry3);

        assertNull(cacheServiceGuava.get(cacheEntry3));
        assertEquals(cacheEntry1, cacheServiceGuava.get(cacheEntry1));
        assertEquals(cacheEntry2, cacheServiceGuava.get(cacheEntry2));
    }

    @Test
    public void shouldRemoveFromCacheLastAccessedItemIn5seconds() throws InterruptedException {
        cacheServiceGuava.put(cacheEntry1);
        cacheServiceGuava.put(cacheEntry2);
        cacheServiceGuava.put(cacheEntry3);
        cacheServiceGuava.get(cacheEntry3);

        TimeUnit.SECONDS.sleep(5);

        assertNull(cacheServiceGuava.get(cacheEntry3));
        assertEquals(cacheEntry1, cacheServiceGuava.get(cacheEntry1));
        assertEquals(cacheEntry2, cacheServiceGuava.get(cacheEntry2));
    }
}