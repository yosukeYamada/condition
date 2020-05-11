package com.example.service;

import java.sql.Timestamp;

import com.example.mapper.MailMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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