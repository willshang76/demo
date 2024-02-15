package com.example.demo.demos.web;

import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

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
