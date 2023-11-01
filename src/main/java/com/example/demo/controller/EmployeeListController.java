package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.EmployeeListResponse;
import com.example.demo.logic.EmployeeListLogic;
import com.example.demo.service.EmployeeInfoService;
import com.example.demo.service.EmployeeStateService;
import com.example.demo.session.SessionUser;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 社員リストコントローラー.
 */
@Controller
public class EmployeeListController {

    /** セッション. */
    @Autowired
    private SessionUser session;
    /** 社員リストロジック. */
    @Autowired
    private EmployeeListLogic logic;

    /**
     * 一覧画面初期表示.
     * 
     * @param model Model
     * @return "employeeList"
     */
    @GetMapping("/list")
    public String index(Model model) {
        // セッションが切れていたらログイン画面へ遷移
        if (session.getLoginId() == null) {
            return "redirect:/login";
        }

        // 社員情報を取得
        List<EmployeeListResponse> employeeListRes = logic.getEmployeeListResponse();

        model.addAttribute("employeeListRes", employeeListRes);

        return "employeeList";
    }

    /**
     * 削除.
     * 
     * @param empId int
     * @return "redirect:/list"
     */
    @PostMapping("/delete")
    public String delete(int empId) {
        
        logic.delete(empId);
        
        // 削除後、一覧画面に戻る
        return "redirect:/list";
    }

}
