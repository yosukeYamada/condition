package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.PostedNews;
import com.example.service.ShowNewsListService;

/**
 * お知らせ投稿一覧を表示するコントローラー.
 * 
 * @author sakai
 *
 */
@RestController
public class ShowNewsListController {

	@Autowired
	private ShowNewsListService showNewsListService;

	/**
	 * お知らせ投稿一覧を表示するメソッド.
	 * 
	 * @return お知らせ投稿一覧
	 */
	@RequestMapping("/showNewsList")
	public List<PostedNews> reisterUser() {
		return showNewsListService.showNewsPostList();
	}

}