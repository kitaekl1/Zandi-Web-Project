package com.assey.zandi.service;

import com.assey.zandi.account.CfmemberVO;
import com.assey.zandi.repository.CfmemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private CfmemberRepository cfmemberRepository;

    public void registerMember(CfmemberVO memberVO) {
        cfmemberRepository.save(memberVO);
    }

    public boolean isIdAvailable(String mId) {
        return !cfmemberRepository.existsByMId(mId);
    }

    public boolean isNicknameAvailable(String mNickname) {
        return !cfmemberRepository.existsByMNickname(mNickname);
    }
}