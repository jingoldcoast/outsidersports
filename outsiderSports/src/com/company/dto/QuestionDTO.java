package com.company.dto;

public class QuestionDTO {
	private int qno;
	private String qcategory, qcase, qtitle, qname, qpass, qcontent, qemail, qdate;
	private int qhit;
	private String qip;
	
	//
	private String qreplydate;
	private String qstatus;
	private String qreplytitle, qreplyname, qreplypass, qreplycontent;
	//
	public QuestionDTO() { }

	public QuestionDTO(int qno, String qcategory, String qcase, String qtitle, String qname, String qpass,
			String qcontent, String qemail, String qdate, int qhit, String qip, String qreplydate, String qstatus,
			String qreplytitle, String qreplyname, String qreplypass, String qreplycontent) {
		super();
		this.qno = qno;
		this.qcategory = qcategory;
		this.qcase = qcase;
		this.qtitle = qtitle;
		this.qname = qname;
		this.qpass = qpass;
		this.qcontent = qcontent;
		this.qemail = qemail;
		this.qdate = qdate;
		this.qhit = qhit;
		this.qip = qip;
		this.qreplydate = qreplydate;
		this.qstatus = qstatus;
		this.qreplytitle = qreplytitle;
		this.qreplyname = qreplyname;
		this.qreplypass = qreplypass;
		this.qreplycontent = qreplycontent;
	}


	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getQcategory() {
		return qcategory;
	}
	public void setQcategory(String qcategory) {
		this.qcategory = qcategory;
	}
	public String getQcase() {
		return qcase;
	}
	public void setQcase(String qcase) {
		this.qcase = qcase;
	}
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getQpass() {
		return qpass;
	}
	public void setQpass(String qpass) {
		this.qpass = qpass;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getQemail() {
		return qemail;
	}
	public void setQemail(String qemail) {
		this.qemail = qemail;
	}
	public String getQdate() {
		return qdate;
	}
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	public int getQhit() {
		return qhit;
	}
	public void setQhit(int qhit) {
		this.qhit = qhit;
	}
	public String getQip() {
		return qip;
	}
	public void setQip(String qip) {
		this.qip = qip;
	}
	public String getQreplydate() {
		return qreplydate;
	}
	public void setQreplydate(String qreplydate) {
		this.qreplydate = qreplydate;
	}

	public String getQstatus() {
		return qstatus;
	}
	public void setQstatus(String qstatus) {
		this.qstatus = qstatus;
	}

	public String getQreplytitle() {
		return qreplytitle;
	}

	public void setQreplytitle(String qreplytitle) {
		this.qreplytitle = qreplytitle;
	}

	public String getQreplyname() {
		return qreplyname;
	}

	public void setQreplyname(String qreplyname) {
		this.qreplyname = qreplyname;
	}

	public String getQreplypass() {
		return qreplypass;
	}

	public void setQreplypass(String qreplypass) {
		this.qreplypass = qreplypass;
	}

	public String getQreplycontent() {
		return qreplycontent;
	}

	public void setQreplycontent(String qreplycontent) {
		this.qreplycontent = qreplycontent;
	}

	@Override
	public String toString() {
		return "QuestionDTO [qno=" + qno + ", qcategory=" + qcategory + ", qcase=" + qcase + ", qtitle=" + qtitle
				+ ", qname=" + qname + ", qpass=" + qpass + ", qcontent=" + qcontent + ", qemail=" + qemail + ", qdate="
				+ qdate + ", qhit=" + qhit + ", qip=" + qip + ", qreplydate=" + qreplydate + ", qstatus=" + qstatus
				+ ", qreplytitle=" + qreplytitle + ", qreplyname=" + qreplyname + ", qreplypass=" + qreplypass
				+ ", qreplycontent=" + qreplycontent + "]";
	}
}//end class