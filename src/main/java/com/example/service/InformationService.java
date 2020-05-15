package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.Information;
import com.example.domain.response.Form;
import com.example.mapper.CategoryMapper;
import com.example.mapper.InformationMapper;

/**
 * インフォメーションに関するサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class InformationService {

	@Autowired
	private InformationMapper informationMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * インフォメーション一覧を取得する.
	 * 
	 * @return インフォメーション一覧
	 */
	public Form findAll() {
		List<Information> informationList = informationMapper.findAll();
		List<Category> category = categoryMapper.findAll();
		Form form = new Form();
		form.setInformationList(informationList);
		form.setCategory(category);
		return form;
	}
}
