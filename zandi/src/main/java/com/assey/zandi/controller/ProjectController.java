package com.assey.zandi.controller;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/zandi")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/search")
    public String searchProjects(@RequestParam("searchText") String searchText,
                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                 Model model) {
        int pageSize = 1; // 페이지 당 프로젝트 수
        int totalProjects = projectService.countSearchProjects(searchText); // 총 프로젝트 수
        int pageCount = (int) Math.ceil((double) totalProjects / pageSize);

        int offset = (pageNum - 1) * pageSize;
        List<ProjectVO> projectList = projectService.searchProjects(searchText, offset, pageSize);
        model.addAttribute("projectList", projectList);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("searchText", searchText);
        return "zandiProject/projectList";
    }
}
