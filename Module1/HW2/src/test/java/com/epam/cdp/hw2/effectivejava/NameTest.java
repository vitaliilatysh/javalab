package com.epam.cdp.hw2.effectivejava;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class NameTest {

    //Section 7. Item 43: Prefer method references to lambdas
    @Test
    public void shouldCountElementsInList(){

        assertEquals(Lists.newArrayList("DANIEL", "MARIA", "CLARA"), new Name().getNames().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList()));
    }
}