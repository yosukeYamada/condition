package com.example.controller;

import com.example.domain.DailyPost;
import com.example.form.RegisterDailyPostForm;
import com.example.service.RegisterDailyPostServise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 毎日のコンディション登録を行うコントローラークラス
 * 
 * @author ryoheisuzuki
 */
@RestController
public class RegisterDailyPostController {

	@Autowired
	private RegisterDailyPostServise registerDailyPostServise;

	public RegisterDailyPostForm setUpForm() {
		return new RegisterDailyPostForm();
	}

	/**
	 * 毎日のコンディション登録を行うメソッド
	 * 
	 * @apiNote 呼び出し方：http://localhost:8080/registerDailyPost
	 * @param form 投稿内容
	 */
	@RequestMapping("/registerDailyPost")
	public List<DailyPost> registerDailyPost(@RequestBody RegisterDailyPostForm form) {
		List<DailyPost> DailyPostList= registerDailyPostServise.registerDailyPost(form);
		System.err.println(DailyPostList);
		return  DailyPostList;
	}
}