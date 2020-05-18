package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.PostedNews;
import com.example.mapper.PostedNewsMapper;

/**
 * お知らせ投稿取得を行うサービスクラス
 * 
 * @author sakai
 *
 */
@Service
public class ShowNewsListService {

	@Autowired
	private PostedNewsMapper postedNewsMapper;

	/**
	 * お知らせ投稿一覧を取得する.
	 * 
	 * @return お知らせ投稿一覧
	 */
	public List<PostedNews> showNewsPostList() {
		return postedNewsMapper.findAll();
	}

}
