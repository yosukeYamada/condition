package com.example.controller;

import java.util.ArrayList;
import java.util.List;

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
	public List<DailyPost> edit(@RequestBody EditDailyPostForm form) {
		List<DailyPost> dailyPostList = new ArrayList<>();
		// DBのversion番号
		DailyPost dailyPost = editDailyPostService.findByDailyPostId(form.getDailyPostId());
		Integer versionDB = dailyPost.getVersion();
		// 送られてきたversion番号
		Integer version = form.getVersion();
		// DBのversion番号と送られてきたversion番号が違った場合
		if (versionDB != version) {
			DailyPost daily = new DailyPost();
			daily.setVersion(0);
			dailyPostList.add(daily);
			return dailyPostList;
			// DBのversion番号と送られてきたversion番号が合っていた場合
		} else {
			return editDailyPostService.edit(form);
		}
	}
}