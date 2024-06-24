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
        int pageSize = 8; 
        int totalProjects = projectService.countSearchProjects(searchText); 
        int pageCount = (int) Math.ceil((double) totalProjects / pageSize);

        int offset = (pageNum - 1) * pageSize;
        List<ProjectVO> projectList = projectService.searchProjects(searchText, offset, pageSize);

        
        for (ProjectVO project : projectList) {
            if (!isNumeric(project.getPrGoal())) {
                project.setPrGoal(null);
            }
            if (!isNumeric(project.getPrCurrent())) {
                project.setPrCurrent(null);
            }
        }

      
        int pageGroupSize = 5;
        int currentGroup = (int) Math.ceil((double) pageNum / pageGroupSize);
        int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
        int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, pageCount);

        model.addAttribute("projectList", projectList);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("groupStartPage", groupStartPage);
        model.addAttribute("groupEndPage", groupEndPage);
        model.addAttribute("searchText", searchText);
        return "zandiProject/projectList";
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}