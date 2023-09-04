package com.hai818i.tp4.controllers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HomeController {


    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    @RequestMapping("/appversion")
    public String getVersion() {
        return appVersion;
    }

}