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
 * Tconstraint entity. @author wei
 */
@Entity
@Table(name = "Tconstraint", schema = "")
public class Tconstraint implements java.io.Serializable {

	// Fields

	private String cid;
	private Tconstraint tconstraint;
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
	private Set<Tconstraint> tconstraints = new HashSet<Tconstraint>(0);

	// Constructors

	/** default constructor */
	public Tconstraint() {
	}

	/** minimal constructor */
	public Tconstraint(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Tconstraint(String cid, Tconstraint tconstraint, Tuser tuser,
			String ciconcls, String cname, BigDecimal cseq, String curl,
			Date cstarttime, Date cendtime, String cresponsecompany,
			String cresponser, String cstatus, Float cbudget,Float cprogress,
			Set<Tconstraint> tconstraints) {
		this.cid = cid;
		this.tconstraint = tconstraint;
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
		this.tconstraints = tconstraints;
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
	public Tconstraint getTconstraint() {
		return this.tconstraint;
	}

	public void setTconstraint(Tconstraint tconstraint) {
		this.tconstraint = tconstraint;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tconstraint")
	public Set<Tconstraint> getTconstraints() {
		return this.tconstraints;
	}

	public void setTconstraints(Set<Tconstraint> tconstraints) {
		this.tconstraints = tconstraints;
	}
	@Column(name = "CPROGRESS")
	public Float getCprogress() {
		return cprogress;
	}

	public void setCprogress(Float cprogress) {
		this.cprogress = cprogress;
	}
	

}