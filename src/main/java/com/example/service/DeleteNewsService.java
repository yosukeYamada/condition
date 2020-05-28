package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.PostedNews;
import com.example.domain.Status;
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
	 * @param form お知らせID,更新ユーザーID
	 */
	public void deleteNews(PostedNewsForm form) {
		 Timestamp tsDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成);
		PostedNews postedNews = new PostedNews();
		postedNews.setNewsId(form.getNewsId());
		postedNews.setUpdateUserId(form.getUserId());
		postedNews.setUpdateDate(tsDate);
		postedNews.setStatus(Status.DELETED.getStatusId());
		postedNewsMapper.deleteByNewsId(postedNews);
	}

}
