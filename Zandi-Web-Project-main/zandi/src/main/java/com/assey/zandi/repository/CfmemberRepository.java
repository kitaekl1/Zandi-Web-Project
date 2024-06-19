package com.assey.zandi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.assey.zandi.account.cfmemberVO;

@Repository
public interface CfmemberRepository extends JpaRepository<cfmemberVO, String> {
    boolean existsBymId(String mId);
}