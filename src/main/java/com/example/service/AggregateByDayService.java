package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
 * 日別で投稿内容の集計を行うサービスクラス
 * 
 * @author yuichiyasui
 */
@Service
@Transactional
public class AggregateByDayService {

    @Autowired
    private DailyPostMapper dailyPostMapper;

    /**
     * 引数で受け取った日付の投稿の集計を行うメソッド
     * @param date 日付('yyyy/MM/dd')
     * @return 集計結果
     */
    public Map<String, DailyScore> aggregateByDay(String date) {
        LocalDateTime arg = stringToLocalDateTime(date); // 引数で受け取ったyyyy/MM/ddをLocalDateTimeに変換
        LocalDateTime startLdt = LocalDateTime.of(2020, arg.getMonth(), arg.getDayOfMonth(), 0, 0, 0); // 検索の開始日時を生成
        LocalDateTime endLdt = LocalDateTime.of(2020, arg.getMonth(), arg.getDayOfMonth(), 23, 59, 59); // 検索の終了日時を生成
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
                    motivationScore.setCloudyCount(motivationScore.getCloudyCount() + 1);
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
                    conditionScore.setCloudyCount(conditionScore.getCloudyCount() + 1);
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
                    performanceScore.setCloudyCount(performanceScore.getCloudyCount() + 1);
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

    /**
     * 日付をStringからLocalDateTimeに変換するメソッド
     * 
     * @param date 日付('yyyy/MM/dd')
     * @return LocalDateTimeに変換した日付
     */
    public LocalDateTime stringToLocalDateTime(String date) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return LocalDate.parse(date, dtf).atTime(LocalTime.MIN);
        } catch (Exception e) {
            /** 変換に失敗した場合は今日の日付を返す */
            e.printStackTrace();
            return LocalDateTime.now();
        }
    }

}