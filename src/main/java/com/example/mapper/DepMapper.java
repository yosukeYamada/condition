package com.example.mapper;

import java.util.List;

import com.example.domain.Dep;

import org.apache.ibatis.annotations.Mapper;

/**
 * depsテーブルを操作するマッパー.
 * 
 * @author sakai
 */
@Mapper
public interface DepMapper {

	/**
	 * 引数で受け取ったステータスの部署情報を取得するメソッド
	 * 
	 * @param status ステータス
	 * @return 部署リストの全件取得
	 */
	public List<Dep> findByStatus(Integer status);

	/**
	 * 部署情報の挿入を行うメソッド
	 * 
	 * @param dep 新規に追加する部署情報
	 * @return 自動採番された部署ID
	 */
	public Integer save(Dep dep);

	/**
	 * 部署名の更新を行うメソッド
	 * 
	 * @param dep 更新する部署情報
	 * @return 更新されたバージョン
	 */
	public Integer updateDepName(Dep dep);

	/**
	 * 部署のステータスを削除にするメソッド
	 * 
	 * @param dep 削除する部署情報
	 */
	public void deleteByDepId(Dep dep);

	/**
	 * 部署IDで部署情報の検索を行い、バージョン情報を取得するメソッド
	 * 
	 * @param depId 部署ID
	 * @return バージョン情報
	 */
	public Integer findVersionByDepId(Integer depId);

}
