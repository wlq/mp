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
@Table(name = "tdevice", schema = " ")
public class Tdevice implements java.io.Serializable {

	// Fields

	private String cid;
	private String cnumber;
	private String csource;
	private String ccname;
	private String cename;
	private Date cresearchtime;
	private String cversion;
	private String cfield;
	private String cfactory;
	private Double cprice;
	private String cnote;
	private String cunit;
	private String ccontactid;
	private String cbackup1;
	private String cbackup2;
	private String cflag;

	
	// Constructors

	/** default constructor */
	public Tdevice() {
	}

	/** minimal constructor */
	public Tdevice(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tdevice(String cid, String cnumber, String csource, String ccname,
			String cename, Date cresearchtime, String cversion, String cfield,
			String cfactory, Double cprice, String cnote, String cunit,
			String ccontactid, String cbackup1, String cbackup2, String cflag) {
		this.cid = cid;
		this.cnumber = cnumber;
		this.csource = csource;
		this.ccname = ccname;
		this.cename = cename;
		this.cresearchtime = cresearchtime;
		this.cversion = cversion;
		this.cfield = cfield;
		this.cfactory = cfactory;
		this.cprice = cprice;
		this.cnote = cnote;
		this.cunit = cunit;
		this.ccontactid = ccontactid;
		this.cbackup1 = cbackup1;
		this.cbackup2 = cbackup2;
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

	@Column(name = "cnumber", length = 32)
	public String getCnumber() {
		return this.cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	@Column(name = "csource", length = 16)
	public String getCsource() {
		return this.csource;
	}

	public void setCsource(String csource) {
		this.csource = csource;
	}

	@Column(name = "ccname", length = 32)
	public String getCcname() {
		return this.ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}

	@Column(name = "cename", length = 32)
	public String getCename() {
		return this.cename;
	}

	public void setCename(String cename) {
		this.cename = cename;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cresearchtime", length = 10)
	public Date getCresearchtime() {
		return this.cresearchtime;
	}

	public void setCresearchtime(Date cresearchtime) {
		this.cresearchtime = cresearchtime;
	}

	@Column(name = "cversion", length = 64)
	public String getCversion() {
		return this.cversion;
	}

	public void setCversion(String cversion) {
		this.cversion = cversion;
	}

	@Column(name = "cfield", length = 128)
	public String getCfield() {
		return this.cfield;
	}

	public void setCfield(String cfield) {
		this.cfield = cfield;
	}

	@Column(name = "cfactory", length = 128)
	public String getCfactory() {
		return this.cfactory;
	}

	public void setCfactory(String cfactory) {
		this.cfactory = cfactory;
	}

	@Column(name = "cprice", precision = 10)
	public Double getCprice() {
		return this.cprice;
	}

	public void setCprice(Double cprice) {
		this.cprice = cprice;
	}

	@Column(name = "cnote", length = 2048)
	public String getCnote() {
		return this.cnote;
	}

	public void setCnote(String cnote) {
		this.cnote = cnote;
	}

	@Column(name = "cunit", length = 128)
	public String getCunit() {
		return this.cunit;
	}

	public void setCunit(String cunit) {
		this.cunit = cunit;
	}

	@Column(name = "ccontactid")
	public String getCcontactid() {
		return this.ccontactid;
	}

	public void setCcontactid(String ccontactid) {
		this.ccontactid = ccontactid;
	}

	@Column(name = "cbackup1")
	public String getCbackup1() {
		return this.cbackup1;
	}

	public void setCbackup1(String cbackup1) {
		this.cbackup1 = cbackup1;
	}

	@Column(name = "cbackup2")
	public String getCbackup2() {
		return this.cbackup2;
	}

	public void setCbackup2(String cbackup2) {
		this.cbackup2 = cbackup2;
	}
	@Column(name = "cflag", length = 36)
	public String getCflag() {
		return cflag;
	}
		
	public void setCflag(String cflag) {
		this.cflag = cflag;
	}
}