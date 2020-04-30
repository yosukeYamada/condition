package com.example.controller;

import java.util.List;

import com.example.domain.User;
import com.example.service.ShowEmployeeListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * 
	 * @return 従業員一覧
	 */
	@CrossOrigin(origins="http://localhost:8888") // TODO URLドメインをデプロイ時に変更
	@ResponseBody
	@RequestMapping("/showEmployeeList")
	public List<User> showEmployeeList() {
		String date = "2020/04/27";
		return showEmployeeListService.showEmployee(date);
	}

}
