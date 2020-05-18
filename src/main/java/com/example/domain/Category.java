package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Category {

	private Integer categoryId;
	private String categoryName;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
}
