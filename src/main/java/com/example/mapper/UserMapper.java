package com.example.mapper;

import java.sql.Timestamp;
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
	 * 全ユーザー情報とそのユーザーの引数で指定した期間の投稿を取得するメソッド.
	 * 
	 * @param startTs 検索開始期間
	 * @param endTs   検索終了期間
	 * @return 全ユーザー情報とその投稿の一覧
	 */
	public List<User> findAllAndDailyPost(Timestamp startTs, Timestamp endTs);

}
