package com.loiane.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloControllerJava {

    @GetMapping
    public String hello(){
        return  "Hello, world!";
    }

}
