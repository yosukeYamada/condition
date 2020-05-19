package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.form.UpdateUserForm;
import com.example.mapper.MailMapper;
import com.example.mapper.UserMapper;

/**
 * ユーザー登録を更新するサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class UpdateUserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MailMapper mailMapper;

	/**
	 * ユーザー情報の更新の際にversionを確認するため取得する.
	 * 
	 * @param userId 更新情報
	 * @return version
	 */
	public Integer findVersion(Integer userId) {
		return userMapper.findVersion(userId);

	}

	/**
	 * ユーザー情報の更新をusersとmailsのテーブルにUPDATEする.
	 * 
	 * @param form 更新情報
	 * @return 全従業員のユーザー情報
	 */
	public List<User> updateUser(UpdateUserForm form) {

		User user = new User();

		StringBuilder bldHireDate = new StringBuilder();
		bldHireDate.append(form.getHireYear());
		bldHireDate.append("-");
		bldHireDate.append(form.getHireMonth());
		bldHireDate.append("-01 00:00:00");
		Timestamp hireDate = java.sql.Timestamp.valueOf(bldHireDate.toString());

		Timestamp updateDate = new Timestamp(System.currentTimeMillis());

		user.setUserId(form.getUserId());
		user.setUserName(form.getUserName());
		user.setUserNameKana(form.getUserNameKana());
		user.setDepId(form.getIntDepId());
		user.setHireDate(hireDate);
		user.setUpdateDate(updateDate);
		user.setUpdateUserId(form.getUpdateUserId());

		userMapper.updateUser(user);

		Mail mail = new Mail();

		mail.setUserId(form.getUserId());
		mail.setMailName(form.getMailAddress());
		mail.setUpdateDate(updateDate);
		mail.setUpdateUserId(form.getUpdateUserId());

		mailMapper.updateMail(mail);

		List<User> employeeList = userMapper.findAllAndDailyPost();
		return employeeList;

	}

}
