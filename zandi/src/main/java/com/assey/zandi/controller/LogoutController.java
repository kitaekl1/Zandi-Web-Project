package com.assey.zandi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/zandiMainPage/MainPage.jsp"; // 로그아웃 후 메인 페이지로 리디렉션
    }
}
