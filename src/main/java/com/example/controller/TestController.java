package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.DailyPostMapper;
import com.example.mapper.MailMapper;
import com.example.mapper.PostedCommentMapper;
import com.example.mapper.PostedConditionMapper;
import com.example.mapper.PostedMotivationMapper;
import com.example.mapper.PostedPerformanceMapper;
import com.example.mapper.UserMapper;

/**
 * DBに大量のデータを挿入してテストするコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserMapper user;
	@Autowired
	private MailMapper mail;
	@Autowired
	private DailyPostMapper daily;
	@Autowired
	private PostedCommentMapper com;
	@Autowired
	private PostedPerformanceMapper per;
	@Autowired
	private PostedMotivationMapper mot;
	@Autowired
	private PostedConditionMapper con;
	
	
	//50人分をテスト
	@RequestMapping("/insert")
	public void insert() {
		user.insertUsers();
		mail.insertMails();
		
		for (int i = 1; i <= 10; i++) {
		}
		daily.insertDailyPost();
		con.insertPostedCondition();
		mot.insertPostedMotivation();
		per.insertPostedPerformance();
		com.insertPostedComment();
	}
}