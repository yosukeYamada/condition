package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.User;

@Mapper
public interface UserMapper {

	public Integer insertUser(User user);
	
}
