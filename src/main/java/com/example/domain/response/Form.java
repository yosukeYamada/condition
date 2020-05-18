package com.example.domain.response;

import java.util.List;

import com.example.domain.Category;
import com.example.domain.Information;

import lombok.Data;

@Data
public class Form {

	private List<Information> informationList;
	private List<Category> category;
	
}
