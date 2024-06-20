package com.assey.zandi.controller;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zandi")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/search")
    public String searchProjects(@RequestParam("searchText") String searchText, Model model) {
        List<ProjectVO> projectList = projectService.searchProjects(searchText);
        model.addAttribute("projectList", projectList);
        return "zandiProject/projectList";
    }
}
