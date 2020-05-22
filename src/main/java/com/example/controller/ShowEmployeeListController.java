package com.example.controller;

import java.util.List;

import com.example.domain.User;
import com.example.service.ShowEmployeeListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員一覧画面を表示するコントローラー.
 * 
 * @author riho.ikeda
 *
 */
@RestController
public class ShowEmployeeListController {

	@Autowired
	private ShowEmployeeListService showEmployeeListService;

	/**
	 * 従業員一覧(ユーザー情報with投稿一覧)を表示するメソッド.
	 * 
	 * @apiNote 投稿一覧は最新の投稿が先頭に来るように降順に設定
	 * @return 従業員一覧
	 */
	@RequestMapping("/showEmployeeList")
	public List<User> showEmployeeList() {
		return showEmployeeListService.showEmployeeList();
	}
}