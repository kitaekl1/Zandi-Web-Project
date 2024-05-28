package com.zandiproject;

import java.sql.*;

public class ProjectVO {


	    private int prCode;
	    private String prName;
	    private String prDescription;
	    private String prTeam;
	    private String prId;
	    private String prCategory;
	    private String prGoal;
		private String prCurrent;
	    private int prLikecount;
	    private Timestamp prStartdate;
	    private Timestamp prEnddate;
	    
	    
	    public Timestamp getPrStartdate() {
			return prStartdate;
		}
		public void setPrStartdate(Timestamp prStartdate) {
			this.prStartdate = prStartdate;
		}
		public Timestamp getPrEnddate() {
			return prEnddate;
		}
		public void setPrEnddate(Timestamp prEnddate) {
			this.prEnddate = prEnddate;
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
		public String getPrCurrent() {
			return prCurrent;
		}
		public void setPrCurrent(String prCurrent) {
			this.prCurrent = prCurrent;
		}
		public int getPrLikecount() {
			return prLikecount;
		}
		public void setPrLikecount(int prLikecount) {
			this.prLikecount = prLikecount;
		}


}
