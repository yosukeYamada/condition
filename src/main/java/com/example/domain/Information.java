package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Information {

	private Integer informationId;
	private Timestamp informationDate;
	private String informationTitle;
	private String informationContent;
	private Integer categoryId;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
	
}