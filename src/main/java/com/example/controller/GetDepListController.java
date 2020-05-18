package com.example.controller;

import java.util.List;

import com.example.domain.Dep;
import com.example.service.GetDepListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部署一覧を取得するコントローラークラス
 * 
 * @author yuichiyasui
 * 
 */
@RestController
public class GetDepListController {

    @Autowired
    private GetDepListService getDepListService;

    /**
     * 部署一覧を返すメソッド.
     * 
     * @return 部署一覧
     */
    @RequestMapping("/getDepList")
    public List<Dep> getDepList() {
        return getDepListService.getDepList();
    }

}