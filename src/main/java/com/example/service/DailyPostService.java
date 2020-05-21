package com.example.service;

import java.util.List;

import com.example.domain.DailyPost;
import com.example.mapper.DailyPostMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyPostService {

	@Autowired
	private DailyPostMapper dailyPostMapper;

	public List<DailyPost> getDailyPostList(Integer userId) {
		List<DailyPost> dailyPostList = dailyPostMapper.findByUserId(userId);
		return dailyPostList;
	}
}