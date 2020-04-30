package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DailyPost;

@Mapper
public interface RegisterDailyPostMapper {
	public void insertDailyPost(DailyPost dailyPost);
}
