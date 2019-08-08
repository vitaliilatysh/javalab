package com.epam.cdp.hw2.cacheservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CacheServiceJavaTest extends AbstractCacheServiceTest {

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
    public void shouldRemoveLastFrequentUsedEntryAfterReachingTheEntryLimit() {
        cacheServiceJava.put(cacheEntry1);
        cacheServiceJava.get(cacheEntry1);
        cacheServiceJava.put(cacheEntry2);
        cacheServiceJava.put(cacheEntry3);

        assertNull(cacheServiceJava.get(cacheEntry1));
        assertEquals(cacheEntry2, cacheServiceJava.get(cacheEntry2));
        assertEquals(cacheEntry3, cacheServiceJava.get(cacheEntry3));
    }

}
