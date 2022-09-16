package com.example.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "git")
class GitConfig {
    lateinit var token: String
    lateinit var url: String
}
