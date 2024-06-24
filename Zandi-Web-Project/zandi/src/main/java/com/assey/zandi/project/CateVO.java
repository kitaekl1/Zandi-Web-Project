package com.assey.zandi.project;


public class CateVO {
	public int seeAll;
	private String prCategoryName;
	private String prCategoryCode;
	private String prCategoryParent;
	public int getSeeAll() {
		return seeAll;
	}	
	public void setSeeAll(int seeAll) {
		this.seeAll = seeAll;
	}
	public String getPrCategoryName() {
		return prCategoryName;
	}
	public void setPrCategoryName(String prCategoryName) {
		this.prCategoryName = prCategoryName;
	}
	public String getPrCategoryCode() {
		return prCategoryCode;
	}
	public void setPrCategoryCode(String prCategoryCode) {
		this.prCategoryCode = prCategoryCode;
	}
	public String getPrCategoryParent() {
		return prCategoryParent;
	}
	public void setPrCategoryParent(String prCategoryParent) {
		this.prCategoryParent = prCategoryParent;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CateVO [seeAll="+seeAll+", prCategoryName="+prCategoryName+", prCategoryCode="
				+prCategoryCode+", prCategoryParent="+prCategoryParent+"]";
	}
	
}
