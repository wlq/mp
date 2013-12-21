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
@Table(name = "tapplicationNote", schema = " ")
public class TapplicationNote implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String csuitableid;
	private String cinformation;
	private String cnote;
	private String cevaluation;
	private String cidentify;
	private String crighted;
	private String ccontactid;
	private Date ctypetime;
	
	private String cflag;

	// Constructors

	
	/** default constructor */
	public TapplicationNote() {
	}

	/** minimal constructor */
	public TapplicationNote(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public TapplicationNote(String cid, String cname, String csuitableid,
			String cinformation, String cnote, String cevaluation,
			String cidentify, String crighted, String ccontactid, Date ctypetime, String cflag) {
		this.cid = cid;
		this.cname = cname;
		this.csuitableid = csuitableid;
		this.cinformation = cinformation;
		this.cnote = cnote;
		this.cevaluation = cevaluation;
		this.cidentify = cidentify;
		this.crighted = crighted;
		this.ccontactid = ccontactid;
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

	@Column(name = "cname", length = 128)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "csuitableid")
	public String getCsuitableid() {
		return this.csuitableid;
	}

	public void setCsuitableid(String csuitableid) {
		this.csuitableid = csuitableid;
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

	@Column(name = "cevaluation", length = 2048)
	public String getCevaluation() {
		return this.cevaluation;
	}

	public void setCevaluation(String cevaluation) {
		this.cevaluation = cevaluation;
	}

	@Column(name = "cidentify", length = 2048)
	public String getCidentify() {
		return this.cidentify;
	}

	public void setCidentify(String cidentify) {
		this.cidentify = cidentify;
	}

	@Column(name = "crighted")
	public String getCrighted() {
		return this.crighted;
	}

	public void setCrighted(String crighted) {
		this.crighted = crighted;
	}

	@Column(name = "ccontactid")
	public String getCcontactid() {
		return this.ccontactid;
	}

	public void setCcontactid(String ccontactid) {
		this.ccontactid = ccontactid;
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