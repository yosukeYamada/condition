package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.Condition;
import com.example.domain.DailyPost;
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
	 * @param date 日付('yyyy/MM/dd')
	 * @return 引数で受け取った日付の投稿を含む従業員一覧
	 */
	public List<User> showEmployee(String date) {
		LocalDateTime arg = stringToLocalDateTime(date); // 引数で受け取ったyyyy/MM/ddをLocalDateTimeに変換
		LocalDateTime startLdt = LocalDateTime.of(2020, arg.getMonth(), arg.getDayOfMonth(), 0, 0, 0); // 検索の開始日時を生成
		LocalDateTime endLdt = LocalDateTime.of(2020, arg.getMonth(), arg.getDayOfMonth(), 23, 59, 59); // 検索の終了日時を生成
		Timestamp startTs = Timestamp.valueOf(startLdt); // LocalDateTime→Timestamp
		Timestamp endTs = Timestamp.valueOf(endLdt); // LocalDateTime→Timestamp
		List<User> findAllList = userMapper.findAllAndDailyPost(); // TODO 日付の引数を渡すように変更する

		// employeeListに従業員一覧画面に必要な、userName,depName,hireDate,condition,motivation,performance,commentをつめる
		List<User> employeeList = new ArrayList<User>();
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

	/**
	 * 日付をStringからLocalDateTimeに変換するメソッド
	 * 
	 * @param date 日付('yyyy/MM/dd')
	 * @return LocalDateTimeに変換した日付
	 */
	public LocalDateTime stringToLocalDateTime(String date) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			return LocalDate.parse(date, dtf).atTime(LocalTime.MIN);
		} catch (Exception e) {
			/** 変換に失敗した場合は今日の日付を返す */
			e.printStackTrace();
			return LocalDateTime.now();
		}
	}

}
