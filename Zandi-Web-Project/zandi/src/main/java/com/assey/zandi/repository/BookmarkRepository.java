package com.assey.zandi.repository;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.bookmark.LikelistVO;
import com.assey.zandi.bookmark.LikelistVO.LikelistId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<LikelistVO, LikelistId> {

    @Query("SELECT p FROM ProjectVO p WHERE p.prCode IN (SELECT l.id.prCode FROM LikelistVO l WHERE l.id.memId = :loginID)")
    List<ProjectVO> findBookmarkedProjectsByUserId(@Param("loginID") String loginID, Pageable pageable);

    @Query("SELECT COUNT(l) FROM LikelistVO l WHERE l.id.memId = :loginID")
    int countBookmarkedProjectsByUserId(@Param("loginID") String loginID);

    @Modifying
    @Transactional
    @Query("DELETE FROM LikelistVO l WHERE l.id.prCode = :prCode AND l.id.memId = :memId")
    void deleteByPrCodeAndMId(@Param("prCode") int prCode, @Param("memId") String memId);
    
    @Query("SELECT p FROM ProjectVO p WHERE p.prCode = :prCode")
    ProjectVO findProjectByPrCode(@Param("prCode") int prCode);
}