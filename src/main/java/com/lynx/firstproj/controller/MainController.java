package com.lynx.firstproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Home Page");
        return "other/homepage";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Page");
        return "other/about";
    }

    @GetMapping("/adminpage")
    public String admin(Model model) {
        model.addAttribute("title", "Administrator Page");
        return "other/admin";
    }
}
