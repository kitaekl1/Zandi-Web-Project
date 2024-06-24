package com.assey.zandi.service;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectVO> searchProjects(String searchText, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<ProjectVO> projectPage = projectRepository.findByPrNameContainingOrPrDescriptionContaining(searchText, searchText, pageable);
        return projectPage.getContent();
    }

    public int countSearchProjects(String searchText) {
        return (int) projectRepository.countByPrNameContainingOrPrDescriptionContaining(searchText, searchText);
    }
}