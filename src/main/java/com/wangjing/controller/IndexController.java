package com.wangjing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/home/")
	public String returnIndex() {
		return "index";
	}

	@RequestMapping("/userHome/")
	public String returnUserHome() {
		return "userHome";
	}
}
