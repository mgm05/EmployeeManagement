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

import com.example.demo.constEnum.ContractType;
import com.example.demo.constEnum.DogSex;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogType;
import com.example.demo.dto.dog.PuchaseRequest;
import com.example.demo.logic.dog.PurchaseLogic;
import com.example.demo.service.dog.DogService;
import com.example.demo.session.SessionUser;

/**
 * 仕入れコントローラー.
 */
@Controller
public class PurchaseController {
	/** セッション. */
	@Autowired
	private SessionUser session;
	/** 仕入れロジック. */
	@Autowired
	private PurchaseLogic purchaseLogic;

	/**
	 * 仕入れ画面初期表示.
	 * 
	 * @param model Model
	 * @return purchase
	 */
	@GetMapping("/purchase")
	public String index(Model model, PuchaseRequest puchaseRequest) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}
		List<DogGroup> dogGroupList = purchaseLogic.createDogGroupList();
		List<DogType> dogTypeList = purchaseLogic.createDogTypeList();

		model.addAttribute("dogGroupList", dogGroupList);
		model.addAttribute("dogTypeList", dogTypeList);
		model.addAttribute("sexEnum", DogSex.values());
		model.addAttribute("contractTypeEnum", ContractType.values());
		return "purchase";
	}

	/**
	 * 仕入れ登録.
	 * 
	 * @param puchaseRequest PuchaseRequest
	 * @return purchase
	 */
	@PostMapping("/purchaseRegist")
	public String regist(@ModelAttribute @Validated PuchaseRequest puchaseRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return index(model, puchaseRequest);
		}
		purchaseLogic.regist(puchaseRequest, session.getLoginId());
		return "redirect:/purchase";
	}
}
