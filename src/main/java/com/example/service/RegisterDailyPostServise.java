package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Condition;
import com.example.domain.DailyPost;
import com.example.domain.Motivation;
import com.example.domain.Performance;
import com.example.domain.PostedComment;
import com.example.domain.PostedCondition;
import com.example.domain.PostedMotivation;
import com.example.domain.PostedPerformance;
import com.example.domain.Status;
import com.example.form.RegisterDailyPostForm;
import com.example.mapper.DailyPostMapper;

@Service
public class RegisterDailyPostServise {
	@Autowired
	private DailyPostMapper dailyPostMapper;
	
	public void registerDailyPost(RegisterDailyPostForm form) {
		// 投稿日をString型→Date型→TimeStamp型に変換
		Timestamp tsDate = Timestamp.valueOf(stringToLocalDateTime(form.getDate()));
		Integer dailyPostId = null; //serial
		
		// conditionのセット
		Condition con = new Condition();
		con.setConditionId(null); //serial
		con.setConditionName(form.getConditionName());
		// motivationのセット
		Motivation mot = new Motivation();
		mot.setMotivationId(null); //serial
		mot.setMotivationName(form.getMotivationName());
		// performanceのセット
		Performance per = new Performance();
		per.setPerformanceId(null); //serial
		per.setPerformanceName(form.getPerformanceName());
		// statusのセット
		Status sta = new Status();
		sta.setStatusId(null); //serial
		sta.setStatusName(null); //何これ
		
		// postedMotivationのセット
		PostedMotivation pm = setPostedMotivationId(mot, form, dailyPostId, tsDate);
		// postedConditionのセット
		PostedCondition pc = setPostedCondition(con, form, dailyPostId, tsDate);
		// postedPerformanceのセット
		PostedPerformance pp = setPostedPerformance(per, form, dailyPostId, tsDate);
		// postedCommentのセット
		PostedComment pcomment = setPostedComment(form, dailyPostId, tsDate);
		
		// フォームの値をDailyPostにつめる
		DailyPost dailyPost = new DailyPost();
		dailyPost.setDailyPostId(null); //serial
		dailyPost.setUserId(form.getIntUserId());
		dailyPost.setDate(tsDate);
		dailyPost.setRegisterUserId(form.getIntUserId());
		dailyPost.setRegisterDate(tsDate);
		dailyPost.setUpdateDate(null);
		dailyPost.setUpdateUserId(null);
		dailyPost.setVersion(1);
		dailyPost.setStatus(1);
		dailyPost.setPostedMotivation(pm);
		dailyPost.setPostedCondition(pc);
		dailyPost.setPostedPerformance(pp);
		dailyPost.setPostedComment(pcomment);

		//ドメインをマッパーに渡す
		dailyPostMapper.save(dailyPost);
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
    
    public PostedMotivation setPostedMotivationId(Motivation mot, RegisterDailyPostForm form, Integer dailyPostId, Timestamp tsDate) {
    	PostedMotivation pm = new PostedMotivation();
   		pm.setPostedMotivationId(null); //serial
   		pm.setDailyPostId(dailyPostId);
    	pm.setMotivationId(mot.getMotivationId());
    	pm.setRegisterUserId(form.getIntUserId());
    	pm.setRegisterDate(tsDate);
    	pm.setUpdateUserId(null);
    	pm.setUpdateDate(null);
    	pm.setVersion(1);
    	pm.setStatus(1);
    	pm.setMotivation(mot);
    	
    	return pm;
    }
    
   public PostedCondition setPostedCondition(Condition con, RegisterDailyPostForm form, Integer dailyPostId, Timestamp tsDate) {
	   PostedCondition pc = new PostedCondition();
	   pc.setPostedConditionId(null); //serial
	   pc.setDailyPostId(dailyPostId);
	   pc.setConditionId(con.getConditionId());
	   pc.setRegisterUserId(form.getIntUserId());
	   pc.setRegisterDate(tsDate);
	   pc.setUpdateUserId(null);
	   pc.setUpdateDate(null);
	   pc.setVersion(1);
	   pc.setStatus(1);
	   pc.setCondition(con);
	   
	   return pc;
   }
   
   public PostedPerformance setPostedPerformance(Performance per, RegisterDailyPostForm form, Integer dailyPostId, Timestamp tsDate) {
	   PostedPerformance pp = new PostedPerformance();
	   pp.setPostedPerformanceId(null); //serial
	   pp.setDailyPostId(dailyPostId);
	   pp.setPerformanceId(per.getPerformanceId());
	   pp.setRegisterUserId(form.getIntUserId());;
	   pp.setRegisterDate(tsDate);
	   pp.setUpdateUserId(null);
	   pp.setUpdateDate(null);
	   pp.setVersion(1);
	   pp.setStatus(1);
	   pp.setPerformance(per);
	   
	   return pp;
   }
   
   public PostedComment setPostedComment(RegisterDailyPostForm form, Integer dailyPostId, Timestamp tsDate) {
	   PostedComment pcomment = new PostedComment();
	   pcomment.setPostedCommentId(null);
	   pcomment.setDailyPostId(dailyPostId);
	   pcomment.setComment(form.getComment());
	   pcomment.setRegisterUserId(form.getIntUserId());
	   pcomment.setRegisterDate(tsDate);
	   pcomment.setUpdateUserId(null);
	   pcomment.setUpdateDate(null);
	   pcomment.setVersion(1);
	   pcomment.setStatus(1);
	   
	   return pcomment;
   }
}
