package com.example.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.PostedNews;
import com.example.form.PostedNewsForm;
import com.example.mapper.PostedNewsMapper;

/**
 * @author sakai
 *
 */
@Service
public class PostedNewsService {

	@Autowired
	private PostedNewsMapper postedNewsMapper;
	
	/**
	 * @param form
	 * @return
	 */
	public List<PostedNews> PostedNews(PostedNewsForm form){
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
		postedNews.setStatus(1);
		postedNewsMapper.save(postedNews); // posted_newsテーブルに挿入
		
		List<PostedNews> PostedNewsList = postedNewsMapper.findAll();
		return PostedNewsList;
	}
	
	
	/**
	 * お知らせ全件取得を行う
	 * 
	 * @return
	 */
	public List<PostedNews> showNewsPostList() {
		List<PostedNews> PostedNewsList = postedNewsMapper.findAll();
		return PostedNewsList;
	}
	
	/**
	 * お知らせの削除を行う
	 * 
	 * @param form お知らせID
	 */
	public void deleteNews(PostedNewsForm form) {
		PostedNews postedNews = new PostedNews();
		postedNews.setNewsId(form.getNewsId());
		postedNewsMapper.deleteByNewsId(postedNews);
	}
	
}
