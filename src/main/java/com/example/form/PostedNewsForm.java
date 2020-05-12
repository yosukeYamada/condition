package com.example.form;

import lombok.Data;

/**
 * @author sakai
 *
 */
@Data
public class PostedNewsForm {

	/** 投稿者のuserId */
	private Integer userId;
	/** お知らせ投稿内容 */
	private String newsComment;
	
}
