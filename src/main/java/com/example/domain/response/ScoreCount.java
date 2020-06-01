package com.example.domain.response;

import lombok.Data;

@Data
public class ScoreCount {
	private Integer maxTotalScoreOfLastWeek;
	private Integer maxPartScoreOfLastWeek;
    private Integer totalLastWeekMotivationScore;
    private Integer totalLastWeekConditionScore;
    private Integer totalLastWeekPerformanceScore;
	private Integer totalLastWeekCount;
	
	private Integer maxTotalScoreOfLastMonth;
	private Integer maxPartScoreOfLastMonth;
	private Integer totalLastMonthMotivationScore;
	private Integer totalLastMonthConditionScore;
	private Integer totalLastMonthPerformanceScore;
	private Integer totalLastMonthCount;
	
	
}
