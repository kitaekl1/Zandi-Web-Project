package com.assey.zandi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.service.AdminService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/zandi")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("/addProjectForm")
    public String addProjectForm() {
        logger.info("프로젝트 등록 페이지로 이동..");
        return "/zandiBookmark/addProjectForm";  // JSP 파일의 경로 반환
    }

    @PostMapping("/addProjectForm")
    public String projectRegi(ProjectVO proj, RedirectAttributes rttr, HttpSession session) {

        // 세션에서 로그인 ID를 가져와 prId에 설정
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            logger.error("로그인된 사용자의 ID를 가져오지 못했습니다.");
            return "redirect:/zandi/login";
        }
        proj.setPrId(loginID);

        // 문자열을 Timestamp로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        Timestamp startDate = Timestamp.valueOf(LocalDateTime.parse(proj.getPrStartdate(), formatter));
        Timestamp endDate = Timestamp.valueOf(LocalDateTime.parse(proj.getPrEnddate(), formatter));

        proj.setPrStartdate(startDate.toString());
        proj.setPrEnddate(endDate.toString());

        logger.info("Converted Start Date: " + startDate);
        logger.info("Converted End Date: " + endDate);

        adminService.projRegi(proj);

        rttr.addFlashAttribute("enroll_result", proj.getPrName());

        return "redirect:/zandiMainPage/MainPage.jsp"; 
    }
}