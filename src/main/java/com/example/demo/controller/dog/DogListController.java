package com.example.demo.controller.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constEnum.DogSex;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogInfoResponse;
import com.example.demo.dto.dog.DogListRequest;
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
	DogListLogic logic;

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
		List<DogInfoResponse> dogInfoList = logic.createDogList(req);
		List<DogGroup> dogGroupList = logic.createDogGroupList();
		List<DogType> dogTypeList = logic.createDogTypeList();
		model.addAttribute("req", req);
		model.addAttribute("dogList", dogInfoList);
		model.addAttribute("dogGroupList", dogGroupList);
		model.addAttribute("dogTypeList", dogTypeList);
		model.addAttribute("sexEnum", DogSex.values());
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

//        List<DogInfoResponse> dogList = logic.createDogList(req);
//        List<DogGroup> dogGroupList = logic.createDogGroupList();
//        
//        model.addAttribute("req", req);
//        model.addAttribute("dogList", dogList);
//        model.addAttribute("dogGroupList", dogGroupList);
//        model.addAttribute("sexEnum", DogSex.values());
		return "forward:/dogList";
	}
}
