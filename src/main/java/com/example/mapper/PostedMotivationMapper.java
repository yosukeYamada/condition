package com.example.mapper;

import com.example.domain.PostedMotivation;

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
}