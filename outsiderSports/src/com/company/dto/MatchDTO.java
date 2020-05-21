package com.company.dto;

public class MatchDTO {
	private int mno;
	private String mtitle, mhost, msex, mday, mdate;
	private int mhour, mduration, mprice;
	private String mcreatedate;
	private int gno, mtotal;
	private String thumbnail; //경기참에 메뉴에서 list 볼때 대표이미지 보여주려고 추가함
	private int cnt; //경기 남은 자리 (예약가능자리)
	private double gstar; // 경기장 평균평점
	private double hstar; // 경기장 호스트 평균평점
	private String gcategory;
	
	public MatchDTO() { super(); }

	public String getGcategory() {
		return gcategory;
	}

	public void setGcategory(String gcategory) {
		this.gcategory = gcategory;
	}

	public double getGstar() {
		return gstar;
	}

	public void setGstar(double gstar) {
		this.gstar = gstar;
	}

	public double getHstar() {
		return hstar;
	}

	public void setHstar(double hstar) {
		this.hstar = hstar;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMtitle() {
		return mtitle;
	}

	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}

	public String getMhost() {
		return mhost;
	}

	public void setMhost(String mhost) {
		this.mhost = mhost;
	}

	public String getMsex() {
		return msex;
	}

	public void setMsex(String msex) {
		this.msex = msex;
	}

	public String getMday() {
		return mday;
	}

	public void setMday(String mday) {
		this.mday = mday;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public int getMhour() {
		return mhour;
	}

	public void setMhour(int mhour) {
		this.mhour = mhour;
	}

	public int getMduration() {
		return mduration;
	}

	public void setMduration(int mduration) {
		this.mduration = mduration;
	}

	public int getMprice() {
		return mprice;
	}

	public void setMprice(int mprice) {
		this.mprice = mprice;
	}

	public String getMcreatedate() {
		return mcreatedate;
	}

	public void setMcreatedate(String mcreatedate) {
		this.mcreatedate = mcreatedate;
	}

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public int getMtotal() {
		return mtotal;
	}

	public void setMtotal(int mtotal) {
		this.mtotal = mtotal;
	}

	@Override
	public String toString() {
		return "MatchDTO [mno=" + mno + ", mtitle=" + mtitle + ", mhost=" + mhost + ", msex=" + msex + ", mday=" + mday
				+ ", mdate=" + mdate + ", mhour=" + mhour + ", mduration=" + mduration + ", mprice=" + mprice
				+ ", mcreatedate=" + mcreatedate + ", gno=" + gno + ", mtotal=" + mtotal + ", thumbnail=" + thumbnail
				+ ", cnt=" + cnt + ", gstar=" + gstar + "]";
	}






	
}
