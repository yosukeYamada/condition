package com.example.service;

import java.util.List;

import com.example.domain.Dep;
import com.example.domain.Status;
import com.example.mapper.DepMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部署一覧の取得処理を行うサービスクラス
 * 
 * @author yuichiyasui
 */
@Service
@Transactional
public class GetDepListService {

    @Autowired
    private DepMapper depMapper;

    /**
     * 部署一覧の取得を行うメソッド
     * 
     * @return 部署一覧
     */
    public List<Dep> getDepList() {
        return depMapper.findByStatus(Status.AVAILABLE.getStatusId());
    }

}