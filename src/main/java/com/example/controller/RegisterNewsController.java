package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.PostedNews;
import com.example.form.PostedNewsForm;
import com.example.service.RegisterNewsService;

/**
 * お知らせ投稿の登録を行うコントローラー.
 * 
 * @author sakai
 *
 */
@RestController
public class RegisterNewsController {

	@Autowired
	private RegisterNewsService registerNewsService;

	public PostedNewsForm setUpForm() {
		return new PostedNewsForm();
	}

	/**
	 * お知らせ投稿を行うメソッド.
	 * 
	 * @param form 投稿内容
	 * @return お知らせ投稿一覧
	 */
	@RequestMapping("/news")
	public List<PostedNews> postedNews(@RequestBody PostedNewsForm form) {
		List<PostedNews> PostedNewsList = registerNewsService.PostedNews(form);
		return PostedNewsList;
	}

}
