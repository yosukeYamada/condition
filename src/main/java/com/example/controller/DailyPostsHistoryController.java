package com.example.controller;

import java.util.List;
import java.util.Map;

import com.example.domain.DailyPost;
import com.example.service.DailyPostService;

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
public class DailyPostsHistoryController {

	@Autowired
	private DailyPostService dailyPostService;

	/**
	 * ユーザの投稿履歴を取得するメソッド.
	 * 
	 * @param form ユーザIDのリクエストパラメータ
	 * @return 投稿履歴リスト
	 */
	@RequestMapping("/motivations") // TODO メソッド名とパスを一致させるByYasui
	public List<DailyPost> showDailyPostsHistory(@RequestBody Map<String, String> param) {
		List<DailyPost> dailyPostList = dailyPostService.getDailyPostList(Integer.parseInt(param.get("userId")));
		return dailyPostList;
	}
}