package com.example.mapper;

import java.util.List;

import com.example.domain.User;

import org.apache.ibatis.annotations.Mapper;

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
	 * @return ユーザリスト ユーザー情報を全件取得
	 * 
	 */
	public List<User> findAll();

	/**
	 * 新規ユーザ登録時、登録したuserのidを登録するメソッド.
	 * 
	 * @param userId
	 */
	public void updateRegisterUserId(Integer userId);

	/**
	 * 全ユーザー情報とそのユーザーの投稿一覧を取得するメソッド.
	 * 
	 * @return 全ユーザー情報とその投稿の一覧
	 */
	public List<User> findAllAndDailyPost();

	/**
	 * メールからユーザー情報を検索する.
	 * 
	 * @return ユーザー情報(ユーザードメインのプロパティ全て)
	 */
	public User findByMail(String mail);

	/**
	 * メールアドレスからユーザー情報を取得する.
	 * 
	 * @param email メール
	 * @return ユーザー情報(dep, mailなし)
	 */
	public User findUserByMail(String email);

	/**
	 * ユーザー登録を更新する
	 * 
	 * @param user ユーザー情報
	 */
	public void updateUser(User user);
	
	/**
	 * ユーザーstatusを更新するメソッド.
	 * 
	 * @param updateUserId ユーザID
	 * @param updateUserStatus ユーザstatus
	 */
	public Integer updateUserStatus(User user);
	
	/**
	 * ユーザIDでユーザを検索するメソッド.
	 * 
	 * @param userId ユーザID
	 * @return ユーザID
	 */
	public User findByUserId(Integer userId);

	/**
	 * 部署IDで該当の部署に所属している人数を取得するメソッド
	 * 
	 * @param depId 部署ID
	 * @return 所属人数
	 */
	public Integer countByDepId(Integer depId);
	
	
	//テスト用
	public void insertUsers();

	/**
	 * ユーザー情報の更新の際の排他処理のためにversionを取得するメソッド.
	 * 
	 * @param userId ユーザーID
	 * @return version
	 */
	public Integer findVersion(Integer userId);

}