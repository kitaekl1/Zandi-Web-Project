package com.assey.zandi.controller;

import com.assey.zandi.account.CfmemberVO;
import com.assey.zandi.service.AccountService;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.*;

@Controller
@RequestMapping("/zandi")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private DataSource dataSource;

    @GetMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam("id") String id) {
        return accountService.isIdAvailable(id);
    }

    @GetMapping("/nickCheck")
    @ResponseBody
    public boolean nickCheck(@RequestParam("mNickname") String mNickname) {
        return accountService.isNicknameAvailable(mNickname);
    }

    @PostMapping("/register")
    public String register(CfmemberVO memberVO, Model model) {
        try {
            accountService.registerMember(memberVO);
            model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            model.addAttribute("message", "회원가입에 실패하였습니다.");
        }
        return "zandiaccount/result";
    }
    
    
    // 회원 탈퇴 메서드 추가
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String showDeleteAccountPage() {
        return "zandiaccount/deleteAccount";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("password") String password, HttpSession session, Model model) {
        String loginID = (String) session.getAttribute("loginID");

        if (loginID != null) {
            CfmemberVO loginMember = getMemberById(loginID);
            if (loginMember != null && loginMember.getMPw().equals(password)) {
                if (deleteAccountFromDatabase(loginID)) {
                    session.invalidate(); // 세션 무효화
                    model.addAttribute("message", "회원 탈퇴가 성공적으로 완료되었습니다.");
                } else {
                    model.addAttribute("message", "회원 탈퇴에 실패했습니다.");
                }
            } else {
                model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            }
        } else {
            model.addAttribute("message", "로그인 상태가 아닙니다.");
        }
        return "zandiaccount/result";
    }

    private CfmemberVO getMemberById(String id) {
        String query = "SELECT * FROM cf_member WHERE mId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CfmemberVO member = new CfmemberVO();
                    member.setMId(resultSet.getString("mId"));
                    member.setMPw(resultSet.getString("mPw"));
                    member.setMNickname(resultSet.getString("mNickname"));
                    member.setMName(resultSet.getString("mName"));
                    member.setMPhone(resultSet.getString("mPhone"));
                    member.setMMail(resultSet.getString("mMail"));
                    member.setMPost(resultSet.getString("mPost"));
                    member.setMAddress(resultSet.getString("mAddress"));
                    member.setMSaddress(resultSet.getString("mSaddress"));
                    return member;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean deleteAccountFromDatabase(String mId) {
        String query = "DELETE FROM cf_member WHERE mId = ?";
        		try (Connection connection = dataSource.getConnection();
        				PreparedStatement statement = connection.prepareStatement(query)) {
        				statement.setString(1, mId);
        				int rowsAffected = statement.executeUpdate();
        				return rowsAffected > 0;
        				} catch (SQLException e) {
        				e.printStackTrace();
        				}
        				return false;
        			}
}
