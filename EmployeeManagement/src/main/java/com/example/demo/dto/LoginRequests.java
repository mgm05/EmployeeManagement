package com.example.demo.dto;

import lombok.Data;

/**
 * ログイン画面用リクエストDto.
 */
@Data
public class LoginRequests {
    /** ログインID. */
    private String loginId;
    /** パスワード. */
    private String password;

}
