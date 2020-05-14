package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.PostedNews;
import com.example.form.PostedNewsForm;
import com.example.service.PostedNewsService;

/**
 * @author sakai
 *
 */
@RestController
public class NewsController {

	@Autowired
	private PostedNewsService postedNewsService;
	
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
		List<PostedNews> PostedNewsList = postedNewsService.PostedNews(form);
		return PostedNewsList;
	}
	
	/**
	 * お知らせ投稿一覧を表示するメソッド.
	 * 
	 * @return お知らせ投稿一覧
	 */
	@ResponseBody
	@RequestMapping("/showNewsPostList")
	public List<PostedNews> showNewsPostList() {
		return postedNewsService.showNewsPostList();
	}
	
}
