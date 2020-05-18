package com.example.form;

import lombok.Data;

/**
 * @author sakai
 *
 */
@Data
public class PostedNewsForm {

	/** ユーザーID */
	private Integer userId;
	/** お知らせID */
	private Integer newsId;
	/** お知らせ投稿内容 */
	private String newsComment;
	
}
