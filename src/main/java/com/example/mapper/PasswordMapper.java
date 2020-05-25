package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Password;

@Mapper
public interface PasswordMapper {

	public void registerPassword(Password password) ;
	
	public Password load(String mailAddress);
	
}
