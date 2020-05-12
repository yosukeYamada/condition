package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.PostedNewsForm;
import com.example.service.PostedNewsService;

/**
 * @author sakai
 *
 */
@RestController
public class NewsController {

	@Autowired
	private PostedNewsService postedNewsService;
	
	@RequestMapping("/News")
	public void postedNews(@RequestBody PostedNewsForm form) {
		postedNewsService.PostedNews(form);
	}
	
}
