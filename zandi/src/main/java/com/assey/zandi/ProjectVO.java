package com.assey.zandi;

import java.sql.*;

public class ProjectVO {


	    private int prCode;//프로젝트 번호
	    private String prName;//프로젝트 명
	    private String prDescription;//프로젝트 설명
	    private String prTeam;//팀 
	    private String prId;//아이디
	    private String prCategoryCode;//카테고리 코드
	    private String prCategory;//카테고리
	    private String prGoal;//목표금액
		private String prCurrent;//현제 금액
	    private int prLikecount;//찜 수
	    private Timestamp prStartdate;//후원 시작일
	    private Timestamp prEnddate;//후원 종료일
	    
	    
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
		
		public String getPrCategoryCode() {
			return prCategoryCode;
		}
		public void setPrCategoryCode(String prCategoryCode) {
			this.prCategoryCode = prCategoryCode;
		}
		//목표금액
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

		@Override
		public String toString() {
			return "ProjectVO [prCode=" + prCode + ", prName=" + prName + ", prDescription= " + prDescription + ", prTeam="
					+ prTeam + ", prId= "+ prId + ", prCategory= "+ prCategory + ", prCategoryCode= "+ prCategoryCode +", prGoal= "+ prGoal + ", prCurrent= "
					+ prCurrent + ", prLikecount "+prLikecount+"]";
			
		}
}
