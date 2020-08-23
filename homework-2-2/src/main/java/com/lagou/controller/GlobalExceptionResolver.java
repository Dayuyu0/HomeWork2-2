package com.lagou.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

// 可以让我们优雅的捕获所有Controller对象handler方法抛出的异常
@ControllerAdvice
public class GlobalExceptionResolver {


    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", e.getMessage());
        modelAndView.setViewName("/login");
        return modelAndView;
    }


    @Controller
    public class RestNotFoundFilter implements ErrorController {

        private static final int NOT_FOUND_CODE = 404;

        private static final String ERROR_PATH = "/error";

        @RequestMapping(ERROR_PATH)
        public ModelAndView handleError() {
            return handleException(new RuntimeException("404"), null);
        }

        @Override
        public String getErrorPath() {
            return ERROR_PATH;
        }
    }
}
