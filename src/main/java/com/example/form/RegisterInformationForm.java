package com.example.form;


import lombok.Data;

@Data
public class RegisterInformationForm {

	private String title;
	private String content;
	private Integer categoryId;
	private Integer registerUserId;
	
}
