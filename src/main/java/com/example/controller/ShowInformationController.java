package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.response.Form;
import com.example.service.ShowInformationService;

/**
 * インフォメーション一覧を取得するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
public class ShowInformationController {

	@Autowired
	private ShowInformationService informationService;

	/**
	 * インフォメーション一覧を取得する.
	 * 
	 * @return インフォメーション一覧
	 */
	@RequestMapping("/information")
	public Form findAll() {
		return informationService.findAll();
	}

}
