package com.example.service;

import com.example.mapper.DepMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EditDepsService {

    @Autowired
    private DepMapper depMapper;

    public Integer addNewDep() {
        depMapper.save();
        return 1;
    }

    public Integer changeDepName() {
        depMapper.updateDepName();
        return 1;
    }

    public Integer deleteDep() {
        depMapper.deleteByDepId();
        return 1;
    }

}