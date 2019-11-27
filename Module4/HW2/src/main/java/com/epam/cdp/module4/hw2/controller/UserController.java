package com.epam.cdp.module4.hw2.controller;

import com.epam.cdp.module4.hw2.exceptions.UserNotFoundException;
import com.epam.cdp.module4.hw2.facade.impl.BookingFacadeImpl;
import com.epam.cdp.module4.hw2.model.User;
import com.epam.cdp.module4.hw2.model.impl.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BookingFacadeImpl bookingFacade;

    /**
     * Get user by id
     *
     * @param id    user id
     * @param model model
     * @return model view
     */
    @GetMapping(value = "/{id}")
    public String getUserById(
            @PathVariable int id,
            Model model) {
        User user = bookingFacade.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        model.addAttribute("users", Collections.singleton(user));
        return "users";
    }

    /**
     * Get users by name
     *
     * @param userName user name
     * @param pageSize page size
     * @param page     page number
     * @param model    model
     * @return model view
     */
    @GetMapping(value = "/find", params = {"userName", "pageSize", "page"})
    public String getUserByName(
            @RequestParam String userName,
            @RequestParam int pageSize,
            @RequestParam int page,
            Model model) {
        List<User> byName = bookingFacade.getUsersByName(userName, pageSize, page);
        model.addAttribute("users", byName);
        return "users";
    }

    /**
     * Get users by email
     *
     * @param email user email
     * @param model model
     * @return model view
     */
    @GetMapping(value = "/find", params = {"email"})
    public String getUserByEmail(
            @RequestParam String email,
            Model model) {
        User result = bookingFacade.getUserByEmail(email);
        model.addAttribute("users", Collections.singleton(result));
        return "users";
    }


    /**
     * Create user
     *
     * @param name  user name
     * @param email user email
     * @param model model
     * @return model view
     */
    @GetMapping(value = "/create", params = {"name", "email"})
    public String createUser(
            @RequestParam String name,
            @RequestParam String email,
            Model model) {
        User user = new UserImpl();
        user.setName(name);
        user.setEmail(email);

        User result = bookingFacade.createUser(user);
        model.addAttribute("users", Collections.singleton(result));
        return "users";
    }

    /**
     * Update user
     *
     * @param id    user id
     * @param name  user name
     * @param email user email
     * @param model model
     * @return model view
     */
    @GetMapping(value = "/update", params = {"id", "name", "email"})
    public String updateUser(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String email,
            Model model) {
        User user = new UserImpl();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        User result = bookingFacade.updateUser(user);
        model.addAttribute("users", Collections.singleton(result));
        return "users";
    }

    /**
     * Delete user by id
     *
     * @param id    user id
     * @param model model
     * @return model view
     */
    @PostMapping(value = "/{id}")
    public String deleteUser(@PathVariable int id, Model model) {
        boolean result = bookingFacade.deleteUser(id);
        if (!result) {
            throw new UserNotFoundException(id);
        }
        model.addAttribute("deleted", result);
        return "users";
    }

}
