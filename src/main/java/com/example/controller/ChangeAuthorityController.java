package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.service.ChangeAuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * @return 変更したユーザーのメールアドレスと名前
     */
    @ResponseBody
    @RequestMapping("/changeAuthority")
    public Map<String, String> changeAuthority(@RequestBody Map<String, String> param) {
        String name = changeAuthorityService.changeAuthority(param.get("email"),
                Integer.parseInt(param.get("authority")), Integer.parseInt(param.get("updateUserId")));
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("email", param.get("email"));
        resultMap.put("name", name);
        return resultMap;
    }

}