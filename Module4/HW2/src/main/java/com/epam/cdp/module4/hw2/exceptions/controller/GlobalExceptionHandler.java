package com.epam.cdp.module4.hw2.exceptions.controller;

import com.epam.cdp.module4.hw2.exceptions.EventNotFoundException;
import com.epam.cdp.module4.hw2.exceptions.TicketNotFoundException;
import com.epam.cdp.module4.hw2.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle exception when no event/user/ticket found by id
     *
     * @param request request
     * @param ex      exception
     * @return model view
     */
    @ExceptionHandler({EventNotFoundException.class, UserNotFoundException.class, TicketNotFoundException.class})
    public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex) {
        logger.error("Requested URL=" + request.getRequestURL());
        logger.error("Exception Raised=" + ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());

        modelAndView.setViewName("error");
        return modelAndView;
    }
}
