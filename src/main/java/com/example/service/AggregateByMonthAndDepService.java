package com.example.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DailyPost;
import com.example.domain.DailyScore;
import com.example.domain.User;
import com.example.mapper.UserMapper;

/**
 * 月別と部署別で投稿内容の集計を行うサービスクラス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class AggregateByMonthAndDepService {

	@Autowired
	private UserMapper userMapper;
	
	 /**
     * 引数で受け取った年月の投稿の集計を行うメソッド
     * 
     * @param date 日付('yyyy/MM/dd')
     * @return 集計結果
     */
    public Map<String, Map<String, DailyScore>> aggregateByMonthAndDep(String date, Integer depId) {
        LocalDate arg = stringToLocalDate(date); // 引数で受け取ったyyyy/MM/ddをLocalDateに変換
        LocalDateTime startLdt = LocalDateTime.of(2020, arg.getMonth(), 1, 0, 0, 0); // 検索の開始日時を生成(月初)
        LocalDateTime endLdt = LocalDateTime.of(2020, arg.getMonth(), arg.lengthOfMonth(), 23, 59, 59); // 検索の終了日時を生成(月末)
        Timestamp startTs = Timestamp.valueOf(startLdt); // LocalDateTime→Timestamp
        Timestamp endTs = Timestamp.valueOf(endLdt); // LocalDateTime→Timestamp
        List<User> employeeListByMonthAndDep = userMapper.findByDayAndDep(startTs, endTs, depId);
        
        Map<String, Map<String, DailyScore>> resultMap = new LinkedHashMap<>();
        for (int i = 0; i < endLdt.getDayOfMonth(); i++) {
            Map<String, DailyScore> dayMap = new HashMap<>();
            DailyScore motivationScore = new DailyScore();
            DailyScore conditionScore = new DailyScore();
            DailyScore performanceScore = new DailyScore();
            dayMap.put("motivation", motivationScore);
            dayMap.put("condition", conditionScore);
            dayMap.put("performance", performanceScore);
            String key = startLdt.getYear() + "/" + String.format("%02d", startLdt.getMonthValue()) + "/"
                    + String.format("%02d", startLdt.getDayOfMonth() + i);
            resultMap.put(key, dayMap);
        }
        
        List<DailyPost> monthPostByDayAndDep = new ArrayList<>();
        for(User user : employeeListByMonthAndDep) {
        	monthPostByDayAndDep = user.getDailyPost();
        }
        
        for (DailyPost post : monthPostByDayAndDep) {
            // TimestampをString型に変換してyyyy/MM/ddにしてreturnMapの日付と合致するキーの中に計算した結果を入れる
            Map<String, DailyScore> dayMap = resultMap.get(timestampToString(post.getDate()));
            switch (post.getPostedMotivation().getMotivation().getMotivationName()) {
                case "快晴":
                    dayMap.get("motivation").setClearCount(dayMap.get("motivation").getClearCount() + 1);
                    break;
                case "晴":
                    dayMap.get("motivation").setSunnyCount(dayMap.get("motivation").getSunnyCount() + 1);
                    break;
                case "曇":
                    dayMap.get("motivation").setCloudyCount(dayMap.get("motivation").getClearCount() + 1);
                    break;
                case "雨":
                    dayMap.get("motivation").setRainyCount(dayMap.get("motivation").getRainyCount() + 1);
                    break;
                case "嵐":
                    dayMap.get("motivation").setStormyCount(dayMap.get("motivation").getStormyCount() + 1);
                    break;
            }
            switch (post.getPostedCondition().getCondition().getConditionName()) {
                case "快晴":
                    dayMap.get("condition").setClearCount(dayMap.get("condition").getClearCount() + 1);
                    break;
                case "晴":
                    dayMap.get("condition").setSunnyCount(dayMap.get("condition").getSunnyCount() + 1);
                    break;
                case "曇":
                    dayMap.get("condition").setCloudyCount(dayMap.get("condition").getClearCount() + 1);
                    break;
                case "雨":
                    dayMap.get("condition").setRainyCount(dayMap.get("condition").getRainyCount() + 1);
                    break;
                case "嵐":
                    dayMap.get("condition").setStormyCount(dayMap.get("condition").getStormyCount() + 1);
                    break;
            }
            switch (post.getPostedPerformance().getPerformance().getPerformanceName()) {
                case "快晴":
                    dayMap.get("performance").setClearCount(dayMap.get("performance").getClearCount() + 1);
                    break;
                case "晴":
                    dayMap.get("performance").setSunnyCount(dayMap.get("performance").getSunnyCount() + 1);
                    break;
                case "曇":
                    dayMap.get("performance").setCloudyCount(dayMap.get("performance").getClearCount() + 1);
                    break;
                case "雨":
                    dayMap.get("performance").setRainyCount(dayMap.get("performance").getRainyCount() + 1);
                    break;
                case "嵐":
                    dayMap.get("performance").setStormyCount(dayMap.get("performance").getStormyCount() + 1);
                    break;
            }
            dayMap.get("motivation").setScoreAverage();
            dayMap.get("condition").setScoreAverage();
            dayMap.get("performance").setScoreAverage();
        }
        return resultMap;
    }

    /**
     * 日付をStringからLocalDateTimeに変換するメソッド
     * 
     * @param date 日付('yyyy/MM/dd')
     * @return LocalDateTimeに変換した日付
     */
    public LocalDate stringToLocalDate(String date) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return LocalDate.parse(date, dtf);
        } catch (Exception e) {
            /** 変換に失敗した場合は今日の日付を返す */
            e.printStackTrace();
            return LocalDate.now();
        }

    }

    /**
     * 日付をTimestamp型からString型に変換するメソッド
     * 
     * @param date Timestamp型の日付
     * @return String型のyyyy/MM/ddに変換した日付
     */
    public String timestampToString(Timestamp date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);

    }
    
}