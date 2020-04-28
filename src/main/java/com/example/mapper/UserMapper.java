package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.User;

@Mapper
public interface UserMapper {

	public Integer insertUser(User user);
	
	/**
	 * ユーザー情報を全件取得
	 * 
	 * @return　ユーザー情報
	 */
	public List<User> findAll() ;
	
}
