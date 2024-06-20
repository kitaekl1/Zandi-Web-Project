package com.assey.zandi.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cf_member")
public class CfmemberVO {

    @Id
    private String mId;
    
    private String mPw;
    private String mNickname;
    private String mName;
    private String mPhone;
    private String mMail;
    private String mPost;
    private String mAddress;
    private String mSaddress;

    // Getters and Setters
    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getMPw() {
        return mPw;
    }

    public void setMPw(String mPw) {
        this.mPw = mPw;
    }

    public String getMNickname() {
        return mNickname;
    }

    public void setMNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMPhone() {
        return mPhone;
    }

    public void setMPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getMMail() {
        return mMail;
    }

    public void setMMail(String mMail) {
        this.mMail = mMail;
    }

    public String getMPost() {
        return mPost;
    }

    public void setMPost(String mPost) {
        this.mPost = mPost;
    }

    public String getMAddress() {
        return mAddress;
    }

    public void setMAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getMSaddress() {
        return mSaddress;
    }

    public void setMSaddress(String mSaddress) {
        this.mSaddress = mSaddress;
    }
}
