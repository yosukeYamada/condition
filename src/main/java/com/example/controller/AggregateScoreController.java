package com.example.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DailyPost;
import com.example.domain.DailyScore;
import com.example.domain.User;
import com.example.domain.response.ScoreCount;
import com.example.mapper.UserMapper;

/**
 * ラクスカル君からの総評のための点数集計コントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/showScore")
public class AggregateScoreController {
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * ラクスカル君からの総評のための点数集計する.
	 * 
	 * @return 点数
	 */
	@RequestMapping("")
	public ScoreCount showScore(@RequestBody Map<String, String> param) {
		
		System.err.println("userID : " + param.get("userId"));
		
		ScoreCount count = new ScoreCount();
		
		// 現在日付を取得
		LocalDate now = LocalDate.now();
		
		/* ---------- 先週の集計結果を返す ---------- */

        // 現在日の週の月曜日を取得
        LocalDate mon = now.with(DayOfWeek.MONDAY);
        
        // 現在日の週の日曜日を取得
        LocalDate fri = now.with(DayOfWeek.FRIDAY);
		
        //現在の週の1つ前の月曜日をと金曜日を取得
        mon = mon.minusDays(7);
        fri = fri.minusDays(7);
        
        //LocalDate→LocalDateTime
        LocalDateTime monday = mon.atTime(0, 0, 0);
        LocalDateTime friday = fri.atTime(23, 59, 59);
        
        //LocalDateTime→Timestamp
        Timestamp startMon = Timestamp.valueOf(monday);
        Timestamp endFri = Timestamp.valueOf(friday);
        
		List<User> myDailyPostLastWeekList = userMapper.findByUserIdAndLastWeek(startMon,endFri,Integer.parseInt(param.get("userId")));
		
        DailyScore motivationScore = new DailyScore();
        DailyScore conditionScore = new DailyScore();
        DailyScore performanceScore = new DailyScore();
        
        List<DailyPost> dailyPostByDayAndDep = new ArrayList<>();
        for(User user : myDailyPostLastWeekList) {
        	dailyPostByDayAndDep = user.getDailyPost();
        }
        
        
        for (User user : myDailyPostLastWeekList) {
        	for(DailyPost post : user.getDailyPost()) {
	            switch (post.getPostedMotivation().getMotivation().getMotivationName()) {
	                case "快晴":
	                    motivationScore.setClearCount(motivationScore.getClearCount() + 5);
	                    break;
	                case "晴":
	                    motivationScore.setSunnyCount(motivationScore.getSunnyCount() + 4);
	                    break;
	                case "曇":
	                    motivationScore.setCloudyCount(motivationScore.getClearCount() + 3);
	                    break;
	                case "雨":
	                    motivationScore.setRainyCount(motivationScore.getRainyCount() + 2);
	                    break;
	                case "嵐":
	                    motivationScore.setStormyCount(motivationScore.getStormyCount() + 1);
	                    break;
	            }
	            switch (post.getPostedCondition().getCondition().getConditionName()) {
	                case "快晴":
	                    conditionScore.setClearCount(conditionScore.getClearCount() + 5);
	                    break;
	                case "晴":
	                    conditionScore.setSunnyCount(conditionScore.getSunnyCount() + 4);
	                    break;
	                case "曇":
	                    conditionScore.setCloudyCount(conditionScore.getClearCount() + 3);
	                    break;
	                case "雨":
	                    conditionScore.setRainyCount(conditionScore.getRainyCount() + 2);
	                    break;
	                case "嵐":
	                    conditionScore.setStormyCount(conditionScore.getStormyCount() + 1);
	                    break;
	            }
	            switch (post.getPostedPerformance().getPerformance().getPerformanceName()) {
	                case "快晴":
	                    performanceScore.setClearCount(performanceScore.getClearCount() + 5);
	                    break;
	                case "晴":
	                    performanceScore.setSunnyCount(performanceScore.getSunnyCount() + 4);
	                    break;
	                case "曇":
	                    performanceScore.setCloudyCount(performanceScore.getClearCount() + 3);
	                    break;
	                case "雨":
	                    performanceScore.setRainyCount(performanceScore.getRainyCount() + 2);
	                    break;
	                case "嵐":
	                    performanceScore.setStormyCount(performanceScore.getStormyCount() + 1);
	                    break;
	            }
        	}
        }
        
        motivationScore.setPercentage(dailyPostByDayAndDep.size());
        conditionScore.setPercentage(dailyPostByDayAndDep.size());
        performanceScore.setPercentage(dailyPostByDayAndDep.size());
        
        Integer totalOrderCountOfLastWeek = userMapper.findByUserIdAndLastWeek(startMon,endFri,Integer.parseInt(param.get("userId"))).size();
        Integer maxTotalScoreOfLastWeek = totalOrderCountOfLastWeek * 15;
        Integer maxPartScoreOfLastWeek = totalOrderCountOfLastWeek * 5;
		Integer totalLastWeekMotivationScore = motivationScore.getClearCount() + motivationScore.getSunnyCount() + motivationScore.getCloudyCount() + motivationScore.getRainyCount() + motivationScore.getStormyCount();
		Integer totalLastWeekConditionScore = conditionScore.getClearCount() + conditionScore.getSunnyCount() + conditionScore.getCloudyCount() + conditionScore.getRainyCount() + conditionScore.getStormyCount();
		Integer totalLastWeekPerformanceScore = performanceScore.getClearCount() + performanceScore.getSunnyCount() + performanceScore.getCloudyCount() + performanceScore.getRainyCount() + performanceScore.getStormyCount();
	    
        count.setMaxTotalScoreOfLastWeek(maxTotalScoreOfLastWeek);
        count.setMaxPartScoreOfLastWeek(maxPartScoreOfLastWeek);
		count.setTotalLastWeekMotivationScore(totalLastWeekMotivationScore);
		count.setTotalLastWeekConditionScore(totalLastWeekConditionScore);
		count.setTotalLastWeekPerformanceScore(totalLastWeekPerformanceScore);
		
		Integer totalLastWeekCount = totalLastWeekConditionScore + totalLastWeekMotivationScore + totalLastWeekPerformanceScore;
		count.setTotalLastWeekCount(totalLastWeekCount);
		
		System.out.println("先週の結果" + maxTotalScoreOfLastWeek + maxPartScoreOfLastWeek);
		
		
		/* ---------- 先月の集計結果を返す ---------- */
		
		Integer totalLastMonthCount = 0;
		Integer totalLastMonthMotivationScore = 0;
		Integer totalLastMonthConditionScore = 0;
		Integer totalLastMonthPerformanceScore = 0;
		
		 //前月の最終日を取得
        LocalDate earlier = now.minusMonths(1);
        LocalDate lastDayOfMonth = earlier.with(TemporalAdjusters.lastDayOfMonth());
        
        //前月の最初日を取得
        LocalDate firstDayOfMonth = earlier.with(TemporalAdjusters.firstDayOfMonth());
        
        //LocalDate→LocalDateTime
        LocalDateTime firstDay = firstDayOfMonth.atTime(0, 0, 0);
        LocalDateTime lastDay = lastDayOfMonth.atTime(0, 0, 0);
        
        //LocalDateTime→Timestamp
        Timestamp startFirstDayOfMonth = Timestamp.valueOf(firstDay);
        Timestamp endLastDayOfMonth = Timestamp.valueOf(lastDay);
        
        List<User> myDailyPostLastMonthList = userMapper.findByUserIdAndLastWeek(startFirstDayOfMonth,endLastDayOfMonth,Integer.parseInt(param.get("userId")));
        
        Map<String, Map<String, DailyScore>> resultMap2 = new LinkedHashMap<>();
        for (int i = 0; i < lastDay.getDayOfMonth(); i++) {
            Map<String, DailyScore> dayMap = new HashMap<>();
            dayMap.put("motivation", motivationScore);
            dayMap.put("condition", conditionScore);
            dayMap.put("performance", performanceScore);
            String key = firstDay.getYear() + "/" + String.format("%02d", firstDay.getMonthValue()) + "/"
                    + String.format("%02d", firstDay.getDayOfMonth() + i);
            resultMap2.put(key, dayMap);
        }
        
        List<DailyPost> monthPostByDayAndDep = new ArrayList<>();
        for(User user : myDailyPostLastMonthList) {
        	monthPostByDayAndDep = user.getDailyPost();
        }
        
        for (DailyPost post : monthPostByDayAndDep) {
        	// TimestampをString型に変換してyyyy/MM/ddにしてreturnMapの日付と合致するキーの中に計算した結果を入れる
        	Map<String, DailyScore> dayMap = resultMap2.get(timestampToString(post.getDate()));
            switch (post.getPostedMotivation().getMotivation().getMotivationName()) {
                case "快晴":
                    dayMap.get("motivation").setClearCount(dayMap.get("motivation").getClearCount() + 5);
                    break;
                case "晴":
                    dayMap.get("motivation").setSunnyCount(dayMap.get("motivation").getSunnyCount() + 4);
                    break;
                case "曇":
                    dayMap.get("motivation").setCloudyCount(dayMap.get("motivation").getClearCount() + 3);
                    break;
                case "雨":
                    dayMap.get("motivation").setRainyCount(dayMap.get("motivation").getRainyCount() + 2);
                    break;
                case "嵐":
                    dayMap.get("motivation").setStormyCount(dayMap.get("motivation").getStormyCount() + 1);
                    break;
            }
            switch (post.getPostedCondition().getCondition().getConditionName()) {
                case "快晴":
                    dayMap.get("condition").setClearCount(dayMap.get("condition").getClearCount() + 5);
                    break;
                case "晴":
                    dayMap.get("condition").setSunnyCount(dayMap.get("condition").getSunnyCount() + 4);
                    break;
                case "曇":
                    dayMap.get("condition").setCloudyCount(dayMap.get("condition").getClearCount() + 3);
                    break;
                case "雨":
                    dayMap.get("condition").setRainyCount(dayMap.get("condition").getRainyCount() + 2);
                    break;
                case "嵐":
                    dayMap.get("condition").setStormyCount(dayMap.get("condition").getStormyCount() + 1);
                    break;
            }
            switch (post.getPostedPerformance().getPerformance().getPerformanceName()) {
                case "快晴":
                    dayMap.get("performance").setClearCount(dayMap.get("performance").getClearCount() + 5);
                    break;
                case "晴":
                    dayMap.get("performance").setSunnyCount(dayMap.get("performance").getSunnyCount() + 4);
                    break;
                case "曇":
                    dayMap.get("performance").setCloudyCount(dayMap.get("performance").getClearCount() + 3);
                    break;
                case "雨":
                    dayMap.get("performance").setRainyCount(dayMap.get("performance").getRainyCount() + 2);
                    break;
                case "嵐":
                    dayMap.get("performance").setStormyCount(dayMap.get("performance").getStormyCount() + 1);
                    break;
            }
            totalLastMonthMotivationScore = dayMap.get("motivation").getClearCount() + dayMap.get("motivation").getSunnyCount() + dayMap.get("motivation").getCloudyCount() + dayMap.get("motivation").getRainyCount() + dayMap.get("motivation").getStormyCount();
            totalLastMonthConditionScore = dayMap.get("condition").getClearCount() + dayMap.get("condition").getSunnyCount() + dayMap.get("condition").getCloudyCount() + dayMap.get("condition").getRainyCount() + dayMap.get("condition").getStormyCount();
            totalLastMonthPerformanceScore = dayMap.get("performance").getClearCount() + dayMap.get("performance").getSunnyCount() + dayMap.get("performance").getCloudyCount() + dayMap.get("performance").getRainyCount() + dayMap.get("performance").getStormyCount();
            
        }
        Integer totalOrderCountOfLastMonth = userMapper.findByUserIdAndLastWeek(startFirstDayOfMonth,endLastDayOfMonth,Integer.parseInt(param.get("userId"))).size();
        Integer maxTotalScoreOfLastMonth = totalOrderCountOfLastMonth * 15;
        Integer maxPartScoreOfLastMonth = totalOrderCountOfLastMonth * 5;
        
        
        count.setMaxTotalScoreOfLastMonth(maxTotalScoreOfLastMonth);
        count.setMaxPartScoreOfLastMonth(maxPartScoreOfLastMonth);
        count.setTotalLastMonthMotivationScore(totalLastMonthMotivationScore);
        count.setTotalLastMonthConditionScore(totalLastMonthConditionScore);
        count.setTotalLastMonthPerformanceScore(totalLastMonthPerformanceScore);
        totalLastMonthCount = totalLastMonthMotivationScore + totalLastMonthConditionScore + totalLastMonthPerformanceScore;
        count.setTotalLastMonthCount(totalLastMonthCount);
        return count;
        
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
