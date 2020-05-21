package com.company.dto;

public class GroundDTO {
	private int gno;
	private String gname, gregion, gzipcode, gaddr1, gaddr2, gfile;
	private int gside;
	private String gwriter, gdate, gcategory, gdesc;
	private String parking, light, io, shower, air, ball, vest, shoes; //편의시설
	private double gstar; //경기장 평점
	private int glike; //경기장 좋아요 수
	
	public GroundDTO() { }


	public int getGlike() {
		return glike;
	}


	public void setGlike(int glike) {
		this.glike = glike;
	}


	public double getGstar() {
		return gstar;
	}
	public void setGstar(double gstar) {
		this.gstar = gstar;
	}
	public String getGzipcode() {
		return gzipcode;
	}
	public void setGzipcode(String gzipcode) {
		this.gzipcode = gzipcode;
	}

	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGregion() {
		return gregion;
	}
	public void setGregion(String gregion) {
		this.gregion = gregion;
	}

	public String getGfile() {
		return gfile;
	}

	public void setGfile(String gfile) {
		this.gfile = gfile;
	}

	public int getGside() {
		return gside;
	}
	public void setGside(int gside) {
		this.gside = gside;
	}
	public String getGwriter() {
		return gwriter;
	}
	public void setGwriter(String gwriter) {
		this.gwriter = gwriter;
	}
	public String getGdate() {
		return gdate;
	}
	public void setGdate(String gdate) {
		this.gdate = gdate;
	}
	public String getGcategory() {
		return gcategory;
	}
	public void setGcategory(String gcategory) {
		this.gcategory = gcategory;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getIo() {
		return io;
	}
	public void setIo(String io) {
		this.io = io;
	}
	public String getShower() {
		return shower;
	}
	public void setShower(String shower) {
		this.shower = shower;
	}
	public String getAir() {
		return air;
	}
	public void setAir(String air) {
		this.air = air;
	}
	public String getBall() {
		return ball;
	}
	public void setBall(String ball) {
		this.ball = ball;
	}
	public String getVest() {
		return vest;
	}
	public void setVest(String vest) {
		this.vest = vest;
	}
	public String getShoes() {
		return shoes;
	}
	public void setShoes(String shoes) {
		this.shoes = shoes;
	}

	public String getGaddr1() {
		return gaddr1;
	}

	public void setGaddr1(String gaddr1) {
		this.gaddr1 = gaddr1;
	}

	public String getGaddr2() {
		return gaddr2;
	}

	public void setGaddr2(String gaddr2) {
		this.gaddr2 = gaddr2;
	}

	public String getGdesc() {
		return gdesc;
	}

	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}


	@Override
	public String toString() {
		return "GroundDTO [gno=" + gno + ", gname=" + gname + ", gregion=" + gregion + ", gzipcode=" + gzipcode
				+ ", gaddr1=" + gaddr1 + ", gaddr2=" + gaddr2 + ", gfile=" + gfile + ", gside=" + gside + ", gwriter="
				+ gwriter + ", gdate=" + gdate + ", gcategory=" + gcategory + ", gdesc=" + gdesc + ", parking="
				+ parking + ", light=" + light + ", io=" + io + ", shower=" + shower + ", air=" + air + ", ball=" + ball
				+ ", vest=" + vest + ", shoes=" + shoes + ", gstar=" + gstar + ", glike=" + glike + "]";
	}




	
}
