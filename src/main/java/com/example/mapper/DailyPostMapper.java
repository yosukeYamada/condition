package com.example.mapper;

import java.sql.Timestamp;
import java.util.List;

import com.example.domain.DailyPost;

import org.apache.ibatis.annotations.Mapper;

/**
 * daily_postsテーブルを操作するマッパー
 * 
 * @author yuichiyasui
 */
@Mapper
public interface DailyPostMapper {

    /**
     * 毎日の投稿の挿入を行うメソッド.
     * 
     * @param dailyPost 挿入する毎日の投稿情報
     * @return 挿入したdailyPostID
     */
    public Integer save(DailyPost dailyPost);

    /**
     * 年月日で検索して投稿を取得するメソッド.
     * 
     * @return 引数で受け取った年月日に合致する投稿一覧
     */
    public List<DailyPost> findByDate(Timestamp startTs, Timestamp endTs);
    
    
    /**
     * ユーザIDで検索して投稿を取得するメソッド.
     * 
     * @param userId ユーザID
     * @return ユーザIDに紐づく投稿内容
     */
    public List<DailyPost> findByUserId(Integer userId);

}