package com.example.mapper;

import com.example.domain.PostedMotivation;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
/**
 * posted_motivationsテーブルを操作するマッパー
 * 
 * @author yuichiyasui
 */
@Mapper
public interface PostedMotivationMapper {

    /**
     * モチベーション情報の挿入を行うメソッド
     * 
     * @param postedMotivation 投稿されたモチベーション情報
     */
    public void save(PostedMotivation postedMotivation);
    
    public void update(Integer dailyPostId,Integer updateUserId,Integer motivationId,Timestamp updateDate);
    
    
    //テスト用
    public void insertPostedMotivation();
}