package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encode")
public class EncodeController {

	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("")
	public String index() {
//		System.out.println(encoder.encode("yosuke.yamada@rakus-partners.co.jp"));
//		System.out.println(encoder.encode("ryohei.suzuki@rakus-partners.co.jp"));
//		System.out.println(encoder.encode("akihiro.sakai@rakus-partners.co.jp"));
//		System.out.println(encoder.encode("riho.ikeda@rakus-partners.co.jp"));
//		System.out.println(encoder.encode("yuichi.yasui@rakus-partners.co.jp"));
//		System.out.println(encoder.encode("shuhei.iida@rakus-partners.co.jp"));
//		System.out.println(encoder.encode("aki.suzuki@rakus.co.jp"));
		System.out.println("aaa");
		System.out.println("aaa");
		System.out.println("aaa");
		System.out.println("aaa");
		System.out.println("aaa");
		System.out.println(encoder.encode("shunsuke.higashi@rakus-partners.co.jp"));
//		System.out.println(encoder.encode("shoya.yamaseki@rakus-partners.co.jp"));
		return "index";
	}
}
