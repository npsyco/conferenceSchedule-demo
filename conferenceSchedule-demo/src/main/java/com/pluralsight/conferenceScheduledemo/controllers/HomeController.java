package com.pluralsight.conferenceScheduledemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    /**
     * Spring looks in the application.properties
     * for the version using @Value
     * and loads in the the appVersion
     */
    @Value("${app.version}")
    private String appVersion;
    
    @GetMapping
    @RequestMapping("/")
    public Map getStatus(){
        Map map = new HashMap<String, String>();
        map.put("app-version", appVersion);
        return map;
    }
}
