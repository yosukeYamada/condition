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
	public List<Information> findAll();
	
	
}