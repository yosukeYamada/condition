package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Information;

@Mapper
public interface InformationMapper {

	/**
	 * インフォメーション一覧取得.
	 * 
	 * @return インフォメーション一覧
	 */
	public List<Information> findAll(Integer status);
	
	/**
	 * インフォメーション一覧取得.
	 * 
	 * @return インフォメーション一覧
	 */
	public Information findByInformationId(Integer informationId,Integer status);
	
	/**
	 * トップページのNews投稿.
	 * 
	 */
	public void insert(Information information);
	
	/**
	 * トップページのNews編集
	 * 
	 * @param information
	 */
	public Integer update(Information information);
	
	/**
	 * トップページのNewsを論理削除する.
	 * 
	 * @param information
	 */
	public Integer delete(Information information);
}