package com.example.service;

import java.util.List;

import com.example.domain.Status;
import com.example.domain.User;
import com.example.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者側の従業員一覧画面のサービス.
 * 
 * @author riho.ikeda
 */
@Service
@Transactional
public class ShowEmployeeListService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 従業員情報の一覧を取得するメソッド.
	 * 
	 * @return 従業員一覧(ステータスが"利用可能"のデータだけ取得)
	 */
	public List<User> showEmployeeList() {
		Integer userStatus = Status.AVAILABLE.getStatusId();
		Integer mailStatus = Status.AVAILABLE.getStatusId();
		List<User> employeeList = userMapper.findAllAndDailyPost(userStatus, mailStatus);
		return employeeList;
	}
}