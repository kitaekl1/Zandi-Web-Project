package com.assey.zandi.controller;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/zandi")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    // 북마크 리스트 조회
    @GetMapping("/bookmarkList")
    public String showBookmarkList(HttpSession session,
                                   @RequestParam(value = "pageNum", defaultValue = "1") String pageNumStr,
                                   Model model) {

        String loginID = (String) session.getAttribute("loginID"); // 세션에서 로그인된 사용자의 ID를 가져옴

        if (loginID == null) {
            return "redirect:/zandiaccount/login.jsp"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        }

        int pageSize = 2;
        int pageNum;

        try {
            pageNum = Integer.parseInt(pageNumStr);
        } catch (NumberFormatException e) {
            pageNum = 1; // 페이지 번호가 유효하지 않은 경우 기본값 설정
        }

        int startRow = (pageNum - 1) * pageSize;

        List<ProjectVO> bookmarkedProjects = bookmarkService.getBookmarkedProjects(loginID, startRow, pageSize);
        int totalBookmarks = bookmarkService.getBookmarkedProjectCount(loginID);
        int pageCount = (int) Math.ceil((double) totalBookmarks / pageSize);

        // 페이징 그룹 설정
        int pageGroupSize = 5;
        int currentGroup = (int) Math.ceil((double) pageNum / pageGroupSize);
        int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
        int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, pageCount);

        model.addAttribute("bookmarkedProjects", bookmarkedProjects);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("groupStartPage", groupStartPage);
        model.addAttribute("groupEndPage", groupEndPage);

        return "zandiBookmark/bookmarkList"; // 북마크 리스트 뷰 페이지로 이동
    }

    // 북마크 삭제
    @PostMapping("/bookmark/delete")
    public String deleteBookmark(@RequestParam("prCode") int prCode, HttpSession session) {
        String loginID = (String) session.getAttribute("loginID");

        if (loginID == null) {
            return "redirect:/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        }

        // 북마크 삭제 로직 수행
        bookmarkService.deleteBookmark(prCode, loginID);

        // 삭제 후 다시 북마크 리스트 페이지로 리다이렉트
        return "redirect:/zandi/bookmarkList";
    }
}