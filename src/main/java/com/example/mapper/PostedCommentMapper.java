package com.example.mapper;

import com.example.domain.PostedComment;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;

/**
 * posted_commentsテーブルを操作するマッパー
 * 
 * @author yuichiyasui
 */
@Mapper
public interface PostedCommentMapper {

    /**
     * コメントの挿入を行うメソッド
     * 
     * @param postedComment 投稿されたコメント
     */
    public void save(PostedComment postedComment);
    
    public void update(Integer dailyPostId,Integer updateUserId,String comment,Timestamp updateDate);

}