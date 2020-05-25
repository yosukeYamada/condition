package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DailyPost;
import com.example.form.EditDailyPostForm;
import com.example.service.EditDailyPostService;

/**
 * 毎日の投稿を編集するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/editDailyPost")
public class EditDailyPostController {

	@Autowired
	private EditDailyPostService editDailyPostService;

	@RequestMapping("/edit")
	public DailyPost edit(@RequestBody EditDailyPostForm form) {
			return editDailyPostService.edit(form);
		}
}