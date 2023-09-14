package com.example.demo.session;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.servlet.http.HttpSession;
import lombok.Data;

/**
 * セッション.
 */
@Data
@Component
@SessionScope
public class SessionUser implements Serializable{
    /** HttpSession. */
    @Autowired
    private HttpSession httpSession;
    
    private static final long serialVersionUID = 1L;
    /** メッセージ. */
    private String msg;
    /** ログインID. */
    private String loginId;
    /** パスワード. */
    private String password;

}
