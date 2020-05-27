package com.example.controller;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Information;
import com.example.domain.Status;
import com.example.service.UpdateInformationService;

/**
 * トップページのNewsを編集するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/info")
public class UpdateInformationController {

	@Autowired
	private UpdateInformationService service;
	
	/**
	 * トップページのNewsを編集する.
	 * 
	 * @param param
	 */
	@RequestMapping("/update")
	public Integer update(@RequestBody Map<String, String> param) {
		
		Information information = service.findByInformationId(Integer.parseInt(param.get("informationId")),Status.AVAILABLE.getStatusId());
		
		if(information.getVersion() != Integer.parseInt(param.get("version"))) {
			
			return Status.HOLD.getStatusId();
			
		} else {
		
			Information newInformation = new Information();
			newInformation.setInformationId(Integer.parseInt(param.get("informationId")));
			newInformation.setInformationTitle(param.get("title"));
			newInformation.setInformationContent(param.get("content"));
			newInformation.setCategoryId(Integer.parseInt(param.get("categoryId")));
			newInformation.setUpdateUserId(Integer.parseInt(param.get("updateUserId")));
			Timestamp updateDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
			newInformation.setUpdateDate(updateDate);
			
			return service.update(newInformation);

		}
	}
	
	/**
	 * トップページのNewsを論理削除する.
	 * 
	 * @param param param
	 */
	@RequestMapping("/delete")
	public Integer delete(@RequestBody Map<String, Integer> param) {
		
		Information information = service.findByInformationId(param.get("informationId"),Status.AVAILABLE.getStatusId());
		
		if(information.getVersion() != param.get("version")) {
			
			return Status.HOLD.getStatusId();
			
		} else {
			
			information.setInformationId(param.get("informationId"));
			information.setUpdateUserId(param.get("updateUserId"));
			information.setStatus(param.get("status"));
			Timestamp updateDate = new Timestamp(System.currentTimeMillis()); // 現在時刻を生成
			information.setUpdateDate(updateDate);
			
			return service.delete(information);
		}
		
	}
}
