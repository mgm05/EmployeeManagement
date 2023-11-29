package com.example.demo.controller.dog;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.constEnum.DogSex;
import com.example.demo.constEnum.DogSize;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogListRequest;
import com.example.demo.dto.dog.DogListResponse;
import com.example.demo.dto.dog.DogType;
import com.example.demo.logic.dog.DogListLogic;
import com.example.demo.session.SessionUser;

/**
 * 犬一覧コントローラー.
 */
@Controller
public class DogListController {
	/** セッション. */
	@Autowired
	private SessionUser session;
	/** ロジック. */
	@Autowired
	private DogListLogic logic;

	/**
	 * 犬一覧画面初期表示.
	 * 
	 * @param model Model
	 * @return dogList
	 */
	@GetMapping("/dogList")
	public String index(DogListRequest req, Model model) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}
		List<DogListResponse> dogList = logic.createDogList(req);
		List<DogGroup> dogGroupList = logic.createDogGroupList();
		List<DogType> dogTypeList = logic.createDogTypeList(req.getDogGroup());
		model.addAttribute("req", req);
		model.addAttribute("dogList", dogList);
		model.addAttribute("dogGroupList", dogGroupList);
		model.addAttribute("dogTypeList", dogTypeList);
		model.addAttribute("sexEnum", DogSex.values());
		model.addAttribute("dogSizeEnum", DogSize.values());
		return "dogList";
	}

	/**
	 * 検索.
	 * 
	 * @param req   DogListRequest
	 * @param model Model
	 * @return dogList
	 */
	@GetMapping("/dogListSearch")
	public String search(DogListRequest req, Model model) {
		return "forward:/dogList";
	}
	
	@GetMapping("/serchDogTypeList")
	@ResponseBody
	public List<DogType> serchDogTypeList(@RequestParam String[] dogGroupArr) {
		List<String> dogGroup = Arrays.asList(dogGroupArr);
		return  logic.createDogTypeList(dogGroup);
	}
}
