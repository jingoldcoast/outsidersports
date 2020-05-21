package com.company.dto;

public class MemberDTO {

	private String oid, opass, oname, obirth, osex, ocontact, ophoto, ograde, odate;
	private int opoint;
	private double hstar; // 메니져 평점
	private int usedpoint; //누적 포인트사용량
	private int matchcnt; //누적 경기참여 횟수
	private int matchAsHost; //호스트로서 경기 얼마나 주관(?) 했는지
	public MemberDTO() {
		super();
	}

	public int getMatchAsHost() {
		return matchAsHost;
	}

	public void setMatchAsHost(int matchAsHost) {
		this.matchAsHost = matchAsHost;
	}

	public int getMatchcnt() {
		return matchcnt;
	}
	public void setMatchcnt(int matchcnt) {
		this.matchcnt = matchcnt;
	}
	public double getHstar() {
		return hstar;
	}
	public void setHstar(double hstar) {
		this.hstar = hstar;
	}
	public int getUsedpoint() {
		return usedpoint;
	}
	public void setUsedpoint(int usedpoint) {
		this.usedpoint = usedpoint;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public int getOpoint() {
		return opoint;
	}
	public void setOpoint(int opoint) {
		this.opoint = opoint;
	}
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOpass() {
		return opass;
	}

	public void setOpass(String opass) {
		this.opass = opass;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getObirth() {
		return obirth;
	}

	public void setObirth(String obirth) {
		this.obirth = obirth;
	}

	public String getOsex() {
		return osex;
	}

	public void setOsex(String osex) {
		this.osex = osex;
	}

	public String getOcontact() {
		return ocontact;
	}

	public void setOcontact(String ocontact) {
		this.ocontact = ocontact;
	}

	public String getOphoto() {
		return ophoto;
	}

	public void setOphoto(String ophoto) {
		this.ophoto = ophoto;
	}

	public String getOgrade() {
		return ograde;
	}

	public void setOgrade(String ograde) {
		this.ograde = ograde;
	}


	@Override
	public String toString() {
		return "MemberDTO [oid=" + oid + ", opass=" + opass + ", oname=" + oname + ", obirth=" + obirth + ", osex="
				+ osex + ", ocontact=" + ocontact + ", ophoto=" + ophoto + ", ograde=" + ograde + ", odate=" + odate
				+ ", opoint=" + opoint + ", hstar=" + hstar + "]";
	}


	
}

/*
create table omember(
oid varchar2(50) constraint omember_pk primary key,
opass varchar2(50) not null,
oname varchar2(150) not null,
obirth timestamp not null,
osex char(2) constraint omember_sex_check check (osex in('m','f')),
ograde varchar2(50) default 'GREEN' not null,
ocontact varchar2(150) not null,
ophoto varchar2(50) not null,
odate timestamp default current_timestamp,
opoint number default 0
);
 
 */
