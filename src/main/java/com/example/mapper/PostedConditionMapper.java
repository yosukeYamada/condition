package com.example.mapper;

import com.example.domain.PostedCondition;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;

/**
 * posted_conditionsテーブルを操作するマッパー
 * 
 * @author yuichiyasui
 */
@Mapper
public interface PostedConditionMapper {

    /**
     * 体調情報の挿入を行うメソッド
     * 
     * @param postedCondition 投稿された体調情報
     */
    public void save(PostedCondition postedCondition);
    
    public void update(Integer dailyPostId,Integer updateUserId,Integer conditionId,Timestamp updateDate);
    
    /**
     * statusを更新するメソッド
     * 
     * @param postedCondition 更新情報
     * @return 最新のバージョン番号
     */
    public Integer updateStatus(PostedCondition postedCondition);

}