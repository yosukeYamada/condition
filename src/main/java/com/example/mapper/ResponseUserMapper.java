package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Mail;

@Mapper
public interface ResponseUserMapper {

	public Mail findUserInfo(String mail);
}
