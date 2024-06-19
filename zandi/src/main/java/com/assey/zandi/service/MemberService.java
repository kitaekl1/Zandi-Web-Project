package com.assey.zandi.service;

import com.assey.zandi.repository.CfmemberRepository;
import com.assey.zandi.account.cfmemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private CfmemberRepository cfmemberRepository;

    public boolean idCheck(String id) {
        return cfmemberRepository.existsByMId(id);
    }

    public boolean nickCheck(String nickname) {
        return cfmemberRepository.existsByMNickname(nickname);
    }

    public List<cfmemberVO> findByDong(String dong) {
        return null;
    }

    public boolean saveMember(cfmemberVO member) {
        cfmemberRepository.save(member);
        return true;
    }

    public int loginCheck(String id, String pass) {
        cfmemberVO member = cfmemberRepository.findByMId(id);
        if (member == null) {
            return -1; // 아이디가 존재하지 않음
        }
        return member.getMPassword().equals(pass) ? 1 : 0; // 비밀번호 체크
    }
}