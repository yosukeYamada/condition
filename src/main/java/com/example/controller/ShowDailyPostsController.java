package com.example.controller;

import java.util.List;
import java.util.Map;

import com.example.domain.DailyPost;
import com.example.service.ShowDailyPostsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 投稿したコンディション一覧を表示するコントローラークラス
 * 
 * @yuichiyasui
 */
@RestController
public class ShowDailyPostsController {

	@Autowired
	private ShowDailyPostsService showDailyPostsService;

	/**
	 * ユーザの投稿履歴を取得するメソッド.
	 * 
	 * @param form ユーザIDのリクエストパラメータ
	 * @return 投稿履歴リスト
	 */
	@RequestMapping("/showDailyPosts")
	public List<DailyPost> showDailyPostsHistory(@RequestBody Map<String, String> param) {
		
		List<DailyPost> dailyPostList = showDailyPostsService.getDailyPostList(Integer.parseInt(param.get("userId")));
		return dailyPostList;
	}
}