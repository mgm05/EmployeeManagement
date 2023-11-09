package com.example.demo.controller.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogTypeRequest;
import com.example.demo.dto.dog.DogTypeResponse;
import com.example.demo.logic.dog.DogTypeLogic;
import com.example.demo.session.SessionUser;

/**
 * 犬種コントローラー.
 */
@Controller
public class DogTypeController {
	/** セッション. */
	@Autowired
	private SessionUser session;
	/** ロジック. */
	@Autowired
	DogTypeLogic logic;

	/**
	 * 犬種登録画面初期表示.
	 * 
	 * @param model Model
	 * @return dogList
	 */
	@GetMapping("/dogType")
	public String index(DogTypeRequest dogTypeRequest,Model model) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}
		List<DogGroup> dogGroupList = logic.createDogGroupList();
		model.addAttribute("dogGroupList", dogGroupList);
		return "dogType";
	}
	
	/**
	 * 登録	.
	 * @param req
	 * @return
	 */
	@PostMapping("/dogTypeRegist")
	public String regist(@ModelAttribute @Validated DogTypeRequest dogTypeRequest, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}
		
		if(result.hasErrors()) {
			return index(dogTypeRequest, model);
		}
		logic.regist(dogTypeRequest);
		
		DogTypeResponse res = new DogTypeResponse();
		res.setMsg("登録完了しました");
		
		redirectAttributes.addFlashAttribute("res", res);
		return "redirect:/dogType";
	}
}
