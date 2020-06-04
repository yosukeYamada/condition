package com.example.service;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Authority;
import com.example.domain.Status;
import com.example.domain.User;
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
    private UserMapper userMapper;

    /**
     * メールからユーザー情報を取得する.
     * 
     * @param email メール
     * @return ユーザー情報
     */
    public User findUserByMail(String email) {
        User user = userMapper.findUserByMail(email,Status.AVAILABLE.getStatusId());
        if (user == null) {
            /** 該当のユーザーが存在しなかった場合 */
            User notUser = new User();
            notUser.setAuthority(Authority.OUTSIDER.getAuthorityId());
            return notUser;
        } else {
            /** 存在した場合 */
            return user;
        }
    }

    /**
     * ユーザー権限の変更を行うメソッド
     * 
     * @param email        更新するユーザーのメールアドレス
     * @param authority    更新するユーザー権限
     * @param updateUserId 更新者のユーザーID
     * @return 最新のバージョン番号
     */
    public User changeAuthority(Map<String, String> param) {
        Integer authority = Integer.parseInt(param.get("authority"));
        Integer updateUserId = Integer.parseInt(param.get("updateUserId"));
        Timestamp updateDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
        Integer version = userMapper.updateAuthorityByEmail(param.get("email"), authority, updateUserId, updateDate);
        User user = new User();
        user.setAuthority(authority);
        user.setUpdateUserId(updateUserId);
        user.setUpdateDate(updateDate);
        user.setVersion(version);
        return user;
    }
}