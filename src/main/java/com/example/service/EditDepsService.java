package com.example.service;

import java.sql.Timestamp;
import java.util.Map;

import com.example.domain.Dep;
import com.example.domain.Status;
import com.example.mapper.DepMapper;
import com.example.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部署情報の追加、変更、削除を行うサービスクラス
 * 
 * @author yuichiyasui
 */
@Service
@Transactional
public class EditDepsService {

    @Autowired
    private DepMapper depMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 新規に部署を登録するメソッド
     * 
     * @param param 追加する部署情報
     * @return 挿入した部署情報
     */
    public Dep addNewDep(Map<String, String> param) {
        Timestamp tsDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成);
        Integer registerUserId = Integer.parseInt(param.get("registerUserId"));
        Dep dep = new Dep();
        dep.setDepName(param.get("depName"));
        dep.setRegisterUserId(registerUserId);
        dep.setRegisterDate(tsDate);
        dep.setUpdateUserId(registerUserId);
        dep.setUpdateDate(tsDate);
        dep.setVersion(1);
        dep.setStatus(Status.AVAILABLE.getStatusId());
        Integer depId = depMapper.save(dep);
        dep.setDepId(depId);
        return dep;
    }

    /**
     * 部署名の変更を行うメソッド
     * 
     * @param param 部署ID, 変更後の部署名, 更新ユーザーID
     * @return 更新した部署情報
     */
    public Dep changeDepName(Map<String, String> param) {
        Timestamp tsDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成);
        Dep dep = new Dep();
        dep.setDepId(Integer.parseInt(param.get("depId")));
        dep.setDepName(param.get("depName"));
        dep.setUpdateUserId(Integer.parseInt(param.get("updateUserId")));
        dep.setUpdateDate(tsDate);
        dep.setVersion(depMapper.updateDepName(dep));
        return dep;
    }

    /**
     * 部署の削除を行う(ステータスを削除にする)メソッド
     * 
     * @param param 部署ID, 更新ユーザーID
     */
    public void deleteDep(Map<String, Integer> param) {
        Timestamp tsDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成);
        Dep dep = new Dep();
        dep.setDepId(param.get("depId"));
        dep.setUpdateUserId(param.get("updateUserId"));
        dep.setUpdateDate(tsDate);
        dep.setStatus(Status.DELETED.getStatusId());
        depMapper.deleteByDepId(dep);
    }

    /**
     * 部署IDでその部署に従業員が所属していないか確認するメソッド
     * 
     * @param depId 部署ID
     * @return 従業員が所属しているか(true:していない / false:している)
     */
    public boolean checkIsEmployeeBelong(Integer depId) {
        Integer result = userMapper.countByDepId(depId);
        if (result == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 最新バージョンのチェックを行うメソッド
     * 
     * @param param 部署IDとバージョン情報
     * @return 最新バージョンチェック結果(true:変更OK / false:変更NG)
     */
    public boolean exclusiveProcessing(Map<String, Integer> param) {
        Integer currentVersion = depMapper.findVersionByDepId(param.get("depId"));
        if (currentVersion == param.get("version")) {
            return true;
        } else {
            return false;
        }
    }

}