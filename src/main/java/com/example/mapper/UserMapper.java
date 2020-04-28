package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.User;

/**
 * ユーザーマッパー.
 * 
 * @author yosuke.yamada
 *
 */
@Mapper
public interface UserMapper {

	/**
	 * ユーザ登録をするmapper.
	 * 
	 * @param user 登録するユーザー
	 * @return 登録したユーザのID
	 */
	public Integer insertUser(User user);
	
	/**
	 * 全件検索するマッパー.
	 * 
	 * @return ユーザリスト
	 * ユーザー情報を全件取得
	 * 
	 * @return　ユーザー情報
	 */
	public List<User> findAll() ;
	
}
