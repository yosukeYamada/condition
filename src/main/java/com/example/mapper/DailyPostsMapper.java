package com.example.mapper;

import java.util.List;
import com.example.domain.DailyPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DailyPostsMapper {

    public List<DailyPost> findAll();

}