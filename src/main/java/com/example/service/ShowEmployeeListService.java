package com.example.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Condition;
import com.example.domain.DailyPost;
import com.example.domain.User;
import com.example.mapper.UserMapper;

/**
 * 
 * 管理者側の従業員一覧画面のサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class ShowEmployeeListService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * ユーザー情報を取得する.
	 * 
	 * @return
	 */
	public List<User> showEmployee() {
		/**
		 * employeeListに従業員一覧画面に必要な、
		 * userName,depName,hireDate,condition,motivation,performance,commentをつめる
		 */

		List<User> employeeList = new ArrayList<User>();

		List<User> findAllList = userMapper.findAllAndDailyPost();
		
		
		User user = new User();
		DailyPost dailyPost = new DailyPost();
		List<DailyPost> dailyPostList = new ArrayList<DailyPost>();

		for (User users : findAllList) {
			user.setUserName(users.getUserName());
			for (DailyPost dailyPosts : users.getDailyPost()) {
				dailyPost.setPostedComment(dailyPosts.getPostedComment());
				Condition condition = new Condition();

				condition.setConditionName(dailyPosts.getPostedCondition().getCondition().getConditionName());
				dailyPost.setPostedCondition(dailyPosts.getPostedCondition());

			}
			dailyPostList.add(dailyPost);
			user.setDailyPost(dailyPostList);
		}
		employeeList.add(user);

		return employeeList;
	}

}
