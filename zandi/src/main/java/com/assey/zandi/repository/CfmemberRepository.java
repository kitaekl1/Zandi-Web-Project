package com.assey.zandi.repository;

import com.assey.zandi.account.CfmemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CfmemberRepository extends JpaRepository<CfmemberVO, String> {
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM CfmemberVO m WHERE m.mId = :mId")
    boolean existsByMId(@Param("mId") String mId);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM CfmemberVO m WHERE m.mNickname = :mNickname")
    boolean existsByMNickname(@Param("mNickname") String mNickname);
}
