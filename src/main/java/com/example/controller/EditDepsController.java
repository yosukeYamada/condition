package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.service.EditDepsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部署名の変更・追加・削除を行うコントローラークラス
 * 
 * @author yuichiyasui
 */
@RestController
public class EditDepsController {

    @Autowired
    private EditDepsService editDepsService;

    @ResponseBody
    @RequestMapping("/addNewDep")
    public Map<String, String> addNewDep(@RequestBody Map<String, String> param) {
        Map<String, String> resultMap = new HashMap<>();
        editDepsService.addNewDep();
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/changeDepName")
    public Map<String, String> changeDepName(@RequestBody Map<String, String> param) {
        Map<String, String> resultMap = new HashMap<>();
        editDepsService.changeDepName();
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/deleteDep")
    public Map<String, String> deleteDep(@RequestBody Map<String, String> param) {
        Map<String, String> resultMap = new HashMap<>();
        editDepsService.deleteDep();
        return resultMap;
    }
}