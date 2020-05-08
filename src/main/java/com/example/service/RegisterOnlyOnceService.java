package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.DailyPostMapper;

/**
 * 投稿回数を１日1回に制限するサービス.
 * 
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class RegisterOnlyOnceService {

	@Autowired
	private DailyPostMapper dailyPostMapper;

	/**
	 * 投稿回数を１日1回に制限するため、戻り値で判断する.
	 * 
	 * @param userId ユーザーID
	 * @param tsDate 現在の日付
	 * @return 投稿ID
	 */
	public Integer registerLimit(Integer userId, Timestamp tsDate) {
		return dailyPostMapper.findByUserIdAndDate(userId, tsDate);

	}

}
