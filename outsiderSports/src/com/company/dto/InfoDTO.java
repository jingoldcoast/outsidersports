package com.company.dto;

public class InfoDTO {
	private int nno;
	private String ntitle, ncontent, ndate, ncategory;

	public InfoDTO() {
		super();
	}
	public InfoDTO(int nno, String ntitle, String ncontent, String ndate, String ncategory) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.ndate = ndate;
		this.ncategory = ncategory;
	}
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public String getNdate() {
		return ndate;
	}

	public void setNdate(String ndate) {
		this.ndate = ndate;
	}

	public String getNcategory() {
		return ncategory;
	}

	public void setNcategory(String ncategory) {
		this.ncategory = ncategory;
	}
	@Override
	public String toString() {
		return "InfoDTO [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", ndate=" + ndate
				+ ", ncategory=" + ncategory + "]";
	}
	
}
