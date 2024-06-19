package com.assey.zandi.controller;

import com.assey.zandi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원 탈퇴 처리
    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public String deleteMember(String id, String password, Model model) {
        int result = memberService.deleteMember(id, password);
        if (result == 1) {
            model.addAttribute("message", "회원 탈퇴 성공");
            return "redirect:/login";
        } else if (result == 0) {
            model.addAttribute("message", "비밀번호 틀림");
        } else {
            model.addAttribute("message", "아이디가 존재하지 않음");
        }
        return "deleteForm";
    }

 
}