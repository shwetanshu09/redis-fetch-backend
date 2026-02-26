package com.redis.model;

import java.util.Map;

public class JobStatus {
    private String title;
    private Map<String, Object> details;

    public JobStatus(String title, Map<String, Object> details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() { 
    	return title; 
    }
    
    public void setTitle(String title) { 
    	this.title = title; 
    }

    public Map<String, Object> getDetails() { 
    	return details; 
    }
    
    public void setDetails(Map<String, Object> details) { 
    	this.details = details; 
    }
}