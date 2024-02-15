package com.example.demo.demos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

    @Autowired
    private EnvConfig envConfig;

    @GetMapping("/config")
    public EnvConfig config() {
        return envConfig;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return user;
    }

    @GetMapping(value = "/user/{userId}/role/{roleId}")
    public String getLogin(@PathVariable("userId") String userId, @PathVariable("roleId") String roleId) {
        return "User Id : " + userId + " Role Id : " + roleId;
    }

}
