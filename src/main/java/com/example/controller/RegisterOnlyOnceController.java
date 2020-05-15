package com.example.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.example.domain.DailyPost;
import com.example.form.RegisterOnlyOnceForm;
import com.example.service.RegisterOnlyOnceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 投稿回数を1日1回に制限するコントローラー.
 * 
 * @author riho.ikeda
 *
 */
@RestController
public class RegisterOnlyOnceController {

	@Autowired
	private RegisterOnlyOnceService registerOnlyOnceService;

	@RequestMapping("/registerLimit")
	public DailyPost registerLimit(@RequestBody RegisterOnlyOnceForm form) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(timestamp);
		return registerOnlyOnceService.registerLimit(form.getUserId(), date);
	}
}