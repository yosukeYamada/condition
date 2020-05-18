package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.PostedNewsForm;
import com.example.service.DeleteNewsService;

/**
 * お知らせ投稿の削除を行うコントローラー.
 * 
 * @author sakai
 *
 */
@RestController
public class DeleteNewsController {

	
	@Autowired
	private DeleteNewsService deleteNewsService;

	public PostedNewsForm setUpForm() {
		return new PostedNewsForm();
	}
	
	/**
	 * お知らせの削除を行うメソッド
	 * 
	 * @param form お知らせID
	 */
	@ResponseBody
	@RequestMapping("/deleteNews")
	public void deleteNews(@RequestBody PostedNewsForm form) {
		deleteNewsService.deleteNews(form);
	}
	
	
}
