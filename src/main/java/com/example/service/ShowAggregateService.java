package com.example.service;

import java.util.HashMap;
import java.util.Map;

import com.example.mapper.DailyPostsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowAggregateService {

    @Autowired
    private DailyPostsMapper dailyPostsMapper;

    public Map<String, String> showAggregate() {
        Map<String, String> map = new HashMap<>();
        return map;
    }

}