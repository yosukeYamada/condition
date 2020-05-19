package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Information;
import com.example.form.RegisterInformationForm;
import com.example.service.RegisterInformationService;

/**
 * インフォメーションを登録するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/information")
public class RegisterInformationController {

	@Autowired
	private RegisterInformationService informationService;
	
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
