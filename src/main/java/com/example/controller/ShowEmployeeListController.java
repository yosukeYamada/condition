package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.service.ShowEmployeeListService;

/**
 * 従業員一覧画面を表示するコントローラー.
 * 
 * @author riho.ikeda
 *
 */
@RestController
public class ShowEmployeeListController {

	@Autowired
	private ShowEmployeeListService showEmployeeListService;

	@ResponseBody
	@RequestMapping("/showEmployeeList")
	public List<User> showEmployeeList() {
		return showEmployeeListService.showEmployee();
	}

}
