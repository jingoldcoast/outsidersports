package com.company.dto;

public class FestivaltalkDTO {
	private int fno;
	private String fpass, fcontent, fip;
	private String fdate;
	public FestivaltalkDTO() {
		super();
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getFpass() {
		return fpass;
	}
	public void setFpass(String fpass) {
		this.fpass = fpass;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public String getFip() {
		return fip;
	}
	public void setFip(String fip) {
		this.fip = fip;
	}
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	@Override
	public String toString() {
		return "FestivaltalkDTO [fno=" + fno + ", fpass=" + fpass + ", fcontent=" + fcontent + ", fip=" + fip
				+ ", fdate=" + fdate + "]";
	}
	/*
	 create table festivaltalk(
fno int auto_increment primary key,
ftitle varchar(150) not null,
fpass varchar(20) not null,
fcontent varchar(500) not null,
fip varchar(50) not null
) default charset=utf8;
	 */
}
