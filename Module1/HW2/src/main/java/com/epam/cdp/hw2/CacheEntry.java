package com.epam.cdp.hw2;

import java.util.Objects;

public class CacheEntry {

    private String value;

    CacheEntry(String value){
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheEntry that = (CacheEntry) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
