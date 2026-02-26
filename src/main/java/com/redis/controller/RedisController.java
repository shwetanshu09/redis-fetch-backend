package com.redis.controller;

import com.redis.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/data")
    public Map<String, Object> getJobs() {
        Map<String, Object> jobs = redisService.fetchJobs();
        Map<String, Object> response = new HashMap<>();
        response.put("data", jobs);
        response.put("refreshedAt", Instant.now().toString());
        return response;
    }
}