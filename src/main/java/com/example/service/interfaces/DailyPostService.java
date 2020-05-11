package com.example.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.DailyPost;
import com.example.form.ShowDailyPostListForm;

/**
 * DailyPostを制御するサービス.
 * 
 * @author yosuke.yamada
 *
 */
@Service
public interface DailyPostService {

	/**
	 * ユーザIDから投稿履歴を取得するメソッド.
	 * 
	 * @param form ユーザIDが格納されたフォーム
	 * @return 毎日の投稿記録のリスト
	 */
	public List<DailyPost> getDailyPostList(ShowDailyPostListForm form);
	
	
	
	
}
