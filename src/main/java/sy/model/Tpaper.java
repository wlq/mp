package sy.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * wei
 */
@Entity
@Table(name = "tpaper", schema = "")
public class Tpaper implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String cckeyword;
	private String cekeyword;
	private String csummary;
	private String clanguage;
	private String cfcontactid;
	private String cccontactid;
	private String cperiodical;
	private String cissue;
	private String cstate;
	private Date cpublishtime;
	private String cclassify;
	private String cinde;
	private String ctypeman;
	private Date ctypetime;
	private String cflag;


	// Constructors

	/** default constructor */
	public Tpaper() {
	}

	/** minimal constructor */
	public Tpaper(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tpaper(String cid, String cname, String cckeyword, String cekeyword,
			String csummary, String clanguage, String cfcontactid,
			String cccontactid, String cperiodical, String cissue,
			String cstate, Date cpublishtime, String cclassify, String cinde,
			String ctypeman, Date ctypetime, String cflag) {
		this.cid = cid;
		this.cname = cname;
		this.cckeyword = cckeyword;
		this.cekeyword = cekeyword;
		this.csummary = csummary;
		this.clanguage = clanguage;
		this.cfcontactid = cfcontactid;
		this.cccontactid = cccontactid;
		this.cperiodical = cperiodical;
		this.cissue = cissue;
		this.cstate = cstate;
		this.cpublishtime = cpublishtime;
		this.cclassify = cclassify;
		this.cinde = cinde;
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

	@Column(name = "cname", length = 128)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "cckeyword", length = 64)
	public String getCckeyword() {
		return this.cckeyword;
	}

	public void setCckeyword(String cckeyword) {
		this.cckeyword = cckeyword;
	}

	@Column(name = "cekeyword", length = 64)
	public String getCekeyword() {
		return this.cekeyword;
	}

	public void setCekeyword(String cekeyword) {
		this.cekeyword = cekeyword;
	}

	@Column(name = "csummary", length = 2048)
	public String getCsummary() {
		return this.csummary;
	}

	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}

	@Column(name = "clanguage", length = 32)
	public String getClanguage() {
		return this.clanguage;
	}

	public void setClanguage(String clanguage) {
		this.clanguage = clanguage;
	}

	@Column(name = "cfcontactid", length = 64)
	public String getCfcontactid() {
		return this.cfcontactid;
	}

	public void setCfcontactid(String cfcontactid) {
		this.cfcontactid = cfcontactid;
	}

	@Column(name = "cccontactid", length = 64)
	public String getCccontactid() {
		return this.cccontactid;
	}

	public void setCccontactid(String cccontactid) {
		this.cccontactid = cccontactid;
	}

	@Column(name = "cperiodical")
	public String getCperiodical() {
		return this.cperiodical;
	}

	public void setCperiodical(String cperiodical) {
		this.cperiodical = cperiodical;
	}

	@Column(name = "cissue", length = 64)
	public String getCissue() {
		return this.cissue;
	}

	public void setCissue(String cissue) {
		this.cissue = cissue;
	}

	@Column(name = "cstate", length = 16)
	public String getCstate() {
		return this.cstate;
	}

	public void setCstate(String cstate) {
		this.cstate = cstate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cpublishtime", length = 10)
	public Date getCpublishtime() {
		return this.cpublishtime;
	}

	public void setCpublishtime(Date cpublishtime) {
		this.cpublishtime = cpublishtime;
	}

	@Column(name = "cclassify", length = 16)
	public String getCclassify() {
		return this.cclassify;
	}

	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}

	@Column(name = "cinde", length = 32)
	public String getCinde() {
		return this.cinde;
	}

	public void setCinde(String cinde) {
		this.cinde = cinde;
	}

	@Column(name = "ctypeman", length = 16)
	public String getCtypeman() {
		return this.ctypeman;
	}

	public void setCtypeman(String ctypeman) {
		this.ctypeman = ctypeman;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ctypetime", length = 10)
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