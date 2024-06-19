package com.assey.zandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/zandi")
public class LoginControl {

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(HttpSession session) {
        String loginID = (String) session.getAttribute("loginID");
        if (loginID != null) {
            // 이미 로그인 된 경우 mainPage로 리다이렉트
            return "redirect:/zandi/mainPage";
        }
        return "zandiaccount/login"; // "zandiaccount/login.jsp"를 렌더링합니다.
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@RequestParam("id") String id,
                              @RequestParam("pass") String pass,
                              Model model,
                              HttpSession session) {
        // 아이디가 비어 있는지 확인
        if (id == null || id.trim().isEmpty()) {
            model.addAttribute("errorMessage", "아이디를 입력해주세요.");
            return "zandiaccount/login";
        }

        // 비밀번호가 비어 있는지 확인
        if (pass == null || pass.trim().isEmpty()) {
            model.addAttribute("errorMessage", "비밀번호를 입력해주세요.");
            return "zandiaccount/login";
        }

        // 데이터베이스에서 사용자 인증
        int authResult = authenticateUser(id, pass);
        if (authResult == 1) {
            session.setAttribute("loginID", id); // 로그인 성공 시 세션에 사용자 ID 저장
            return "redirect:/zandi/mainPage"; // 성공 시 메인 페이지로 리다이렉트
        } else if (authResult == -1) {
            model.addAttribute("errorMessage", "아이디가 올바르지 않습니다.");
        } else if (authResult == -2) {
            model.addAttribute("errorMessage", "비밀번호가 올바르지 않습니다.");
        } else {
            model.addAttribute("errorMessage", "로그인에 실패했습니다.");
        }
        return "zandiaccount/login"; // 인증 실패 시 다시 로그인 페이지로
    }

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public String showMainPage(HttpSession session, Model model) {
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            // 로그인 되어 있지 않으면 로그인 페이지로 리다이렉트
            return "redirect:/zandi/login";
        }
        model.addAttribute("loginID", loginID);
        return "zandiMainPage/MainPage"; // 메인 페이지로 이동
    }

    // 사용자 인증 메서드
    private int authenticateUser(String id, String pass) {
        String query = "SELECT mPw FROM cf_member WHERE mId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String dbPassword = resultSet.getString("mPw");
                    if (dbPassword.equals(pass)) {
                        return 1; // 인증 성공
                    } else {
                        return -2; // 비밀번호가 틀림
                    }
                } else {
                    return -1; // 아이디가 존재하지 않음
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // 인증 실패
    }
}