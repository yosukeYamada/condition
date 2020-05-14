package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DailyPost;
import com.example.form.EditDailyPostForm;
import com.example.mapper.DailyPostMapper;
import com.example.mapper.PostedCommentMapper;
import com.example.mapper.PostedConditionMapper;
import com.example.mapper.PostedMotivationMapper;
import com.example.mapper.PostedPerformanceMapper;

/**
 * 毎日の投稿を編集するサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class EditDailyPostService {

	@Autowired
	private DailyPostMapper dailyPostMapper;
	@Autowired
	private PostedMotivationMapper postedMotivationMapper;
	@Autowired
	private PostedConditionMapper postedConditionMapper;
	@Autowired
	private PostedPerformanceMapper postedPerformanceMapper;
	@Autowired
	private PostedCommentMapper postedCommentMapper;
	
	/**
	 * 毎日の投稿をidから取得する.
	 * 
	 * @param dailyPostId 毎日の投稿ID
	 * @return 投稿状況
	 */
	public DailyPost findByDailyPostId(Integer dailyPostId) {
		return dailyPostMapper.findByDailyPostId(dailyPostId);
	}
	
	/**
	 * 毎日の投稿を編集する.
	 * 
	 * @param form form
	 * @return 毎日の投稿リスト
	 */
	public List<DailyPost> edit(EditDailyPostForm form) {
		
		//updateで必要な要素
		Integer dailyPostId = form.getDailyPostId();
		Integer updateUserId = form.getUpdateUserId();
		Integer motivationId = form.getMotivationId();
		Integer conditionId = form.getConditionId();
		Integer performanceId = form.getPerformanceId();
		String comment = form.getComment();
		Timestamp updateDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
		
		
		dailyPostMapper.update(dailyPostId,updateUserId,updateDate);
		
		postedMotivationMapper.update(dailyPostId, updateUserId, motivationId, updateDate);
		
		postedConditionMapper.update(dailyPostId, updateUserId, conditionId, updateDate);
		
		postedPerformanceMapper.update(dailyPostId, updateUserId, performanceId, updateDate);
		
		postedCommentMapper.update(dailyPostId, updateUserId, comment, updateDate);
		
		return dailyPostMapper.findByUserId(updateUserId);
	
	}
}
