package com.assey.zandi.service;

import com.assey.zandi.account.CfmemberVO;
import com.assey.zandi.repository.CfmemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private CfmemberRepository cfmemberRepository;

    public void deleteAccount(String memId) {
        cfmemberRepository.deleteById(memId);
    }

    public boolean checkPassword(String memId, String password) {
        CfmemberVO member = cfmemberRepository.findById(memId).orElse(null);
        return member != null && member.getMemPw().equals(password);
    }

    public void updateMember(CfmemberVO memberVO) {
        cfmemberRepository.save(memberVO);
    }

    public CfmemberVO getMemberById(String memId) {
        return cfmemberRepository.findMemberByMemId(memId);
    }

    public void registerMember(CfmemberVO memberVO) {
        cfmemberRepository.save(memberVO);
    }

    public boolean isIdAvailable(String memId) {
        return !cfmemberRepository.existsByMemId(memId);
    }

    public boolean isNicknameAvailable(String memNickname) {
        return !cfmemberRepository.existsByMemNickname(memNickname);
    }
}