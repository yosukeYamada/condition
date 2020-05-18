package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DailyPost;
import com.example.domain.Mail;
import com.example.domain.PostedComment;
import com.example.domain.PostedCondition;
import com.example.domain.PostedMotivation;
import com.example.domain.PostedPerformance;
import com.example.domain.Status;
import com.example.domain.User;
import com.example.domain.error.ExclusiveException;
import com.example.form.UpdateUserForm;
import com.example.form.UpdateUserStatusForm;
import com.example.mapper.DailyPostMapper;
import com.example.mapper.MailMapper;
import com.example.mapper.PostedCommentMapper;
import com.example.mapper.PostedConditionMapper;
import com.example.mapper.PostedMotivationMapper;
import com.example.mapper.PostedPerformanceMapper;
import com.example.mapper.UserMapper;

/**
 * ユーザー登録を更新するサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class UpdateUserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MailMapper mailMapper;

	@Autowired
	private DailyPostMapper dailyPostMapper;

	@Autowired
	private PostedPerformanceMapper postedPerformanceMapper;

	@Autowired
	private PostedConditionMapper postedConditionMapper;

	@Autowired
	private PostedMotivationMapper postedMotivationMapper;

	@Autowired
	private PostedCommentMapper postedCommentMapper;

	/**
	 * ユーザー情報の更新をusersとmailsのテーブルにUPDATEする.
	 * 
	 * @param form 更新情報
	 * @return 全従業員のユーザー情報
	 */
	public List<User> updateUser(UpdateUserForm form) {

		User user = new User();

		StringBuilder bldHireDate = new StringBuilder();
		bldHireDate.append(form.getHireYear());
		bldHireDate.append("-");
		bldHireDate.append(form.getHireMonth());
		bldHireDate.append("-01 00:00:00");
		Timestamp hireDate = java.sql.Timestamp.valueOf(bldHireDate.toString());

		Timestamp updateDate = new Timestamp(System.currentTimeMillis());

		user.setUserId(form.getUserId());
		user.setUserName(form.getUserName());
		user.setUserNameKana(form.getUserNameKana());
		user.setDepId(form.getIntDepId());
		user.setHireDate(hireDate);
		user.setUpdateDate(updateDate);
		user.setUpdateUserId(form.getUpdateUserId());

		userMapper.updateUser(user);

		Mail mail = new Mail();

		mail.setUserId(form.getUserId());
		mail.setMailName(form.getMailAddress());
		mail.setUpdateDate(updateDate);
		mail.setUpdateUserId(form.getUpdateUserId());

		mailMapper.updateMail(mail);

		List<User> employeeList = userMapper.findAllAndDailyPost();
		return employeeList;

	}

	/**
	 * ユーザのステータスを更新するメソッド.
	 * 
	 * @param form リクエストパラメータ
	 * @return 最新のユーザVersion
	 */
	public Integer updateUserStatus(UpdateUserStatusForm form) {
		Integer updateUserId = Integer.parseInt(form.getUpdateUserId());
		Integer userId = Integer.parseInt(form.getUserId());
		Integer updateStatus = Integer.parseInt(form.getUpdateUserStatus());
		Integer userVarsion = Integer.parseInt(form.getUserVersion());
		Timestamp updateDate = new Timestamp(System.currentTimeMillis());
		User latestUser = userMapper.findByUserId(userId);
		Integer latestUserVersion = latestUser.getVersion();
		if (userVarsion != latestUserVersion) {
			throw new ExclusiveException("不正なパラメータです");
		}
		userVarsion += 1;
		User user = new User();
		user.setUpdateUserId(updateUserId);
		user.setUserId(userId);
		user.setStatus(updateStatus);
		user.setVersion(userVarsion);
		user.setUpdateDate(updateDate);
		// userのテーブルを更新
		Integer varsion = userMapper.updateUserStatus(user);
		if (user.getStatus() == Status.DELETED.getStatusId()) {
			// もし削除ならばユーザに紐づく投稿履歴とメールアドレスを削除
			updateRelatedUsers(latestUser, user);
		}
		return varsion;

	}

	/**
	 * Userテーブルに紐づく全ての情報を削除するメソッド.
	 * 
	 * @param latestUser
	 * @param user
	 */
	public void updateRelatedUsers(User latestUser, User user) {
		Mail mail = new Mail();
		BeanUtils.copyProperties(user, mail);
		List<Mail> latestMailList = latestUser.getMailList();
		for(Mail latestMail:latestMailList) {
			Integer latestMailVersion = latestMail.getVersion();
			latestMailVersion+=1;
			mail.setVersion(latestMailVersion);
			mailMapper.updateMailStatus(mail);
		}
		DailyPost dailyPost = new DailyPost();
		BeanUtils.copyProperties(user, dailyPost);
		PostedPerformance postedPerformance = new PostedPerformance();
		BeanUtils.copyProperties(user, postedPerformance);
		PostedCondition postedCondition = new PostedCondition();
		BeanUtils.copyProperties(user, postedCondition);
		PostedMotivation postedMotivation = new PostedMotivation();
		BeanUtils.copyProperties(user, postedMotivation);
		PostedComment postedComment = new PostedComment();
		BeanUtils.copyProperties(user, postedComment);
		List<DailyPost> latestDailyPostList = latestUser.getDailyPost();
		// daily_post下位テーブルをすべて削除
		for (DailyPost latestDailyPost : latestDailyPostList) {
			// パフォーマンスを更新
			Integer latestDailyPostId = latestDailyPost.getDailyPostId();
			PostedPerformance latestPostedPerformance = latestDailyPost.getPostedPerformance();
			Integer latestPostedPerformanceVersion = latestPostedPerformance.getVersion();
			latestPostedPerformanceVersion += 1;
			postedPerformance.setDailyPostId(latestDailyPostId);
			postedPerformance.setVersion(latestPostedPerformanceVersion);
			postedPerformanceMapper.updateStatus(postedPerformance);

			// コンディションを更新
			PostedCondition latestPostedCondition = latestDailyPost.getPostedCondition();
			Integer latestPostedConditionVersion = latestPostedCondition.getVersion();
			latestPostedConditionVersion += 1;
			postedCondition.setDailyPostId(latestDailyPostId);
			postedCondition.setVersion(latestPostedConditionVersion);
			postedConditionMapper.updateStatus(postedCondition);

			// モチベーションを更新
			PostedMotivation latestPostedMotivation = latestDailyPost.getPostedMotivation();
			Integer latestPostedMotivationVersion = latestPostedMotivation.getVersion();
			latestPostedMotivationVersion += 1;
			postedMotivation.setDailyPostId(latestDailyPostId);
			postedMotivation.setVersion(latestPostedMotivationVersion);
			postedMotivationMapper.updateStatus(postedMotivation);

			// コメントを更新
			PostedComment latestPostedComment = latestDailyPost.getPostedComment();
			Integer latestPostedCommentVersion = latestPostedComment.getVersion();
			latestPostedCommentVersion += 1;
			postedComment.setDailyPostId(latestDailyPostId);
			postedComment.setVersion(latestPostedCommentVersion);
			postedCommentMapper.updateStatus(postedComment);

			// daily_postを更新
			Integer latestDailyPostVersion = latestDailyPost.getVersion();
			latestDailyPostVersion += 1;
			dailyPost.setDailyPostId(latestDailyPostId);
			dailyPost.setVersion(latestDailyPostVersion);
			dailyPostMapper.updateDailyPostStatus(dailyPost);
		}

	}

}
