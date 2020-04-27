package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.User;


@Mapper
public interface ShowEmployeeListMapper {
	
	public List<User> findAll() ;

}
