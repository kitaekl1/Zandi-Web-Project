package com.assey.zandi.service;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectVO> searchProjects(String searchText) {
        return projectRepository.findByPrNameContainingOrPrDescriptionContaining(searchText, searchText);
    }
}
