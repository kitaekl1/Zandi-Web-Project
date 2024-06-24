package com.assey.zandi.repository;

import com.assey.zandi.account.CfmemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CfmemberRepository extends JpaRepository<CfmemberVO, String> {
    CfmemberVO findMemberByMemId(String memId);
    boolean existsByMemId(String memId);
    boolean existsByMemNickname(String memNickname);
}