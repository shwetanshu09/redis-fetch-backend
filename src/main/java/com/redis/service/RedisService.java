package com.redis.service;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public Map<String, Object> fetchJobs() {
        String redisKey = redisTemplate.keys("cache:data:*").stream().findFirst().orElse(null);
        if (redisKey == null) {
            return new HashMap<>();
        }

        String dataJson = redisTemplate.opsForValue().get(redisKey);
        if (dataJson == null) return new HashMap<>();

        try {
            Map<String, Object> mainMap = objectMapper.readValue(dataJson, new TypeReference<Map<String, Object>>() {});
            String nestedData = (String) mainMap.get("data");
            Map<String, Object> dataMap = objectMapper.readValue(nestedData, new TypeReference<Map<String, Object>>() {});
            return dataMap;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}