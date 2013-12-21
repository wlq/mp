package sy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wei
 */
@Entity
@Table(name = "tresource", schema = " ")
public class Tresource implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private String cclassify;
	private String csummary;
	private Integer cmount;
	private String cinformation;
	private String cstorage;
	private String cunit;
	private String cprinciple;
	private String ccontactid;
	private String cbackup1;
	private String cbackup2;
	private String cflag;

	// Constructors

	/** default constructor */
	public Tresource() {
	}

	/** minimal constructor */
	public Tresource(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tresource(String cid, String cname, String cclassify,
			String csummary, Integer cmount, String cinformation,
			String cstorage, String cunit, String cprinciple,
			String ccontactid, String cbackup1, String cbackup2, String cflag) {
		this.cid = cid;
		this.cname = cname;
		this.cclassify = cclassify;
		this.csummary = csummary;
		this.cmount = cmount;
		this.cinformation = cinformation;
		this.cstorage = cstorage;
		this.cunit = cunit;
		this.cprinciple = cprinciple;
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

	@Column(name = "cname", length = 32)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "cclassify", length = 16)
	public String getCclassify() {
		return this.cclassify;
	}

	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}

	@Column(name = "csummary", length = 2048)
	public String getCsummary() {
		return this.csummary;
	}

	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}

	@Column(name = "cmount")
	public Integer getCmount() {
		return this.cmount;
	}

	public void setCmount(Integer cmount) {
		this.cmount = cmount;
	}

	@Column(name = "cinformation", length = 2048)
	public String getCinformation() {
		return this.cinformation;
	}

	public void setCinformation(String cinformation) {
		this.cinformation = cinformation;
	}

	@Column(name = "cstorage", length = 32)
	public String getCstorage() {
		return this.cstorage;
	}

	public void setCstorage(String cstorage) {
		this.cstorage = cstorage;
	}

	@Column(name = "cunit", length = 128)
	public String getCunit() {
		return this.cunit;
	}

	public void setCunit(String cunit) {
		this.cunit = cunit;
	}

	@Column(name = "cprinciple", length = 16)
	public String getCprinciple() {
		return this.cprinciple;
	}

	public void setCprinciple(String cprinciple) {
		this.cprinciple = cprinciple;
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