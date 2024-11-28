package com.example.Full_Todo_App_Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/{path:[^\\.]*}") // Match all paths except those with a dot (e.g., .js, .css)
    public String redirect() {
        return "forward:/index.html"; // Forward to React's index.html
    }
}
