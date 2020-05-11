package com.example.domain;

import java.util.List;

import lombok.Data;

/**
 * 新規登録のドメインクラス.
 * 
 * @author sakai
 *
 */
@Data
public class NewUser {

	private String mailName;
	private Integer authority;
	private List<Dep> depList;
	
}
