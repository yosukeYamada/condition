package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Information;
import com.example.domain.response.Form;
import com.example.form.RegisterInformationForm;
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
	
	/**
	 * インフォメーションを登録する.
	 * 
	 * @param form form
	 * @return インフォメーション一覧
	 */
	@RequestMapping("/insert")
	public List<Information> insert(@RequestBody RegisterInformationForm form) {
		
		return informationService.insert(form);
	}
}
