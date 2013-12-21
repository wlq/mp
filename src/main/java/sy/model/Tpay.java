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

/**
 * Tproject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TPAY", schema = "")
public class Tpay implements java.io.Serializable {

	// Fields

	private String cid;
	private Tpay tpay;
	private Tuser tuser;
	private String cname;//项目名称
	private String cseq;//项目编号
	private String cresponser;//项目负责人
	private Float cbudget;//预算
	private Double ccost;//支出
	private Float cbalance;//结余
	private Float cmoney;//已到款
	private String cfuid;//项目负责人编号	
	private String cfpid;//项目上级编号
		
	private Set<Tpay> tpays = new HashSet<Tpay>(0);//递归饱包含子课题；	
	
	public Tpay() {
	}

	/** 
	 * @Author wei
	 * minimal constructor */
	public Tpay(String cfpid) {
		this.cfpid = cfpid;
	} 
	
	public Tpay(String cid, Tpay tpay, Tuser tuser, String cname,String cfpid,
			String cseq, String cresponser, Float cbudget, Double ccost,
			Float cbalance, Float cmoney, Set<Tpay> tpays,String cfuid) {
		this.cid = cid;
		this.tpay = tpay;
		this.tuser = tuser;
		this.cname = cname;
		this.cseq = cseq;
		this.cresponser = cresponser;
		this.cbudget = cbudget;
		this.ccost = ccost;
		this.cbalance = cbalance;
		this.cmoney = cmoney;
		this.tpays = tpays;
		this.cfuid =cfuid;
		this.cfpid =cfpid;
	}

	@Id
	@Column(name = "CID", unique = true, nullable = false, length = 36)
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public Tpay getTpay() {
		return tpay;
	}

	public void setTpay(Tpay tpay) {
		this.tpay = tpay;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUID")
	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Column(name = "CSEQ", precision = 22, scale = 0)
	public String getCseq() {
		return cseq;
	}

	public void setCseq(String cseq) {
		this.cseq = cseq;
	}

	@Column(name = "CRESPONSER", length = 300)
	public String getCresponser() {
		return cresponser;
	}

	public void setCresponser(String cresponser) {
		this.cresponser = cresponser;
	}
	
	@Column(name = "CBUDGET", precision = 12, scale = 0)
	public Float getCbudget() {
		return cbudget;
	}

	public void setCbudget(Float cbudget) {
		this.cbudget = cbudget;
	}
	
	@Column(name = "CCOST", precision = 12, scale = 0)
	public Double getCcost() {
		return ccost;
	}


	public void setCcost(Double ccost) {
		this.ccost = ccost;
	}

	@Column(name = "CBALANCE", precision = 12, scale = 0)
	public Float getCbalance() {
		return cbalance;
	}


	public void setCbalance(Float cbalance) {
		this.cbalance = cbalance;
	}


	@Column(name = "Cmoney", precision = 12, scale = 0)
	public Float getCmoney() {
		return cmoney;
	}

	public void setCmoney(Float cmoney) {
		this.cmoney = cmoney;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tpay")
	public Set<Tpay> getTpays() {
		return tpays;
	}

	public void setTpays(Set<Tpay> tpays) {
		this.tpays = tpays;
	}


	@Column(name = "cfuid", length = 36)
	public String getCfuid() {
		return cfuid;
	}

	public void setCfuid(String cfuid) {
		this.cfuid = cfuid;
	}
	
	@Column(name = "cfpid", length = 36)
	public String getCfpid() {
		return cfpid;
	}

	public void setCfpid(String cfpid) {
		this.cfpid = cfpid;
	}

}
