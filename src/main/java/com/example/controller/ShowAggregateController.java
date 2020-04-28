package com.example.controller;

import java.util.Map;

import com.example.domain.DailyScore;
import com.example.service.ShowAggregateService;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ShowAggregateService showAggregateService;

    /**
     * 日別グラフと月別グラフの初期表示を行うメソッド.
     * 
     * @return 当日及び当月の集計結果
     */
    @ResponseBody
    @RequestMapping("/showAggregate")
    public Map<String, DailyScore> showAggregate() {
        return showAggregateService.showAggregate();
    }

    /**
     * 引数で受け取った日付の内容にグラフの切り替えを行うメソッド.
     * TODO 月別グラフの実装
     * @param date セレクトボックスで選択された日付
     * @return 引数で受け取った日付の集計結果
     */
    // @ResponseBody
    // @RequestMapping("/changeDailyChart")
    // public Map<String, DailyScore> changeDailyChart(@RequestBody String date) {
    //     Map<String, DailyScore> responseMap = new HashMap<>();
    //     return responseMap;
    // }

}
