package com.example.service;

import java.sql.Timestamp;

import com.example.mapper.MailMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangeAuthorityService {

    @Autowired
    private MailMapper mailMapper;

    public String changeAuthority(String email, Integer authorityId, Integer updateUserId) {
        Timestamp updateDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
        String name = mailMapper.updateAuthorityByEmail(email, authorityId, updateUserId, updateDate);
        return name;
    }

}