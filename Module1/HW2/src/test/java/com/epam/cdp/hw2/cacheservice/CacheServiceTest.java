package com.epam.cdp.hw2.cacheservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.*;

@RunWith(JUnit4.class)
public class CacheServiceTest {

    private CacheEntry cacheEntry1 = new CacheEntry("1");
    private CacheEntry cacheEntry2 = new CacheEntry("2");
    private CacheEntry cacheEntry3 = new CacheEntry("3");

    private CacheService cacheService = new CacheService();

    @Test
    public void shouldReturnTrueAfterAddingEntryToCache(){
        assertTrue(cacheService.put(cacheEntry1));
    }

    @Test
    public void shouldReturnFalseAfterAddingNullEntry(){
        assertFalse(cacheService.put(null));
    }

    @Test
    public void shouldReturnEntryWhenGettingFromCache(){
        cacheService.put(cacheEntry1);

        assertEquals(cacheEntry1, cacheService.get(cacheEntry1));
    }

    @Test
    public void shouldRemoveLastFrequentUsedEntryAfterReachingTheEntryLimit(){
        cacheService.put(cacheEntry1);
        cacheService.get(cacheEntry1);
        cacheService.put(cacheEntry2);
        cacheService.put(cacheEntry3);

        assertNull(cacheService.get(cacheEntry1));
        assertEquals(cacheEntry2, cacheService.get(cacheEntry2));
        assertEquals(cacheEntry3, cacheService.get(cacheEntry3));
    }

}
