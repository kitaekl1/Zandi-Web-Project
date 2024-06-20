package com.assey.zandi.controller;

import com.assey.zandi.account.CfmemberVO;
import com.assey.zandi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
