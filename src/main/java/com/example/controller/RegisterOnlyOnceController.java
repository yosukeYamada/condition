package com.example.controller;


import java.sql.Timestamp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.RegisterOnlyOnceService;

/**
 * 投稿回数を１日1回に制限するコントローラー.
 * 
 * @author riho.ikeda
 *
 */
@RestController
public class RegisterOnlyOnceController {

	@Autowired
	private RegisterOnlyOnceService registerOnlyOnceService;

	
	@CrossOrigin(origins = "http://localhost:8888")
	@RequestMapping("/registerLimit")
	public Integer registerLimit(@RequestBody Integer userId) {
		Timestamp tsDate  = new Timestamp(System.currentTimeMillis());
		System.out.println(userId);
		return registerOnlyOnceService.registerLimit(userId, tsDate);
	}

}
