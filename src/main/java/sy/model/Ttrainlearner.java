package sy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * wei
 */
@Entity
@Table(name = "ttrainlearner", schema = "")
public class Ttrainlearner implements java.io.Serializable {

	// Fields cID cseq ccontent cname csex cage ciden ccompany ctitle cmajor cphone cmail cresult cnum

	private String cid;
	private String cseq;
	private String ccontent;
	private String cname;
	private String csex;
	private String cage;
	private String ciden;
	private String ccompany;
	private String ctitle;
	private String cmajor;
	private String cphone;
	private String cmail;
	private String cresult;
	private String cnum;
	private String cstyle;


	// Constructors

	/** default constructor */
	public Ttrainlearner() {
	}

	/** minimal constructor */
	public Ttrainlearner(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Ttrainlearner(String cid, String cseq, String ccontent, String cname, String csex, String cage, String ciden, String ccompany, String ctitle, String cmajor, 
			String cphone, String cmail, String cresult, String cnum, String cstyle) {
		this.cid = cid;
		this.cseq = cseq;		
		this.ccontent = ccontent;
		this.cname = cname;
		this.csex = csex;
		this.cage = cage;
		this.ciden = ciden;
		this.ccompany = ccompany;
		this.ctitle = ctitle;
		this.cmajor = cmajor;
		this.cphone = cphone;
		this.cmail = cmail;
		this.cresult = cresult;
		this.cnum = cnum;
		this.cstyle = cstyle;
	}

	// Property accessors
	@Id
	@Column(name = "cID", unique = true, nullable = false, length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(name = "cseq", length = 32)
	public String getCseq() {
		return this.cseq;
	}

	public void setCseq(String cseq) {
		this.cseq = cseq;
	}

	@Column(name = "cname", length = 128)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "cnum", length = 32)
	public String getCnum() {
		return this.cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	@Column(name = "ccontent", length = 32)
	public String getCcontent() {
		return this.ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	@Column(name = "csex", length = 32)
	public String getCsex() {
		return this.csex;
	}

	public void setCsex(String csex) {
		this.csex = csex;
	}

	@Column(name = "cage", length = 128)
	public String getCage() {
		return this.cage;
	}

	public void setCage(String cage) {
		this.cage = cage;
	}

	@Column(name = "ciden", length = 2048)
	public String getCiden() {
		return this.ciden;
	}

	public void setCiden(String ciden) {
		this.ciden = ciden;
	}

	@Column(name = "ccompany", length = 16)
	public String getCcompany() {
		return this.ccompany;
	}

	public void setCcompany(String ccompany) {
		this.ccompany = ccompany;
	}

	@Column(name = "ctitle", length = 16)
	public String getCtitle() {
		return this.ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	
	@Column(name = "cmajor", length = 36)
	public String getCmajor() {
		return this.cmajor;
	}
		
	public void setCmajor(String cmajor) {
		this.cmajor = cmajor;
	}

	public String getCphone() {
		return this.cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getCmail() {
		return this.cmail;
	}

	public void setCmail(String cmail) {
		this.cmail = cmail;
	}

	public String getCresult() {
		return this.cresult;
	}

	public void setCresult(String cresult) {
		this.cresult = cresult;
	}

	public String getCstyle() {
		return this.cstyle;
	}

	public void setCstyle(String cstyle) {
		this.cstyle = cstyle;
	}

}

