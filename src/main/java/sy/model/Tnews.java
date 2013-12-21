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
@Table(name = "TNEWS", schema = "")
public class Tnews implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String cpname;
	private Clob cdesc;
	private Date ccreatedatetime;

	// Constructors

	/** default constructor */
	public Tnews() {
	}

	/** minimal constructor */
	public Tnews(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Tnews(String cid, String cname, String cpname,Clob cdesc, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.cpname = cpname;
		this.cdesc = cdesc;
		this.ccreatedatetime = ccreatedatetime;
	}

	public Tnews(String cid, String cname,String cpname, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.cpname = cpname;
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
	
	@Column(name = "CPNAME", nullable = false, length = 100)
	public String getCpname() {
		return this.cpname;
	}

	public void setCpname(String cpname) {
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