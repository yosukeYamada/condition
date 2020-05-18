package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.PostedNews;
import com.example.form.PostedNewsForm;
import com.example.mapper.PostedNewsMapper;

/**
 * お知らせ投稿の削除を行うサービスクラス
 * 
 * @author sakai
 *
 */
@Service
public class DeleteNewsService {

	@Autowired
	private PostedNewsMapper postedNewsMapper;

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
