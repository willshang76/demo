package com.example.demo.demos.web;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "env-config")
@Component
@Data
@ToString
public class EnvConfig {
    private String envName;
    private String password;
}
