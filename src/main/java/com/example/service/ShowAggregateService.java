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
     * 投稿の集計を行うメソッド TODO フロントができたら引数を受け取る場合の実装を行う
     * 
     * @return 集計結果
     */
    public Map<String, DailyScore> showAggregate() {
        // LocalDateTime startLtd = LocalDateTime.now().with(LocalTime.of(0, 0)); //
        // 今日の0時0分を生成
        // LocalDateTime endLtd = LocalDateTime.now().with(LocalTime.of(23, 59)); //
        // 今日の23時59分を生成
        LocalDateTime startLdt = LocalDateTime.of(2020, 4, 27, 0, 0, 0); // TODO 検索の開始日時を生成
        LocalDateTime endLdt = LocalDateTime.of(2020, 4, 27, 23, 59, 59); // TODO 検索の終了日時を生成
        Timestamp startTs = Timestamp.valueOf(startLdt); // LocalDateTime→Timestamp
        Timestamp endTs = Timestamp.valueOf(endLdt); // LocalDateTime→Timestamp
        List<DailyPost> todaysPostList = dailyPostMapper.findByDate(startTs, endTs);
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