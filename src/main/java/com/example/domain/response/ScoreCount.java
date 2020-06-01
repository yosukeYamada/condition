package com.example.domain.response;

import lombok.Data;

@Data
public class ScoreCount {
	private Integer MaxTotalScoreOfLastWeek;
	private Integer MaxPartScoreOfLastWeek;
    private Integer totalLastWeekMotivationScore;
    private Integer totalLastWeekConditionScore;
    private Integer totalLastWeekPerformanceScore;
	private Integer totalLastWeekCount;
	
	private Integer totalLastMonthMotivationScore;
	private Integer totalLastMonthConditionScor;
	private Integer totalLastMonthPerformanceScore;
	private Integer totalLastMonthCount;
	
	
}
