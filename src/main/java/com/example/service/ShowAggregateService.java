package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.domain.DailyPost;
import com.example.domain.DailyScore;
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
    public Map<String, DailyScore> showAggregate() {
        LocalDateTime ldt = LocalDateTime.of(2020, 4, 27, 9, 0, 0); // 日付を生成
        Timestamp ts = Timestamp.valueOf(ldt); // LocalDateTime→Timestamp
        List<DailyPost> todaysPostList = dailyPostMapper.findByDate(ts);
        Map<String, DailyScore> resultMap = new HashMap<>();
        DailyScore motivationScore = new DailyScore();
        DailyScore conditionScore = new DailyScore();
        DailyScore performanceScore = new DailyScore();
        for (DailyPost post : todaysPostList) {
            switch (post.getPostedMotivation().getMotivation().getMotivationName()) {
                case "快晴":
                    motivationScore.setClearCount(motivationScore.getClearCount() + 1);
                    break;
                case "晴":
                    motivationScore.setSunnyCount(motivationScore.getSunnyCount() + 1);
                    break;
                case "曇":
                    motivationScore.setCloudyCount(motivationScore.getClearCount() + 1);
                    break;
                case "雨":
                    motivationScore.setRainyCount(motivationScore.getRainyCount() + 1);
                    break;
                case "嵐":
                    motivationScore.setStormyCount(motivationScore.getStormyCount() + 1);
                    break;
            }
            switch (post.getPostedCondition().getCondition().getConditionName()) {
                case "快晴":
                    conditionScore.setClearCount(conditionScore.getClearCount() + 1);
                    break;
                case "晴":
                    conditionScore.setSunnyCount(conditionScore.getSunnyCount() + 1);
                    break;
                case "曇":
                    conditionScore.setCloudyCount(conditionScore.getClearCount() + 1);
                    break;
                case "雨":
                    conditionScore.setRainyCount(conditionScore.getRainyCount() + 1);
                    break;
                case "嵐":
                    conditionScore.setStormyCount(conditionScore.getStormyCount() + 1);
                    break;
            }
            switch (post.getPostedPerformance().getPerformance().getPerformanceName()) {
                case "快晴":
                    performanceScore.setClearCount(performanceScore.getClearCount() + 1);
                    break;
                case "晴":
                    performanceScore.setSunnyCount(performanceScore.getSunnyCount() + 1);
                    break;
                case "曇":
                    performanceScore.setCloudyCount(performanceScore.getClearCount() + 1);
                    break;
                case "雨":
                    performanceScore.setRainyCount(performanceScore.getRainyCount() + 1);
                    break;
                case "嵐":
                    performanceScore.setStormyCount(performanceScore.getStormyCount() + 1);
                    break;
            }
        }
        motivationScore.setPercentage(todaysPostList.size());
        conditionScore.setPercentage(todaysPostList.size());
        performanceScore.setPercentage(todaysPostList.size());
        resultMap.put("motivation", motivationScore);
        resultMap.put("condition", conditionScore);
        resultMap.put("performance", performanceScore);
        return resultMap;
    }

}