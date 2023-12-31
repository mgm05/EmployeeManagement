package com.example.demo.controller.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.dog.DogDetailResponse;
import com.example.demo.logic.dog.DogDetailLogic;
import com.example.demo.session.SessionUser;

/**
 * 犬詳細コントローラー.
 */
@Controller
public class DogDetailController {
	/** セッション. */
	@Autowired
	private SessionUser session;
	/** ロジック. */
	@Autowired
	private DogDetailLogic logic;

	/**
	 * 犬詳細画面初期表示.
	 * 
	 * @param dogId String
	 * @param model Model
	 * @return dogList
	 */
	@GetMapping("/dogDetail")
	public String index(String dogId, Model model) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}

		DogDetailResponse res = logic.createDogDetailRes(dogId);

		model.addAttribute("res", res);
		return "dogDetail";
	}
}
