package sy.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tsoft entity. @author wei
 */
@Entity
@Table(name = "tsoft", schema=" ")
public class Tsoft implements java.io.Serializable {

	// Fields

	private String cid;
	private String csoftid;//证书编号
	private String csoftname;//软件名称
	private String cpname;//著作权人
	private Date cdonetime;//开发完成日期
	private Date cfirsttime;//首次发表日期
	private String cway;//权利取得方式
	private String cright;//权利范围
	private String ccommitid;//登记号
	private String cflag;

	// Constructors

	/** default constructor */
	public Tsoft() {
	}

	/** full constructor */
	public Tsoft(String cid, String csoftid, String csoftname, String cpname,
			Date cdonetime, Date cfirsttime, String cway,
			String cright, String ccommitid, String cflag) {
		this.cid = cid;
		this.csoftid = csoftid;
		this.csoftname = csoftname;
		this.cpname = cpname;
		this.cdonetime = cdonetime;
		this.cfirsttime = cfirsttime;
		this.cway = cway;
		this.cright = cright;
		this.ccommitid = ccommitid;
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

	@Column(name = "csoftid", nullable = false, length = 48)
	public String getCsoftid() {
		return this.csoftid;
	}

	public void setCsoftid(String csoftid) {
		this.csoftid = csoftid;
	}

	@Column(name = "csoftname", nullable = false)
	public String getCsoftname() {
		return this.csoftname;
	}

	public void setCsoftname(String csoftname) {
		this.csoftname = csoftname;
	}

	@Column(name = "cpname", nullable = false, length = 36)
	public String getCpname() {
		return this.cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}

	@Column(name = "cdonetime", nullable = false, length = 19)
	public Date getCdonetime() {
		return this.cdonetime;
	}

	public void setCdonetime(Date cdonetime) {
		this.cdonetime = cdonetime;
	}

	@Column(name = "cfirsttime", nullable = false, length = 19)
	public Date getCfirsttime() {
		return this.cfirsttime;
	}

	public void setCfirsttime(Date cfirsttime) {
		this.cfirsttime = cfirsttime;
	}

	@Column(name = "cway", nullable = false, length = 36)
	public String getCway() {
		return this.cway;
	}

	public void setCway(String cway) {
		this.cway = cway;
	}

	@Column(name = "cright", nullable = false)
	public String getCright() {
		return this.cright;
	}

	public void setCright(String cright) {
		this.cright = cright;
	}

	@Column(name = "ccommitid", nullable = false)
	public String getCcommitid() {
		return this.ccommitid;
	}

	public void setCcommitid(String ccommitid) {
		this.ccommitid = ccommitid;
	}
	
	@Column(name = "cflag", length = 36)
	public String getCflag() {
		return cflag;
	}
		
	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

}