package com.example.controller;

import java.util.Map;

import com.example.domain.Authority;
import com.example.domain.User;
import com.example.service.ChangeAuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ユーザー権限を変更するコントローラークラス
 * 
 * @author yuichiyasui
 */
@RestController
public class ChangeAuthorityController {

	@Autowired
	private ChangeAuthorityService changeAuthorityService;

	/**
	 * ユーザー権限の変更を行うメソッド
	 * 
	 * @param param メールアドレス、変更するユーザー権限、更新ユーザーのID
	 * @return 変更したユーザーの名前、バージョン、更新日時
	 */
	@RequestMapping("/changeAuthority")
	public User changeAuthority(@RequestBody Map<String, String> param) {
		/** 取得したメールアドレスに該当する従業員がいるかチェック */
		User user = changeAuthorityService.findUserByMail(param.get("email"));
		if (user.getAuthority() == Authority.OUTSIDER.getAuthorityId()) {
			/** ケース1:従業員が存在しなかった場合 */
			user.setAuthority(Authority.OUTSIDER.getAuthorityId());
			return user;
		} else {
			/** ケース2:従業員が存在する場合 */
			if (user.getVersion() != Integer.parseInt(param.get("version"))) {
				/** ケース2-1:排他制御に引っかかった場合(最新版じゃなかった場合) */ 
				user.setVersion(0);
				return user;
			} else {
				/** ケース2-2:従業員が存在してかつ変更するデータが最新版の場合(期待する処理) */
				User updatedUser = changeAuthorityService.changeAuthority(param);
				updatedUser.setUserName(user.getUserName());
				return updatedUser;
			}
		}
	}
}