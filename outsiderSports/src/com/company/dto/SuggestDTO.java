package com.company.dto;

public class SuggestDTO {
	private int sno;
	private String sname;
	private String spass;
	private String stitle;
	private String scontent;
	private String sdate;
	private int shit;
	private String sip;
	private int sgroup;
	private int sstep;
	private int sindent;
	private String sfile;
	
	public SuggestDTO() { super(); }
	public SuggestDTO(int sno, String sname, String spass, String stitle, String scontent, String sdate, int shit,
			String sip, int sgroup, int sstep, int sindent, String sfile) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.spass = spass;
		this.stitle = stitle;
		this.scontent = scontent;
		this.sdate = sdate;
		this.shit = shit;
		this.sip = sip;
		this.sgroup = sgroup;
		this.sstep = sstep;
		this.sindent = sindent;
		this.sfile = sfile;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpass() {
		return spass;
	}
	public void setSpass(String spass) {
		this.spass = spass;
	}
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getShit() {
		return shit;
	}
	public void setShit(int shit) {
		this.shit = shit;
	}
	public String getSip() {
		return sip;
	}
	public void setSip(String sip) {
		this.sip = sip;
	}
	public int getSgroup() {
		return sgroup;
	}
	public void setSgroup(int sgroup) {
		this.sgroup = sgroup;
	}
	public int getSstep() {
		return sstep;
	}
	public void setSstep(int sstep) {
		this.sstep = sstep;
	}
	public int getSindent() {
		return sindent;
	}
	public void setSindent(int sindent) {
		this.sindent = sindent;
	}
	public String getSfile() {
		return sfile;
	}
	public void setSfile(String sfile) {
		this.sfile = sfile;
	}
	
}
