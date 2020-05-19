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
     * お知らせ投稿一覧を取得するメソッド.
     * 
     * @return お知らせ投稿一覧
     */
    public List<PostedNews> findAll();
	
    
    /**
     * お知らせを削除するメソッド
     * 
     * @param postedNews お知らせID
     */
    public void deleteByNewsId(PostedNews postedNews);
    
}
