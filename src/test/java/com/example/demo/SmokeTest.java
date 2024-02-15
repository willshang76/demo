package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.demos.web.BasicController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Smoke testing, also known as build verification testing or sanity testing,
 * is a preliminary set of checks performed on a new software build to ensure basic functionality and stability.
 * It's like a quick sniff test for smoke coming from a fire - if you smell it, something's definitely wrong,
 * but the absence of smoke doesn't necessarily mean everything's perfect.
 */
@SpringBootTest
class SmokeTest {

    @Autowired
    private BasicController basicController;

    @Test
    void contextLoads() throws Exception {
        assertThat(basicController).isNotNull();
    }
}