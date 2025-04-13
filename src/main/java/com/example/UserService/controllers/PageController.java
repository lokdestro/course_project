package com.example.UserService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("/")
    public String get() {
        System.out.println("Start");
        return "redirect:/index.html";
    }
}
