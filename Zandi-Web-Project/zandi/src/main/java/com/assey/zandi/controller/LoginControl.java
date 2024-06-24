package com.assey.zandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assey.zandi.service.AccountService;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.*;

@Controller
@RequestMapping("/zandi")
public class LoginControl {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(HttpSession session) {
        String loginID = (String) session.getAttribute("loginID");
        if (loginID != null) {
            
            return "redirect:/zandi/mainPage";
        }
        return "zandiaccount/login"; 
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@RequestParam("id") String id,
                              @RequestParam("pass") String pass,
                              Model model,
                              HttpSession session) {
        
        if (id == null || id.trim().isEmpty()) {
            model.addAttribute("errorMessage", "아이디를 입력해주세요.");
            return "zandiaccount/login";
        }

        
        if (pass == null || pass.trim().isEmpty()) {
            model.addAttribute("errorMessage", "비밀번호를 입력해주세요.");
            return "zandiaccount/login";
        }

        
        int authResult = authenticateUser(id, pass);
        if (authResult == 1) {
            session.setAttribute("loginID", id); 
            return "redirect:/zandi/mainPage"; 
        } else if (authResult == -1) {
            model.addAttribute("errorMessage", "아이디를 확이해주세요");
        } else if (authResult == -2) {
            model.addAttribute("errorMessage", "비밀번호를 확인해주세요.");
        } else {
            model.addAttribute("errorMessage", "아이디와 비밀번호를 확인해주세요.");
        }	
        return "zandiaccount/login"; 
    }

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public String showMainPage(HttpSession session, Model model) {
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
           
            return "redirect:/zandi/login";
        }
        model.addAttribute("loginID", loginID);
        return "zandiMainPage/MainPage"; 
    }

    
    private int authenticateUser(String id, String pass) {
        String query = "SELECT memPw FROM cf_member WHERE memId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String dbPassword = resultSet.getString("memPw");
                    if (dbPassword.equals(pass)) {
                        return 1; 
                    } else {
                        return -2; 
                    }
                } else {
                    return -1; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }
}