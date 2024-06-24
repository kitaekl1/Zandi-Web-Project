package com.assey.zandi.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cf_project")
public class ProjectVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prCode;
    private String prName;
    private String prDescription;
    private String prTeam;
    private String prId;
    private String prCategory;
    private String prGoal;
    private String prCurrent ="0";
    private int prLikecount =0;
    private String prStartdate;
    private String prEnddate; 

    // Getters and Setters


    public String getPrCurrent() {
        return prCurrent;
    }

    public void setPrCurrent(String prCurrent) {
        this.prCurrent = prCurrent;
    }

    public int getPrCode() {
        return prCode;
    }

    public void setPrCode(int prCode) {
        this.prCode = prCode;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getPrDescription() {
        return prDescription;
    }

    public void setPrDescription(String prDescription) {
        this.prDescription = prDescription;
    }

    public String getPrTeam() {
        return prTeam;
    }

    public void setPrTeam(String prTeam) {
        this.prTeam = prTeam;
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getPrCategory() {
        return prCategory;
    }

    public void setPrCategory(String prCategory) {
        this.prCategory = prCategory;
    }

    public String getPrGoal() {
        return prGoal;
    }

    public void setPrGoal(String prGoal) {
        this.prGoal = prGoal;
    }

    public int getPrLikecount() {
        return prLikecount;
    }

    public void setPrLikecount(int prLikecount) {
        this.prLikecount = prLikecount;
    }

    public String getPrStartdate() {
        return prStartdate;
    }

    public void setPrStartdate(String prStartdate) {
        this.prStartdate = prStartdate;
    }

    public String getPrEnddate() {
        return prEnddate;
    }

    public void setPrEnddate(String prEnddate) {
        this.prEnddate = prEnddate;
    }

    public void incrementLikecount() {
        this.prLikecount++;
    }

    public void decrementLikecount() {
        if (this.prLikecount > 0) {
            this.prLikecount--;
        }
    }
    
    @Override
    public String toString() {
        return "ProjectVO [prCode=" + prCode + ", prName=" + prName + ", prDescription= " + prDescription + ", prTeam="
                + prTeam + ", prId= "+ prId + ", prCategory= "+ prCategory + ", prGoal= "+ prGoal + ", prCurrent= "
                + prCurrent + ", prLikecount "+prLikecount+", prStartdate="+prStartdate+", prEnddate="+prEnddate+"]";
    }
}