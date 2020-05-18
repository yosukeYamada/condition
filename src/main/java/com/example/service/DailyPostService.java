package com.example.service;

import java.util.List;

import com.example.domain.DailyPost;
import com.example.form.ShowDailyPostListForm;
import com.example.mapper.DailyPostMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyPostService {

	@Autowired
	private DailyPostMapper dailyPostMapper;

	public List<DailyPost> getDailyPostList(ShowDailyPostListForm form) {
		Integer userId = Integer.parseInt(form.getUserId());
		List<DailyPost> dailyPostList = dailyPostMapper.findByUserId(userId);
		return dailyPostList;
	}
}