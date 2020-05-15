package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.Information;
import com.example.domain.response.Form;
import com.example.form.RegisterInformationForm;
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
	
	/**
	 * インフォメーションを登録する.
	 * 
	 * @param form form
	 * @return インフォメーション一覧
	 */
	public List<Information> insert(RegisterInformationForm form) {
		
		Timestamp tsDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
		
		Information information = new Information();
		information.setInformationTitle(form.getTitle());
		information.setInformationContent(form.getContent());
		information.setCategoryId(form.getCategoryId());
		information.setInformationDate(tsDate);
		information.setRegisterUserId(form.getRegisterUserId());
		information.setRegisterDate(tsDate);
		information.setVersion(1);
		information.setStatus(1);
		
		informationMapper.insert(information);
		
		return informationMapper.findAll();
	}
}
