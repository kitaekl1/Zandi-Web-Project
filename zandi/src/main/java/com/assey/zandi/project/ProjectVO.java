package com.assey.zandi.project;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cf_project")
public class ProjectVO {

    @Id
    private int prCode;
    private String prName;
    private String prDescription;
    private String prTeam;
    private String prId;
    private String prCategory;
    private String prGoal;
    private String prCurrent;
    private int prLikecount;
    private java.sql.Timestamp prStartdate;
    private java.sql.Timestamp prEnddate;



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

    public java.sql.Timestamp getPrStartdate() {
        return prStartdate;
    }

    public void setPrStartdate(java.sql.Timestamp prStartdate) {
        this.prStartdate = prStartdate;
    }

    public java.sql.Timestamp getPrEnddate() {
        return prEnddate;
    }

    public void setPrEnddate(java.sql.Timestamp prEnddate) {
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
    
    
}
