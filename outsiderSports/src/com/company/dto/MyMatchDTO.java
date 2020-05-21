package com.company.dto;

public class MyMatchDTO {
	private String mtitle, mdate, gcategory, gaddr1;
	private int mno, gno;
	private String mhost, gname;
	private int mhour;
	private int mprice;
	public MyMatchDTO() {
		super();
	}
	
	public int getMprice() {
		return mprice;
	}

	public void setMprice(int mprice) {
		this.mprice = mprice;
	}

	public int getMhour() {
		return mhour;
	}

	public void setMhour(int mhour) {
		this.mhour = mhour;
	}

	public String getMhost() {
		return mhost;
	}

	public void setMhost(String mhost) {
		this.mhost = mhost;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public String getMtitle() {
		return mtitle;
	}
	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getGcategory() {
		return gcategory;
	}
	public void setGcategory(String gcategory) {
		this.gcategory = gcategory;
	}
	public String getGaddr1() {
		return gaddr1;
	}
	public void setGaddr1(String gaddr1) {
		this.gaddr1 = gaddr1;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	
}//end class
