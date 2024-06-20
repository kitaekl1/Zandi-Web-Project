package com.assey.zandi.service;

import com.assey.zandi.account.CfmemberVO;
import com.assey.zandi.repository.CfmemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private CfmemberRepository cfmemberRepository;

    public void deleteAccount(String mId) {
    	cfmemberRepository.deleteById(mId);
    }

    public boolean checkPassword(String mId, String password) {
        CfmemberVO member = cfmemberRepository.findById(mId).orElse(null);
        return member != null && member.getMPw().equals(password);
    }
    
    
    
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
