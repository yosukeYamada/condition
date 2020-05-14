package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Dep;

/**
 * 部署マッパー.
 * 
 * @author sakai
 *
 */
@Mapper
public interface DepMapper {

	/**
	 * 全検索するマッパー.
	 * 
	 * @return 部署リストの全件取得
	 */
	public List<Dep> findAll();

	public Integer save();

	public Integer updateDepName();

	public Integer deleteByDepId();

}
