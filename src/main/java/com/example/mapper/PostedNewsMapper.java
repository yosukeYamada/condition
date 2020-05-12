package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.PostedNews;

/**
 * posted_newsテーブルを操作するマッパー.
 * 
 * @author sakai
 *
 */
@Mapper
public interface PostedNewsMapper {

	/**
	 * お知らせ内容の挿入を行うメソッド.
	 * 
	 * @param postedNews 投稿されたお知らせ
	 */
	public void save(PostedNews postedNews);
	
}
