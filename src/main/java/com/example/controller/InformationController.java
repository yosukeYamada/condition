package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.response.Form;
import com.example.service.InformationService;

/**
 * インフォメーションに関するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/information")
public class InformationController {

	@Autowired
	private InformationService informationService;
	
	/**
	 * インフォメーション一覧を取得する.
	 * 
	 * @return インフォメーション一覧
	 */
	@RequestMapping("")
	public Form findAll() {
		return informationService.findAll();
	}
}
