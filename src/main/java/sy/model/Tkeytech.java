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
@Table(name = "tkeytech", schema = "")
public class Tkeytech implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String cresponser;
	private String ccompany;
	private Date cstarttime;
	private Date cendtime;
	private String ckeywords;
	private String csummary;
	private String cphone;
	private String ctypeman;
	private Date ctypetime;
	private String cflag;

	
	// Constructors

	/** default constructor */
	public Tkeytech() {
	}

	/** minimal constructor */
	public Tkeytech(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tkeytech(String cid, String cname, String cresponser,
			String ccompany, Date cstarttime, Date cendtime, String ckeywords,
			String csummary, String cphone, String ctypeman, Date ctypetime, String cflag) {
		this.cid = cid;
		this.cname = cname;
		this.cresponser = cresponser;
		this.ccompany = ccompany;
		this.cstarttime = cstarttime;
		this.cendtime = cendtime;
		this.ckeywords = ckeywords;
		this.csummary = csummary;
		this.cphone = cphone;
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

	@Column(name = "cname")
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "cresponser")
	public String getCresponser() {
		return this.cresponser;
	}

	public void setCresponser(String cresponser) {
		this.cresponser = cresponser;
	}

	@Column(name = "ccompany")
	public String getCcompany() {
		return this.ccompany;
	}

	public void setCcompany(String ccompany) {
		this.ccompany = ccompany;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cstarttime", length = 10)
	public Date getCstarttime() {
		return this.cstarttime;
	}

	public void setCstarttime(Date cstarttime) {
		this.cstarttime = cstarttime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cendtime", length = 10)
	public Date getCendtime() {
		return this.cendtime;
	}

	public void setCendtime(Date cendtime) {
		this.cendtime = cendtime;
	}

	@Column(name = "ckeywords")
	public String getCkeywords() {
		return this.ckeywords;
	}

	public void setCkeywords(String ckeywords) {
		this.ckeywords = ckeywords;
	}

	@Column(name = "csummary", length = 2048)
	public String getCsummary() {
		return this.csummary;
	}

	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}

	@Column(name = "cphone")
	public String getCphone() {
		return this.cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	@Column(name = "ctypeman")
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