package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.example.domain.DailyPost;
import com.example.mapper.DailyPostMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 投稿内容の集計を行うサービスクラス
 * 
 * @author yuichiyasui
 */
@Service
@Transactional
public class ShowAggregateService {

    @Autowired
    private DailyPostMapper dailyPostMapper;

    /**
     * 投稿の集計を行うメソッド
     * 
     * @return 集計結果
     */
    public List<DailyPost> showAggregate() {
        LocalDateTime ldt = LocalDateTime.of(2020, 4, 27, 9, 0, 0); // 日付を生成
        Timestamp ts = Timestamp.valueOf(ldt); // LocalDateTime→Timestamp
        System.out.println(dailyPostMapper.findByDate(ts));
        return dailyPostMapper.findAll();
    }

}