package com.company.dto;

public class AnytalkDTO {
/*
Field    | Type         | Null | Key | Default           | Extra          |
---------+--------------+------+-----+-------------------+----------------+
ano      | int(11)      | NO   | PRI | NULL              | auto_increment |
adate    | timestamp    | NO   |     | CURRENT_TIMESTAMP |                |
acomment | varchar(500) | NO   |     | NULL              |                |
apass    | varchar(10)  | NO   |     | NULL              |                |
alike    | int(11)      | YES  |     | 0                 |                |
adislike | int(11)      | YES  |     | 0                 |                |
aip      | varchar(250) | NO   |     | NULL              |                |
 */
	
	private int ano;
	private String adate, acomment, apass, aip;
	private int alike, adislike;
	public AnytalkDTO() {
		super();
	}
	public AnytalkDTO(int ano, String adate, String acomment, String apass, String aip, int alike, int adislike) {
		super();
		this.ano = ano;
		this.adate = adate;
		this.acomment = acomment;
		this.apass = apass;
		this.aip = aip;
		this.alike = alike;
		this.adislike = adislike;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	public String getAcomment() {
		return acomment;
	}
	public void setAcomment(String acomment) {
		this.acomment = acomment;
	}
	public String getApass() {
		return apass;
	}
	public void setApass(String apass) {
		this.apass = apass;
	}
	public String getAip() {
		return aip;
	}
	public void setAip(String aip) {
		this.aip = aip;
	}
	public int getAlike() {
		return alike;
	}
	public void setAlike(int alike) {
		this.alike = alike;
	}
	public int getAdislike() {
		return adislike;
	}
	public void setAdislike(int adislike) {
		this.adislike = adislike;
	}
	@Override
	public String toString() {
		return "AnytalkDTO [ano=" + ano + ", adate=" + adate + ", acomment=" + acomment + ", apass=" + apass + ", aip="
				+ aip + ", alike=" + alike + ", adislike=" + adislike + "]";
	}
	
	
}
