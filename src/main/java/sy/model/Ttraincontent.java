package sy.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ttraincontent entity. @author wei
 */
@Entity
@Table(name = "Ttraincontent", schema = "")
public class Ttraincontent implements java.io.Serializable {

	// Fields

	private String cid;
	private Ttraincontent ttraincontent;
	private Tuser tuser;
	private String ciconcls;
	private String cname;
	private BigDecimal cseq;
	private String curl;
	private Date cstarttime;
	private Date cendtime;
	private String cresponsecompany;
	private String cresponser;
	private String cstatus;
	private Float cbudget;
	private Float cprogress;
	private String ccontent;
	private String cstyle;
	private String cplace;
	private String cnum;
	private String cmaterial;
	private String cassess;
	private Set<Ttraincontent> ttraincontents = new HashSet<Ttraincontent>(0);

	// Constructors

	/** default constructor */
	public Ttraincontent() {
	}

	/** minimal constructor */
	public Ttraincontent(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Ttraincontent(String cid, Ttraincontent ttraincontent, Tuser tuser,
			String ciconcls, String cname, BigDecimal cseq, String curl,
			Date cstarttime, Date cendtime, String cresponsecompany,
			String cresponser, String cstatus, Float cbudget,Float cprogress,String ccontent,String cstyle,String cplace,String cnum,String cmaterial,String cassess,
			Set<Ttraincontent> ttraincontents) {
		this.cid = cid;
		this.ttraincontent = ttraincontent;
		this.tuser = tuser;
		this.ciconcls = ciconcls;
		this.cname = cname;
		this.cseq = cseq;
		this.curl = curl;
		this.cstarttime = cstarttime;
		this.cendtime = cendtime;
		this.cresponsecompany = cresponsecompany;
		this.cresponser = cresponser;
		this.cstatus = cstatus;
		this.cbudget = cbudget;
		this.cprogress = cprogress;
		this.ttraincontents = ttraincontents;
		this.ccontent = ccontent;
		this.cstyle = cstyle;
		this.cplace = cplace;
		this.cnum = cnum;
		this.cmaterial = cmaterial;
		this.cassess = cassess;
	}

	// Property accessors
	@Id
	@Column(name = "CID", unique = true, nullable = false, length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public Ttraincontent getTtraincontent() {
		return this.ttraincontent;
	}

	public void setTtraincontent(Ttraincontent ttraincontent) {
		this.ttraincontent = ttraincontent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUID")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	@Column(name = "CICONCLS", length = 100)
	public String getCiconcls() {
		return this.ciconcls;
	}

	public void setCiconcls(String ciconcls) {
		this.ciconcls = ciconcls;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CSEQ", precision = 22, scale = 0)
	public BigDecimal getCseq() {
		return this.cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	@Column(name = "CURL", length = 200)
	public String getCurl() {
		return this.curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	@Column(name = "CSTARTTIME", length = 19)
	public Date getCstarttime() {
		return this.cstarttime;
	}

	public void setCstarttime(Date cstarttime) {
		this.cstarttime = cstarttime;
	}

	@Column(name = "CENDTIME", length = 19)
	public Date getCendtime() {
		return this.cendtime;
	}

	public void setCendtime(Date cendtime) {
		this.cendtime = cendtime;
	}

	@Column(name = "CRESPONSECOMPANY", length = 100)
	public String getCresponsecompany() {
		return this.cresponsecompany;
	}

	public void setCresponsecompany(String cresponsecompany) {
		this.cresponsecompany = cresponsecompany;
	}

	@Column(name = "CRESPONSER", length = 300)
	public String getCresponser() {
		return this.cresponser;
	}

	public void setCresponser(String cresponser) {
		this.cresponser = cresponser;
	}

	@Column(name = "CSTATUS", length = 1)
	public String getCstatus() {
		return this.cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	@Column(name = "CBUDGET", precision = 12, scale = 0)
	public Float getCbudget() {
		return this.cbudget;
	}

	public void setCbudget(Float cbudget) {
		this.cbudget = cbudget;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ttraincontent")
	public Set<Ttraincontent> getTtraincontents() {
		return this.ttraincontents;
	}

	public void setTtraincontents(Set<Ttraincontent> ttraincontents) {
		this.ttraincontents = ttraincontents;
	}
	@Column(name = "CPROGRESS")
	public Float getCprogress() {
		return cprogress;
	}

	public void setCprogress(Float cprogress) {
		this.cprogress = cprogress;
	}

	@Column(name = "CCONTENT", length = 300)
	public String getCcontent() {
		return this.ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	@Column(name = "CSTYLE", length = 300)
	public String getCstyle() {
		return this.cstyle;
	}

	public void setCstyle(String cstyle) {
		this.cstyle = cstyle;
	}

	@Column(name = "CPLACE", length = 300)
	public String getCplace() {
		return this.cplace;
	}

	public void setCplace(String cplace) {
		this.cplace = cplace;
	}

	@Column(name = "CNUM", length = 300)
	public String getCnum() {
		return this.cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	@Column(name = "CMATERIAL", length = 300)
	public String getCmaterial() {
		return this.cmaterial;
	}

	public void setCmaterial(String cmaterial) {
		this.cmaterial = cmaterial;
	}

	@Column(name = "CASSESS", length = 300)
	public String getCassess() {
		return this.cassess;
	}

	public void setCassess(String cassess) {
		this.cassess = cassess;
	}
	

}
