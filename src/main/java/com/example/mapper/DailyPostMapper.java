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
     * 年月で検索して投稿を取得するメソッド. TODO 年月検索の実装を行う
     * @return 引数で受け取った年月に合致する投稿一覧
     */
    public List<DailyPost> findByYearAndMonth();
    

    /**
     * 年月日で検索して投稿を取得するメソッド.
     * @return 引数で受け取った年月日に合致する投稿一覧
     */
    public List<DailyPost> findByDate(Timestamp startTs, Timestamp endTs);



}