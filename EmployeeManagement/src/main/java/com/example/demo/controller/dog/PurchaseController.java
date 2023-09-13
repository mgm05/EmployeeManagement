package com.example.demo.controller.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constEnum.ContractType;
import com.example.demo.constEnum.Sex;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogType;
import com.example.demo.dto.dog.PuchaseRequest;
import com.example.demo.logic.dog.PurchaseLogic;
import com.example.demo.mapper.DogGroupMapper;
import com.example.demo.service.dog.DogGroupService;
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
    PurchaseLogic purchaseLogic;

    /**
     * 仕入れ画面初期表示.
     * 
     * @param model Model
     * @return purchase
     */
    @GetMapping("/purchase")
    public String index(Model model) {
        List<DogGroup> dogGroupList = purchaseLogic.createDogGroupList();
        List<DogType> dogTypeList = purchaseLogic.createDogTypeList();
        
        model.addAttribute("dogGroupList", dogGroupList);
        model.addAttribute("dogTypeList", dogTypeList);
        model.addAttribute("sexEnum", Sex.values());
        model.addAttribute("contractTypeEnum", ContractType.values());
        return "purchase";
    }
    
    /**
     * 仕入れ登録.
     * @param puchaseRequest PuchaseRequest
     * @return purchase
     */
    @PostMapping("/purchaseRegist")
    public String regist(PuchaseRequest puchaseRequest) {
        purchaseLogic.regist(puchaseRequest, session.getLoginId());
        return "redirect:/purchase";
    }
}
