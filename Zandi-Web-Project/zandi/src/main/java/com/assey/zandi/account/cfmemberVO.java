package com.assey.zandi.account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cf_member")
public class CfmemberVO {

    @Id
    private String memId;

    private String memPw;
    private String memNickname;
    private String memName;
    private String memPhone;
    private String memMail;
    private String memPost;
    private String memAddress;
    private String memSaddress;

    // Getters and Setters
    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPw() {
        return memPw;
    }

    public void setMemPw(String memPw) {
        this.memPw = memPw;
    }

    public String getMemNickname() {
        return memNickname;
    }

    public void setMemNickname(String memNickname) {
        this.memNickname = memNickname;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemPhone() {
        return memPhone;
    }

    public void setMemPhone(String memPhone) {
        this.memPhone = memPhone;
    }

    public String getMemMail() {
        return memMail;
    }

    public void setMemMail(String memMail) {
        this.memMail = memMail;
    }

    public String getMemPost() {
        return memPost;
    }

    public void setMemPost(String memPost) {
        this.memPost = memPost;
    }

    public String getMemAddress() {
        return memAddress;
    }

    public void setMemAddress(String memAddress) {
        this.memAddress = memAddress;
    }

    public String getMemSaddress() {
        return memSaddress;
    }

    public void setMemSaddress(String memSaddress) {
        this.memSaddress = memSaddress;
    }

    @Override
    public String toString() {
        return "CfmemberVO{" +
                "memId='" + memId + '\'' +
                ", memPw='" + memPw + '\'' +
                ", memNickname='" + memNickname + '\'' +
                ", memName='" + memName + '\'' +
                ", memPhone='" + memPhone + '\'' +
                ", memMail='" + memMail + '\'' +
                ", memPost='" + memPost + '\'' +
                ", memAddress='" + memAddress + '\'' +
                ", memSaddress='" + memSaddress + '\'' +
                '}';
    }
}