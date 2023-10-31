package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.session.SessionUser;

/**
 * システムメニューコントローラー.
 */
@Controller
public class MenuController {
	/** セッション. */
	@Autowired
	private SessionUser session;

	/**
	 * ログイン画面初期表示.
	 * 
	 * @param model Model
	 * @return login
	 */
	@GetMapping("/menu")
	public String index(Model model) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
		    return "redirect:/login";
		}
		return "menu";
	}



}
