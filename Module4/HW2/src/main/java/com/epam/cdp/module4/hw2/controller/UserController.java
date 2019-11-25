package com.epam.cdp.module4.hw2.controller;

import com.epam.cdp.module4.hw2.facade.impl.BookingFacadeImpl;
import com.epam.cdp.module4.hw2.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BookingFacadeImpl bookingFacade;

    /**
     * Get users by name
     *
     * @param userName user name
     * @param pageSize page size
     * @param page     page number
     * @param model    model
     * @return model view
     */
    @GetMapping(value = "/users/find", params = {"userName", "pageSize", "page"})
    public String getUserByName(
            @RequestParam String userName,
            @RequestParam int pageSize,
            @RequestParam int page,
            Model model) {
        List<User> byName = bookingFacade.getUsersByName(userName, pageSize, page);
        model.addAttribute("users", byName);
        return "users";
    }

}
