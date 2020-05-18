package com.example.mapper;

import com.example.domain.PostedPerformance;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;

/**
 * posted_performancesテーブルを操作するマッパー
 * 
 * @author yuichiyasui
 */
@Mapper
public interface PostedPerformanceMapper {

    /**
     * 成果情報の挿入を行うメソッド
     * 
     * @param postedPerformance 投稿された成果情報
     */
    public void save(PostedPerformance postedPerformance);
    
    public void update(Integer dailyPostId,Integer updateUserId,Integer performanceId,Timestamp updateDate);
    
    
    //テスト用
    public void insertPostedPerformance();

}