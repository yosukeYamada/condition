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
     * 毎日の投稿の編集を行うメソッド.
     * 
     * @param dailyPost 挿入する毎日の投稿情報
     * @return 挿入したdailyPostID
     */
    public Integer edit(DailyPost dailyPost);

    /**
     * 毎日の投稿IDから投稿情報を取得する.
     * 
     * @param dailyPostId 毎日の投稿ID
     * @return 毎日の投稿情報
     */
    public DailyPost findByDailyPostId(Integer dailyPostId);

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

    /**
     * ユーザーIDと日付で投稿情報を取得するメソッド.
     * 
     * @param userId ユーザーID
     * @param tsDate 日付(YYYY/MM/DD)
     * @return 投稿情報
     */
    public DailyPost findByUserIdAndDate(Integer userId, String tsDate);

    /**
     * 投稿内容を更新する.
     * 
     * @param dailyPost 毎日の投稿.
     */
    public void update(Integer dailyPostId, Integer updateUserId, Timestamp updateDate);

    /**
     * 投稿を保留・削除するメソッド.
     * 
     * @param dailyPost 投稿内容のstatus等
     * @return 最新のバージョン
     */
    public void updateDailyPostStatus(DailyPost dailyPost);

    // テスト用
    public void insertDailyPost(Integer userId);

}