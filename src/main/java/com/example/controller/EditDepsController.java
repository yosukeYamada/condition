package com.example.controller;

import java.util.Map;

import com.example.domain.Dep;
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
@RequestMapping("/editDeps")
public class EditDepsController {

    @Autowired
    private EditDepsService editDepsService;

    /**
     * 新しく部署を追加する処理を行うメソッド
     * 
     * @param param 新規に追加する部署情報
     * @return 追加した部署情報
     */
    @ResponseBody
    @RequestMapping("/addNewDep")
    public Dep addNewDep(@RequestBody Map<String, String> param) {
        System.out.println("受け取ったparam:" + param);
        Dep dep = editDepsService.addNewDep(param);
        System.out.println("返却するdep:" + dep);
        return dep;
    }

    /**
     * 部署名の変更処理を行うメソッド.
     * 
     * @param param 部署ID, 変更後の部署名, 更新ユーザーID
     * @return 変更した部署情報(部署名, 更新ユーザーID, 更新日時, バージョン)
     */
    @ResponseBody
    @RequestMapping("/changeDepName")
    public Dep changeDepName(@RequestBody Map<String, String> param) {
        Dep dep = editDepsService.changeDepName(param);
        return dep;
    }

    /**
     * 部署の削除を行うメソッド
     * 
     * @param param 部署ID, 更新ユーザーID
     */
    @ResponseBody
    @RequestMapping("/deleteDep")
    public void deleteDep(@RequestBody Map<String, Integer> param) {
        editDepsService.deleteDep(param);
    }

    /**
     * 部署IDでその部署に従業員が所属していないか確認するメソッド
     * 
     * @param param 部署ID
     * @return 従業員が所属しているか(true:していない / false:している)
     */
    @ResponseBody
    @RequestMapping("/checkIsEmployeeBelong")
    public boolean checkIsEmployeeBelong(@RequestBody Map<String, Integer> param) {
        return editDepsService.checkIsEmployeeBelong(param.get("depId"));
    }

    /**
     * 排他処理を行うメソッド.
     * 
     * @param param 部署IDとバージョン情報
     * @return 排他処理結果(true:変更OK / false:変更NG)
     */
    @ResponseBody
    @RequestMapping("/exclusiveProcessing")
    public boolean exclusiveProcessing(@RequestBody Map<String, Integer> param) {
        return editDepsService.exclusiveProcessing(param);
    }

}