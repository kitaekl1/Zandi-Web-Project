package com.assey.zandi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.assey.zandi.project.ProjectVO;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectVO, Integer> {
    List<ProjectVO> findByPrNameContainingOrPrDescriptionContaining(String prName, String prDescription);
}
