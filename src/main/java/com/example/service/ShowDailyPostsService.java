package com.example.service;

import java.util.List;

import com.example.domain.DailyPost;
import com.example.mapper.DailyPostMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 引数で指定したユーザーの毎日の投稿を表示するサービスクラス
 * 
 * @author yuichiyasui
 */
@Service
@Transactional
public class ShowDailyPostsService {

	@Autowired
	private DailyPostMapper dailyPostMapper;

	/**
	 * 引数で指定したユーザーの毎日の投稿一覧を取得するメソッド
	 * 
	 * @param userId ユーザーID
	 * @return 投稿一覧
	 */
	public List<DailyPost> getDailyPostList(Integer userId) {
		List<DailyPost> dailyPostList = dailyPostMapper.findByUserId(userId);
		return dailyPostList;
	}
}