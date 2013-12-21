package sy.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author wei
 */
@Entity
@Table(name = "tresearch",schema= "")
public class Tresearch implements java.io.Serializable {

	// Fields

	private String cid;
	private String cclassify;
	private String cname;
	private String clanguage;
	private String cckeyword;
	private String cekeyword;
	private String cinformation;
	private String cnote;
	private String cstorage;
	private String cyear;
	private String cunit;
	private String ccontactid;
	private String ctypeman;
	private Date ctypetime;
	
	private String cflag;

	
	// Constructors

	/** default constructor */
	public Tresearch() {
	}

	/** minimal constructor */
	public Tresearch(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tresearch(String cid, String cclassify, String cname,
			String clanguage, String cckeyword, String cekeyword,
			String cinformation, String cnote, String cstorage, String cyear,
			String cunit, String ccontactid, String ctypeman, Date ctypetime, String cflag) {
		this.cid = cid;
		this.cclassify = cclassify;
		this.cname = cname;
		this.clanguage = clanguage;
		this.cckeyword = cckeyword;
		this.cekeyword = cekeyword;
		this.cinformation = cinformation;
		this.cnote = cnote;
		this.cstorage = cstorage;
		this.cyear = cyear;
		this.cunit = cunit;
		this.ccontactid = ccontactid;
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

	@Column(name = "cclassify", length = 16)
	public String getCclassify() {
		return this.cclassify;
	}

	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}

	@Column(name = "cname", length = 128)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "clanguage", length = 16)
	public String getClanguage() {
		return this.clanguage;
	}

	public void setClanguage(String clanguage) {
		this.clanguage = clanguage;
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

	@Column(name = "cinformation", length = 2048)
	public String getCinformation() {
		return this.cinformation;
	}

	public void setCinformation(String cinformation) {
		this.cinformation = cinformation;
	}

	@Column(name = "cnote", length = 2048)
	public String getCnote() {
		return this.cnote;
	}

	public void setCnote(String cnote) {
		this.cnote = cnote;
	}

	@Column(name = "cstorage", length = 16)
	public String getCstorage() {
		return this.cstorage;
	}

	public void setCstorage(String cstorage) {
		this.cstorage = cstorage;
	}

	@Column(name = "cyear")
	public String getCyear() {
		return this.cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	@Column(name = "cunit", length = 128)
	public String getCunit() {
		return this.cunit;
	}

	public void setCunit(String cunit) {
		this.cunit = cunit;
	}

	@Column(name = "ccontactid", length = 10)
	public String getCcontactid() {
		return this.ccontactid;
	}

	public void setCcontactid(String ccontactid) {
		this.ccontactid = ccontactid;
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
		return this.cflag;
	}
		
	public void setCflag(String cflag) {
		this.cflag = cflag;
	}
}