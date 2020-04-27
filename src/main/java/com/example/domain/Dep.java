package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Dep {

	private Integer depId;
	private String depName;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
}
