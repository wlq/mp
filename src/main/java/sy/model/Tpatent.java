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
@Table(name = "tpatent", schema = "")
public class Tpatent implements java.io.Serializable {

	// Fields

	private String cid;
	private String ccountry;
	private String cname;
	private String cnumber;
	private String cclassify;
	private String cinvent;
	private String cpatentee;
	private String csummary;
	private String ctypeman;
	private Date ctypetime;
	private String cflag;


	// Constructors

	/** default constructor */
	public Tpatent() {
	}

	/** minimal constructor */
	public Tpatent(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tpatent(String cid, String ccountry, String cname, String cnumber,
			String cclassify, String cinvent, String cpatentee,
			String csummary, String ctypeman, Date ctypetime, String cflag) {
		this.cid = cid;
		this.ccountry = ccountry;
		this.cname = cname;
		this.cnumber = cnumber;
		this.cclassify = cclassify;
		this.cinvent = cinvent;
		this.cpatentee = cpatentee;
		this.csummary = csummary;
		this.ctypeman = ctypeman;
		this.ctypetime = ctypetime;
		this.cflag = cflag;
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

	@Column(name = "ccountry", length = 32)
	public String getCcountry() {
		return this.ccountry;
	}

	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}

	@Column(name = "cname", length = 128)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "cnumber", length = 32)
	public String getCnumber() {
		return this.cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	@Column(name = "cclassify", length = 32)
	public String getCclassify() {
		return this.cclassify;
	}

	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}

	@Column(name = "cinvent", length = 32)
	public String getCinvent() {
		return this.cinvent;
	}

	public void setCinvent(String cinvent) {
		this.cinvent = cinvent;
	}

	@Column(name = "cpatentee", length = 128)
	public String getCpatentee() {
		return this.cpatentee;
	}

	public void setCpatentee(String cpatentee) {
		this.cpatentee = cpatentee;
	}

	@Column(name = "csummary", length = 2048)
	public String getCsummary() {
		return this.csummary;
	}

	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}

	@Column(name = "ctypeman", length = 16)
	public String getCtypeman() {
		return this.ctypeman;
	}

	public void setCtypeman(String ctypeman) {
		this.ctypeman = ctypeman;
	}

	@Column(name = "ctypetime", length = 16)
	public Date getCtypetime() {
		return this.ctypetime;
	}

	public void setCtypetime(Date ctypetime) {
		this.ctypetime = ctypetime;
	}
	
	@Column(name = "cflag", length = 36)
	public String getCflag() {
		return cflag;
	}
		
	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

}