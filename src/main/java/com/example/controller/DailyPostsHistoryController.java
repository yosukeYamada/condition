package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DailyPost;
import com.example.form.showDailyPostListForm;

@RestController
@RequestMapping("")
public class DailyPostsHistoryController {

	@CrossOrigin(origins="http://localhost:8888")
	@RequestMapping("/motivations")
	public List<DailyPost> showDaylyPostsHistory(@RequestBody showDailyPostListForm form){
		
		System.err.println("ユーザーID : " + form.getUserId());
		
		List<DailyPost> dailyPostList = new ArrayList<>();
		return dailyPostList;
	}
}