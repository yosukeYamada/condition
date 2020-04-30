package com.example.controller;

import com.example.form.RegisterDailyPostForm;
import com.example.service.RegisterDailyPostServise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
<<<<<<< HEAD
	/**
	 * 毎日のコンディション登録を行うメソッド
	 * 呼び出し方：http://localhost:8080/registerDailyPost
	 * 
	 * @param form 投稿内容
	 */
	@CrossOrigin(origins = "http://localhost:8888")
	@RequestMapping("/registerDailyPost")
	public void registerDailyPost(@RequestBody RegisterDailyPostForm form) {
=======
	@Autowired
	private RegisterDailyPostServise registerDailyPostServise;
	@CrossOrigin(origins="http://localhost:8888")
	@RequestMapping(value="/registerDailyPost", method = {RequestMethod.POST})
	public void registerDailyPost(@RequestBody RegisterDailyPostForm form) {	
		// フォームの値をサービスに渡す
		System.out.println("動いてる");
		System.out.println(form);
>>>>>>> log
		registerDailyPostServise.registerDailyPost(form);
	}

}
