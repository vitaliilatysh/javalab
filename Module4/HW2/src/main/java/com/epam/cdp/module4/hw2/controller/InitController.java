package com.epam.cdp.module4.hw2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class InitController
{
    @GetMapping("/")
    public String homeInit(Locale locale, Model model) {
        return "home";
    }
}
