package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DailyScore;
import com.example.service.AggregateByDayAndDepService;
import com.example.service.AggregateByDayService;
import com.example.service.AggregateByMonthAndDepService;
import com.example.service.AggregateByMonthService;

/**
 * 集計表示画面を表示するコントローラー.
 * 
 * @author yuichiyasui
 */
@RestController
public class ShowAggregateController {

    @Autowired
    private AggregateByDayService aggregateByDayService;
    @Autowired
    private AggregateByDayAndDepService aggregateByDayAndDepService;
    @Autowired
    private AggregateByMonthService aggregateByMonthService;
    @Autowired
    private AggregateByMonthAndDepService aggregateByMonthAndDepService;

    /**
     * 日別グラフの集計結果を返すメソッド.
     * 
     * @param date 日付('yyyy/MM/dd')
     * @return 引数で受け取った日の集計結果
     */
    @RequestMapping("/getAggregateByDay")
    public Map<String, DailyScore> getAggregateByDay(@RequestBody Map<String, String> param) {
    	
    	System.err.println("日付 : " + param.get("date"));
    	System.err.println("dep : " + param.get("depId"));
    	
    	String date = param.get("date").replace("-", "/");
    	
        if(Integer.parseInt(param.get("depId")) == 0 || param.get("depId") == "") {
        	
        	System.out.println("全従業員");
        	return aggregateByDayService.aggregateByDay(date);
        	
        } else {
        	
        	System.out.println("部署");
        	return aggregateByDayAndDepService.aggregateByDateAndDep(date, Integer.parseInt(param.get("depId")));
        }
    }

    /**
     * 月別グラフの集計結果を返すメソッド
     * 
     * @param date 日付('yyyy/MM/dd')
     * @return 引数で受け取った日付の月の集計結果
     */
    @RequestMapping("/getAggregateByMonth")
    public Map<String, Map<String, DailyScore>> getAggregateByMonth(@RequestBody Map<String, String> param) {
    	
    	System.out.println("月付 : " + param.get("date"));
    	System.out.println("dep : " + param.get("depId"));
        
    	String date = param.get("date").replace("-", "/");
        
        Integer depId = Integer.parseInt(param.get("depId"));
        
        if(Integer.parseInt(param.get("depId")) == 0 || param.get("depId") == "") {
        	
        	return aggregateByMonthService.aggregateByMonth(date);
        	
        } else {
        	
        	return aggregateByMonthAndDepService.aggregateByMonthAndDep(date, depId);
        
        }
    }
}