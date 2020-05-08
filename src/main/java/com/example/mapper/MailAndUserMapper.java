package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Mail;

@Mapper
public interface MailAndUserMapper {

	public Mail findByMailAndAuthority(String mail);
}