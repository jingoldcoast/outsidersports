package com.company.dto;

public class ReviewDTO {
	private int rno;
	private String rwriter, rfile, rdate;
	private int rhit;
	private String hid;
	private double hstar;
	private String hcomment;
	private int gno;
	private double gstar;
	private String gcomment;
	private int mno;
	public ReviewDTO() {
		super();
	}
	public ReviewDTO(int rno, String rwriter, String rfile, String rdate, int rhit, String hid, int hstar,
			String hcomment, int gno, int gstar, String gcomment, int mno) {
		super();
		this.rno = rno;
		this.rwriter = rwriter;
		this.rfile = rfile;
		this.rdate = rdate;
		this.rhit = rhit;
		this.hid = hid;
		this.hstar = hstar;
		this.hcomment = hcomment;
		this.gno = gno;
		this.gstar = gstar;
		this.gcomment = gcomment;
		this.mno = mno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getRwriter() {
		return rwriter;
	}
	public void setRwriter(String rwriter) {
		this.rwriter = rwriter;
	}
	public String getRfile() {
		return rfile;
	}
	public void setRfile(String rfile) {
		this.rfile = rfile;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getRhit() {
		return rhit;
	}
	public void setRhit(int rhit) {
		this.rhit = rhit;
	}
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getHcomment() {
		return hcomment;
	}
	public void setHcomment(String hcomment) {
		this.hcomment = hcomment;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public String getGcomment() {
		return gcomment;
	}
	public void setGcomment(String gcomment) {
		this.gcomment = gcomment;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public double getHstar() {
		return hstar;
	}
	public void setHstar(double hstar) {
		this.hstar = hstar;
	}
	public double getGstar() {
		return gstar;
	}
	public void setGstar(double gstar) {
		this.gstar = gstar;
	}
	
	
}
