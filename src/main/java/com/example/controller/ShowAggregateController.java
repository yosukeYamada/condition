package com.example.controller;

import java.util.Map;

import com.example.domain.DailyScore;
import com.example.service.AggregateByDayService;
import com.example.service.AggregateByMonthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    private AggregateByMonthService aggregateByMonthService;

    /**
     * 日別グラフの集計結果を返すメソッド.
     * 
     * @param date 日付('yyyy/MM/dd')
     * @return 引数で受け取った日の集計結果
     */
    @ResponseBody
    @RequestMapping("/getAggregateByDay")
    public Map<String, DailyScore> getAggregateByDay(@RequestBody Map<String, String> param) {
        String date = param.get("date").replace("-", "/");
        return aggregateByDayService.aggregateByDay(date);
    }

    /**
     * 月別グラフの集計結果を返すメソッド
     * 
     * @param date 日付('yyyy/MM/dd')
     * @return 引数で受け取った日付の月の集計結果
     */
    @ResponseBody
    @RequestMapping("/getAggregateByMonth")
    public Map<String, Map<String, DailyScore>> getAggregateByMonth(@RequestBody Map<String, String> param) {
        String date = param.get("date").replace("-", "/");
        return aggregateByMonthService.aggregateByMonth(date);
    }
}