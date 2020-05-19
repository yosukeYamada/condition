package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DailyPost;
import com.example.form.ShowDailyPostListForm;
import com.example.service.DailyPostService;

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
	public List<DailyPost> showDailyPostsHistory(@RequestBody ShowDailyPostListForm form) {
		List<DailyPost> dailyPostList = dailyPostService.getDailyPostList(form);
		return dailyPostList;
	}
}