package sy.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Tuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TUSER", schema = "")
public class Tuser implements java.io.Serializable {

	// Fields

	private String cid;
	private String cgroupid;
	private Date ccreatedatetime;
	private Date cmodifydatetime;
	private String cname;
	private String cpwd;
	private String cworkplace;
	private String cemail;
	private String ctelphone;
	private String cgender;
	private String caddress;
	private String ccity;
	private Integer cno;//通知数
	private Set<Tusertrole> tusertroles = new HashSet<Tusertrole>(0);

	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** 
	 * @Author wei
	 * minimal constructor */
	public Tuser(String cid) {
		this.cid = cid;
	} 
	/** minimal constructor */
	public Tuser(String cid, String cname, String cpwd) {
		this.cid = cid;
		this.cname = cname;
		this.cpwd = cpwd;
	}

	/** full constructor */
	public Tuser(String cid, Date ccreatedatetime,
			Date cmodifydatetime, String cname, String cpwd,
			String cworkplace, String cemail, String ctelphone, String cgender,
			String caddress, String ccity, Set<Tusertrole> tusertroles) {
		this.cid = cid;
		this.ccreatedatetime = ccreatedatetime;
		this.cmodifydatetime = cmodifydatetime;
		this.cname = cname;
		this.cpwd = cpwd;
		this.cworkplace = cworkplace;
		this.cemail = cemail;
		this.ctelphone = ctelphone;
		this.cgender = cgender;
		this.caddress = caddress;
		this.ccity = ccity;
		this.tusertroles = tusertroles;
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


	@Column(name = "CGROUPID", length = 36)
	public String getCgroupid() {
		return cgroupid;
	}

	public void setCgroupid(String cgroupid) {
		this.cgroupid = cgroupid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CCREATEDATETIME", length = 7)
	public Date getCcreatedatetime() {
		return this.ccreatedatetime;
	}

	public void setCcreatedatetime(Date ccreatedatetime) {
		this.ccreatedatetime = ccreatedatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CMODIFYDATETIME", length = 7)
	public Date getCmodifydatetime() {
		return this.cmodifydatetime;
	}

	public void setCmodifydatetime(Date cmodifydatetime) {
		this.cmodifydatetime = cmodifydatetime;
	}

	@Column(name = "CNAME", unique = true, nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CPWD", nullable = false, length = 100)
	public String getCpwd() {
		return this.cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	@Column(name = "CWORKPLACE", length = 100)
	public String getCworkplace() {
		return this.cworkplace;
	}

	public void setCworkplace(String cworkplace) {
		this.cworkplace = cworkplace;
	}

	@Column(name = "CEMAIL", length = 100)
	public String getCemail() {
		return this.cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	@Column(name = "CTELPHONE", length = 100)
	public String getCtelphone() {
		return this.ctelphone;
	}

	public void setCtelphone(String ctelphone) {
		this.ctelphone = ctelphone;
	}

	@Column(name = "CGENDER", length = 1)
	public String getCgender() {
		return this.cgender;
	}

	public void setCgender(String cgender) {
		this.cgender = cgender;
	}

	@Column(name = "CADDRESS")
	public String getCaddress() {
		return this.caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	@Column(name = "CCITY")
	public String getCcity() {
		return this.ccity;
	}

	public void setCcity(String ccity) {
		this.ccity = ccity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<Tusertrole> getTusertroles() {
		return this.tusertroles;
	}

	public void setTusertroles(Set<Tusertrole> tusertroles) {
		this.tusertroles = tusertroles;
	}
	
	@Column(name = "CNO")
	public Integer getCno() {
		return this.cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

}