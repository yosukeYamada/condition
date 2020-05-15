package com.example.mapper;

import java.util.List;

import com.example.domain.PostedNews;

import org.apache.ibatis.annotations.Mapper;

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
	
	
    /**
     * お知らせIDで検索して投稿を取得するメソッド.
     * 
     * @param newsId お知らせID
     * @return お知らせIDに紐づく投稿内容
     */
    public List<PostedNews> findAll();
	
}
