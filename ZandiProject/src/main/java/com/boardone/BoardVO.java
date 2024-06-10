package com.boardone;

import java.sql.Date;
import java.sql.Timestamp;

public class BoardVO {
	
	private int aNum;
	private String aTitle;
	private String aDate;
	private String aAnnounce;
	private int prCode;
	private String pass;
	private int readcount;
	
	public int getaNum() {
		return aNum;
	}
	public void setaNum(int i) {
		this.aNum = i;
	}
	public String getaTitle() {
		return aTitle;
	}
	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}
	public String getaDate() {
		return aDate;
	}
	public void setaDate(String aDate) {
		this.aDate = aDate;
	}
	public String getaAnnounce() {
		return aAnnounce;
	}
	public void setaAnnounce(String aAnnounce) {
		this.aAnnounce = aAnnounce;
	}
	public int getPrCode() {
		return prCode;
	}
	public void setPrCode(int prCode) {
		this.prCode = prCode;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
}
