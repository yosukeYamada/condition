package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.PostedNews;
import com.example.domain.Status;
import com.example.form.PostedNewsForm;
import com.example.mapper.PostedNewsMapper;

/**
 * お知らせ投稿の登録を行うサービスクラス
 * 
 * @author sakai
 *
 */
@Service
public class RegisterNewsService {

	@Autowired
	private PostedNewsMapper postedNewsMapper;

	/**
	 * お知らせの投稿を行う
	 * 
	 * @param form 投稿内容
	 * @return お知らせ投稿一覧
	 */
	public List<PostedNews> PostedNews(PostedNewsForm form) {
		Timestamp tsDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
		Integer userId = form.getUserId();
		String newsComment = form.getNewsComment();
		PostedNews postedNews = new PostedNews();
		postedNews.setNewsDate(tsDate);
		postedNews.setNewsComment(newsComment);
		postedNews.setUserId(userId);
		postedNews.setRegisterUserId(userId);
		postedNews.setRegisterDate(tsDate);
		postedNews.setVersion(1);
		postedNews.setStatus(Status.AVAILABLE.getStatusId());
		postedNewsMapper.save(postedNews); // posted_newsテーブルに挿入
		List<PostedNews> PostedNewsList = postedNewsMapper.findAll();
		return PostedNewsList;
	}

}
