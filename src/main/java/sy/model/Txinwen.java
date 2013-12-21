package sy.model;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tnews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TXINWEN", schema = "")
public class Txinwen implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String cabstract;
	private Clob cpname;
	private Clob cdesc;
	private Date ccreatedatetime;

	// Constructors

	/** default constructor */
	public Txinwen() {
	}

	/** minimal constructor */
	public Txinwen(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Txinwen(String cid, String cname,String cabstract, Clob cpname,Clob cdesc, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.cabstract = cabstract;
		this.cpname = cpname;
		this.cdesc = cdesc;
		this.ccreatedatetime = ccreatedatetime;
	}

	public Txinwen(String cid, String cname,String cabstract, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.cabstract = cabstract;
		this.ccreatedatetime = ccreatedatetime;
	}

	// Property accessors
	@Id
	@Column(name = "CID", nullable = false, length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Column(name = "CABSTRACT", nullable = false, length = 100)
	public String getCabstract() {
		return this.cabstract;
	}

	public void setCabstract(String cabstract) {
		this.cabstract = cabstract;
	}
	
	@Column(name = "CPNAME")
	public Clob getCpname() {
		return this.cpname;
	}

	public void setCpname(Clob cpname) {
		this.cpname = cpname;
	}
	
	@Column(name = "CDESC")
	public Clob getCdesc() {
		return this.cdesc;
	}

	public void setCdesc(Clob cdesc) {
		this.cdesc = cdesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CCREATEDATETIME", length = 7)
	public Date getCcreatedatetime() {
		return this.ccreatedatetime;
	}

	public void setCcreatedatetime(Date ccreatedatetime) {
		this.ccreatedatetime = ccreatedatetime;
	}

}