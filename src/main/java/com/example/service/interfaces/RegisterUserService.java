package com.example.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.User;

/**
 * ユーザ登録処理を行うserviceのインターフェース.
 * 
 * @author yosuke.yamada
 *
 */
@Service
public interface RegisterUserService {
	
	/**
	 * ユーザ登録をするメソッド.
	 * 
	 * @param user 登録するユーザー
	 * @return ユーザーID
	 */
	public Integer registerUser(User user);
	
	/**
	 * 全件検索メソッド.
	 * 
	 * @return ユーザリスト
	 */
	public List<User> findAll();

}
