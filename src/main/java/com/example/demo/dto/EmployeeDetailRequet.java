package com.example.demo.dto;

import org.hibernate.validator.constraints.Length;

import customvalidation.DateYMD;
import customvalidation.Zenkaku;
import customvalidation.ZenkakuHiragana;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 社員詳細画面用リクエストDto.
 */
@Data
public class EmployeeDetailRequet {
    /** 社員ID. */
    private String employeeId;
    /** 氏名. */
    @NotBlank(message="氏名を入力して下さい。")
    @Length(min=0, max=20, message="氏名は20文字以内で入力して下さい。")
    @Zenkaku(message = "氏名は全角で入力して下さい。")
    private String name;
    /** 氏名ひらがな. */
    @NotBlank(message="氏名(ひらがな)を入力して下さい。")
    @Length(min=0, max=20, message="氏名(ひらがな)は20文字以内で入力して下さい。")
    @ZenkakuHiragana(message="氏名(ひらがな)は全角ひらがなで入力して下さい")
    private String nameHiragana;
    /** 生年月日. */
    @NotBlank(message="生年月日を入力して下さい。")
    @Length(min=0, max=10, message="生年月日は10文字以内で入力して下さい。")
    @DateYMD(message = "生年月日はYYYY/MM/DDの書式で入力して下さい。")
    private String birthday;
    /** 性別. */
    @NotNull(message="性別を選択して下さい。")
    private String sex;
    /** メールアドレス. */
    @NotBlank(message="メールアドレスを入力して下さい。")
    @Length(min=0, max=50, message="メールアドレスは50文字以内で入力して下さい。")
    @Email(message="メールアドレスは半角英数字記号で入力して下さい。")
    private String mailAddress;
    /** 電話番号. */
    @NotBlank(message="電話番号を入力して下さい。")
    @Length(min=0, max=11, message="電話番号は11文字以内で入力して下さい。")
    @Pattern(regexp = "^[0-9]*$", message="電話番号は半角数字で入力して下さい。")
    private String telephoneNumber;
    /** 所属会社ID. */
    @NotBlank(message="所属会社を選択して下さい。")
    private String companyId;
    /** 担当管理営業. */
    @NotBlank(message="担当管理営業を入力して下さい。")
    @Length(min=0, max=20, message="担当管理営業は20文字以内で入力して下さい。")
    @Zenkaku(message = "担当管理営業は全角で入力して下さい。")
    private String businessManager;
    /** 事業部. */
    @NotBlank(message="事業部を選択して下さい。")
    private String department;
    /** 稼働状況. */
    @NotNull(message="稼働状況を選択して下さい。")
    private String commissioningStatus;
    /** 入社日. */
    @NotBlank(message="入社日を入力して下さい。")
    @Length(min=0, max=10, message="入社日は10文字以内で入力して下さい。")
    @DateYMD(message="入社日はYYYY/MM/DDの書式で入力して下さい。")
    private String hireDate;
    /** 退職日. */
    @Length(min=0, max=10, message="退職日は10文字以内で入力して下さい。")
    @DateYMD(message="退職日はYYYY/MM/DDの書式で入力して下さい。")
    private String retireDate;
    /** ステータス. */
    @NotBlank(message="ステータスを選択して下さい。")
    private String status;
}
