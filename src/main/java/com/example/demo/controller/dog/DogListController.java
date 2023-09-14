package com.example.demo.controller.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.session.SessionUser;

/**
 * 犬一覧コントローラー.
 */
@Controller
public class DogListController {
    /** セッション. */
    @Autowired
    private SessionUser session;
    /**
     * 犬一覧画面初期表示.
     * 
     * @param model Model
     * @return purchase
     */
    @GetMapping("/dog")
    public String index(Model model) {
        
        return "";
    }
    
}
