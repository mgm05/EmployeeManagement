package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constEnum.CommissioningStatus;
import com.example.demo.constEnum.Department;
import com.example.demo.constEnum.Sex;
import com.example.demo.constEnum.Status;
import com.example.demo.dto.EmployeeDetailRequet;
import com.example.demo.dto.EmployeeDetailResponse;
import com.example.demo.entity.CompanyInfoEntity;
import com.example.demo.logic.EmployeeDetailLogic;
import com.example.demo.session.SessionUser;

/**
 * 社員詳細コントローラー.
 */
@Controller
public class EmployeeDetailController {
	/** セッション. */
	@Autowired
	private SessionUser session;
	/** 社員詳細ロジック. */
	@Autowired
	private EmployeeDetailLogic logic;
	/** 定数. */
	private static final String ERROR_LIST = "errorList";
	/** 定数. */
	private static final String OLD_REQ = "oldReq";

	/**
	 * 詳細画面初期表示.
	 * 
	 * @param empId        String
	 * @param model        Model
	 * @param modelAndView ModelAndView
	 * @return "employeeDetail"
	 */
	@GetMapping("/detail")
	public String index(String empId, Model model, ModelAndView modelAndView) {

		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}
		// empIdがあったら更新画面表示
		EmployeeDetailResponse employeeDetailRes = new EmployeeDetailResponse();
		if (StringUtils.isNotEmpty(empId)) {
			employeeDetailRes = logic.getEmployeeDetailResponse(empId);
		}

		// 会社リスト取得
		List<CompanyInfoEntity> companyList = logic.getCompanyList();

		// 定数、レスポンスDtoをモデルに追加
		model.addAttribute("sexEnum", Sex.values());
		model.addAttribute("departmentEnum", Department.values());
		model.addAttribute("commissioningStatusEnum", CommissioningStatus.values());
		model.addAttribute("statusEnum", Status.values());
		model.addAttribute("companylist", companyList);
		model.addAttribute("employeeDetailRes", employeeDetailRes);

		// postメソッドからのリダイレクト時、エラーメッセージとユーザーが入力した値をモデルに追加
		// @SuppressWarnings("unchecked")
		// List<String> errList = (List<String>) model.getAttribute(ERROR_LIST);
		// EmployeeDetailRequet oldReq = (EmployeeDetailRequet)
		// model.getAttribute(OLD_REQ);
		modelAndView.addObject(ERROR_LIST, model.getAttribute(ERROR_LIST));
		modelAndView.addObject(OLD_REQ, model.getAttribute(OLD_REQ));

		return "employeeDetail";
	}

	/**
	 * 新規登録・更新.
	 * 
	 * @param req                EmployeeDetailRequet
	 * @param result             BindingResult
	 * @param redirectAttributes RedirectAttributes
	 * @return "redirect:/list"
	 */
	@PostMapping("/detail")
	public String regist(@ModelAttribute @Validated EmployeeDetailRequet req, BindingResult result,
			RedirectAttributes redirectAttributes) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}

		// バリデーションエラーがあったらエラーメッセージとユーザーが入力した値を送る
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}

			redirectAttributes.addFlashAttribute(ERROR_LIST, errorList);
			redirectAttributes.addFlashAttribute(OLD_REQ, req);
			String empId = req.getEmployeeId();
			return "redirect:/detail" + (StringUtils.isEmpty(empId) ? StringUtils.EMPTY : "?empId=" + empId);
		}

		// 登録、更新処理
		if (logic.isRegist(req)) {
			logic.regist(req, session.getLoginId());
		} else {
			logic.update(req, session.getLoginId());
		}

		// 登録、更新後一覧画面へ戻る
		return "redirect:/list";
	}
}
