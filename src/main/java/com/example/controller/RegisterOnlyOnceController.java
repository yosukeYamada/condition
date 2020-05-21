package com.example.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.example.domain.DailyPost;
import com.example.service.RegisterOnlyOnceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 投稿回数を1日1回に制限するコントローラー.
 * 
 * @author riho.ikeda
 */
@RestController
public class RegisterOnlyOnceController {

	@Autowired
	private RegisterOnlyOnceService registerOnlyOnceService;

	/**
	 * 引数で受け取ったユーザーが今日既に投稿を行ったか確認を行うメソッド
	 * 
	 * @param param ユーザーID
	 * @return 未投稿:null / 投稿済:投稿情報
	 */
	@RequestMapping("/registerLimit")
	public DailyPost registerLimit(@RequestBody Map<String, String> param) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = sdf.format(timestamp);
		return registerOnlyOnceService.registerLimit(Integer.parseInt(param.get("userId")), date);
	}
}