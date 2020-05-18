package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.mapper.MailMapper;
import com.example.mapper.UserMapper;

/**
 * ユーザー権限の変更を行うサービスクラス
 * 
 * @author yuichiyasui
 */
@Service
@Transactional
public class ChangeAuthorityService {

    @Autowired
    private MailMapper mailMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * メールからユーザー情報を取得する.
     * 
     * @param email メール
     * @return ユーザー情報
     */
    public User findUserByMail(String email) {
    	User user = userMapper.findUserByMail(email);
    	if(user == null) {
    		User noUser = new User();
    		noUser.setAuthority(3); // TODO
    		return noUser;
    	} else {
    		return user;
    	}
    }

    /**
     * ユーザー権限の変更を行うメソッド
     * 
     * @param email        更新するユーザーのメールアドレス
     * @param authority    更新するユーザー権限
     * @param updateUserId 更新者のユーザーID
     * @return 更新されたユーザーの名前
     */
    public String changeAuthority(String email, Integer authority, Integer updateUserId) {
        Timestamp updateDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
        String name = mailMapper.updateAuthorityByEmail(email, authority, updateUserId, updateDate);
        return name;
    }
}