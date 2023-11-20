package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.LoginInfoDao;
import com.example.demo.dto.LoginRequests;
import com.example.demo.session.SessionUser;

import jakarta.servlet.http.HttpSession;

/**
 * ログインコントローラー.
 */
@Controller
public class LoginController {
	/** ログイン情報. */
	@Autowired
	private LoginInfoDao dao;
	/** セッション. */
	@Autowired
	private SessionUser session;
	/** HttpSession. */
	@Autowired
	private HttpSession httpSession;

	/**
	 * ログイン画面初期表示.
	 * 
	 * @param model Model
	 * @return login
	 */
	@GetMapping("/login")
	public String index(Model model) {

		return "login";
	}

	/**
	 * ログイン成功時は一覧画面に遷移、 失敗時はログイン画面遷移.
	 * 
	 * @param req   LoginRequests
	 * @param model Model
	 * @return "redirect:/list"
	 */
	@PostMapping("/login")
	public String login(LoginRequests req, Model model) {
		// リクエストのログインIDとパスワードでDBのログイン情報検索
		// DBデータとリクエストが一致したらtrueしなかったらfalse
		boolean loginFlg = dao.isLoginSuccess(req.getLoginId(), req.getPassword());

		// ログイン情報が一致したらセッションに保存
		// 一覧画面に遷移
		if (loginFlg) {

			session.setLoginId(req.getLoginId());
			session.setPassword(req.getPassword());
			return "redirect:/menu";
		}

		// ログイン失敗したらエラーメッセージをセッション(仕様)に保存
		// ログイン画面に遷移
		session.setMsg("IDまたはパスワードが異なります。");
		model.addAttribute("msg", session.getMsg());

		return "login";
	}

	/**
	 * セッションを破棄し、ログアウトする。 ログイン画面へ遷移.
	 * 
	 * @return "redirect:/login"
	 */
	@GetMapping("/logout")
	public String logout() {

		httpSession.invalidate();

		return "redirect:/login";
	}

}
