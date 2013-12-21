package sy.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tpulication entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tpulication", schema = " ")
public class Tpulication implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String cauthor;
	private String cckeyword;
	private String cekeyword;
	private String cpublishhous;
	private Date cpublishtime;
	private Integer cwords;
	private String cisbn;
	private String csummary;
	private String ctypeman;
	private Date ctypetime;
	private String cflag;


	// Constructors

	/** default constructor */
	public Tpulication() {
	}

	/** minimal constructor */
	public Tpulication(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tpulication(String cid, String cname, String cauthor,
			String cckeyword, String cekeyword, String cpublishhous,
			Date cpublishtime, Integer cwords, String cisbn, String csummary,
			String ctypeman, Date ctypetime, String cflag) {
		this.cid = cid;
		this.cname = cname;
		this.cauthor = cauthor;
		this.cckeyword = cckeyword;
		this.cekeyword = cekeyword;
		this.cpublishhous = cpublishhous;
		this.cpublishtime = cpublishtime;
		this.cwords = cwords;
		this.cisbn = cisbn;
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

	@Column(name = "cname", length = 128)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "cauthor", length = 64)
	public String getCauthor() {
		return this.cauthor;
	}

	public void setCauthor(String cauthor) {
		this.cauthor = cauthor;
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

	@Column(name = "cpublishhous", length = 64)
	public String getCpublishhous() {
		return this.cpublishhous;
	}

	public void setCpublishhous(String cpublishhous) {
		this.cpublishhous = cpublishhous;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cpublishtime", length = 10)
	public Date getCpublishtime() {
		return this.cpublishtime;
	}

	public void setCpublishtime(Date cpublishtime) {
		this.cpublishtime = cpublishtime;
	}

	@Column(name = "cwords")
	public Integer getCwords() {
		return this.cwords;
	}

	public void setCwords(Integer cwords) {
		this.cwords = cwords;
	}

	@Column(name = "cisbn", length = 32)
	public String getCisbn() {
		return this.cisbn;
	}

	public void setCisbn(String cisbn) {
		this.cisbn = cisbn;
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