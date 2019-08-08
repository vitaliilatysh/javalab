package com.epam.cdp.hw2.effectivejava;

import com.google.common.collect.Lists;

import java.util.List;

public class Name {

    private List<String> names = Lists.newArrayList("Daniel", "Maria", "Clara");

    public List<String> getNames() {
        return names;
    }
}
