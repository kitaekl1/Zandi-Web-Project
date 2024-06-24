package com.assey.zandi.controller;

import com.assey.zandi.account.CfmemberVO;
import com.assey.zandi.service.AccountService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/zandi")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam("id") String id) {
        return accountService.isIdAvailable(id);
    }

    @GetMapping("/nickCheck")
    @ResponseBody
    public boolean nickCheck(@RequestParam("memNickname") String memNickname) {
        return accountService.isNicknameAvailable(memNickname);
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

    @GetMapping("/deleteAccount")
    public String showDeleteAccountPage() {
        return "zandiaccount/deleteAccount";
    }

    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("password") String password, HttpSession session, Model model) {
        String loginID = (String) session.getAttribute("loginID");

        if (loginID != null) {
            CfmemberVO loginMember = accountService.getMemberById(loginID);
            if (loginMember != null && loginMember.getMemPw().equals(password)) {
                accountService.deleteAccount(loginID);
                session.invalidate(); // 세션 무효화
                model.addAttribute("message", "회원 탈퇴가 성공적으로 완료되었습니다.");
            } else {
                model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            }
        } else {
            model.addAttribute("message", "로그인 상태가 아닙니다.");
        }
        return "zandiaccount/result";
    }

    @GetMapping("/updateProfile")
    public String showUpdateProfilePage(HttpSession session, Model model) {
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return "redirect:/login";
        }
        CfmemberVO member = accountService.getMemberById(loginID);
        if (member == null) {
            model.addAttribute("message", "회원 정보를 불러오지 못했습니다.");
            return "zandiaccount/result";
        }

        model.addAttribute("member", member);
        return "zandiaccount/updateProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("member") CfmemberVO memberVO, HttpSession session, Model model) {
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return "redirect:/login";
        }
        memberVO.setMemId(loginID);
        try {
            accountService.updateMember(memberVO);
            model.addAttribute("message", "회원 정보가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            model.addAttribute("message", "회원 정보 수정에 실패하였습니다.");
        }
        return "zandiaccount/result";
    }
}