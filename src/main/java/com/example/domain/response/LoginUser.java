package com.example.domain.response;

import java.sql.Timestamp;
import java.util.List;

import com.example.domain.DailyPost;
import com.example.domain.Dep;
import com.example.domain.Mail;
import com.example.domain.PostedNews;

import lombok.Data;


/**
 * ログインユーザー情報を返す
 * 
 * iidashuhei
 */
@Data
public class LoginUser {

	private Integer userId;
	private String userName;
	private String userNameKana;
	private Integer depId;
	private Timestamp hireDate;
	private Integer authority;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
	private List<DailyPost> dailyPost;
	private List<Mail> mailList;
	private Dep dep; //TODO のちほど削除する
	private List<PostedNews> postedNewsList; //TODO のちほど削除
	
}