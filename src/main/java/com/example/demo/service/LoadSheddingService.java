package com.example.demo.service;

import com.example.demo.entity.LoadSheddingEvent;

public interface LoadSheddingService {

    LoadSheddingEvent triggerLoadShedding(Long forecastId);
}
