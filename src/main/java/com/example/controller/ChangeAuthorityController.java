package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.service.ChangeAuthorityService;

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
     * @return 変更したユーザーのメールアドレスと名前
     */
    @ResponseBody
    @RequestMapping("/changeAuthority")
    public Map<String, String> changeAuthority(@RequestBody Map<String, String> param) {
    	
    	Map<String, String> resultMap = new HashMap<>();
    	
    	User user = changeAuthorityService.findUserByMail(param.get("email"));
    	
    	if(user.getAuthority() == 3) {
    		resultMap.put("email", "null");
    		return resultMap;
    		
    	} else {
    		
    		//DBにあるuserのversion番号を取得.
    		Integer version = user.getVersion();
    		
    		System.err.println("DBのversion番号 : " + version);
    		System.err.println("送られてきたversion番号 : " + Integer.parseInt(param.get("version")));
    		
    		
    		//DBにあるuserのversion番号とフロントから送られてきたversion番号を確認.
    		if(version != Integer.parseInt(param.get("version"))) {
    			resultMap.put("version", "null");
    			return resultMap;
    			
    		} else {
    			
	    		String name = changeAuthorityService.changeAuthority(param.get("email"),
	    				Integer.parseInt(param.get("authority")), Integer.parseInt(param.get("updateUserId")));
	    		resultMap.put("email", param.get("email"));
	    		resultMap.put("name", name);
	    		return resultMap;
    		}
    	}
    }
}