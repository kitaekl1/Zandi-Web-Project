package com.assey.zandi.repository;

import com.assey.zandi.project.ProjectVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectVO, Long> {
    Page<ProjectVO> findByPrNameContainingOrPrDescriptionContaining(String prName, String prDescription, Pageable pageable);
    long countByPrNameContainingOrPrDescriptionContaining(String prName, String prDescription);
}
