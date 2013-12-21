package sy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TLOG", schema = "")
public class Tlog implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String cip;
	private Date clogintime;

	// Constructors

	/** default constructor */
	public Tlog() {
	}

	/** full constructor */
	public Tlog(String cid, String cname, String cip, Date clogintime) {
		this.cid = cid;
		this.cname = cname;
		this.cip = cip;
		this.clogintime = clogintime;
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

	@Column(name = "CIP", length = 100)
	public String getCip() {
		return this.cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CloginTIME", length = 7)
	public Date getClogintime() {
		return this.clogintime;
	}

	public void setClogintime(Date clogintime) {
		this.clogintime = clogintime;
	}

}