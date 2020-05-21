package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * ユーザー登録の更新をするフォーム
 * 
 * @author riho.ikeda
 *
 */
@Data
public class UpdateUserForm {

	private Integer userId;
	private Integer updateUserId;
	private Integer version;
	@NotBlank
	@Pattern(regexp = "^[^ -~｡-ﾟ]{1,20}+$")
	private String userName;
	@NotBlank
	@Pattern(regexp = "^[ぁ-んー]{1,20}+$")
	private String userNameKana;
	@NotBlank
	@Pattern(regexp = "^[0-9]+$")
	private String depId;
	@NotBlank
	@Pattern(regexp = "\\d{4}")
	private String hireYear;
	@NotBlank
	@Pattern(regexp = "\\d{1,2}")
	private String hireMonth;
	@Email
	@NotBlank
	private String mailAddress;

	public Integer getIntDepId() {
		return Integer.parseInt(depId);
	}

}
