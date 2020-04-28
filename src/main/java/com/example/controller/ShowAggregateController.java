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
 * @author yuichiyasui
 */
@RestController
public class ShowAggregateController {

    @Autowired
    private ShowAggregateService showAggregateService;

    @ResponseBody
    @RequestMapping("/showAggregate")
    public Map<String, DailyScore> showAggregate() {
        return showAggregateService.showAggregate();
    }

}
