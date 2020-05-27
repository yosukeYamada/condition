package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Information;
import com.example.mapper.InformationMapper;

/**
 * トップページのNewsを編集するサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class UpdateInformationService {
	
	@Autowired
	private InformationMapper informationMapper;
	
	/**
	 * トップページのNewsを編集する.
	 * 
	 * @param information インフォメーション
	 */
	public Integer update(Information information) {
		
		return informationMapper.update(information);
	}
	
	/**
	 * トップページのNewsを論理削除する.
	 * 
	 * @param information
	 * 
	 */
	public Integer delete(Information information) {
		return informationMapper.delete(information);
	}
	
	/**
	 * 情報Idと状態から情報を取得する.
	 * 
	 * @param informationId 情報ID
	 * @param status 状態
	 * @return 情報
	 */
	public Information findByInformationId(Integer informationId, Integer status) {
		return informationMapper.findByInformationId(informationId, status);
	}
	
}