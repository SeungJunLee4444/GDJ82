package com.gdu.app15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gdu.app15.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
		
	
}
