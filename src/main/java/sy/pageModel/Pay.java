package sy.pageModel;


import sy.model.Tpay;
import sy.model.Tuser;

public class Pay implements java.io.Serializable {
	
	// 自定义属性
	private String id;
	private String state = "open";// 是否展开(open,closed)
	private String cpid;
	private String cpname;
	private User tuser;
	private String cuid;
	private String cuname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	public User getTuser() {
		return tuser;
	}
	public void setTuser(User tuser) {
		this.tuser = tuser;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getCuname() {
		return cuname;
	}
	public void setCuname(String cuname) {
		this.cuname = cuname;
	}
	
	private String cid;
	private Tpay tpay;
	private String cseq;
	private String cname;
	private String cresponser;
	private Float cbudget;
	private Double ccost;
	private Float cbalance;	
	private Float cmoney;
	private String cfuid;
	private String cfpid;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Tpay getTpay() {
		return tpay;
	}
	public void setTpay(Tpay tpay) {
		this.tpay = tpay;
	}
	public String getCseq() {
		return cseq;
	}
	public void setCseq(String cseq) {
		this.cseq = cseq;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCresponser() {
		return cresponser;
	}
	public void setCresponser(String cresponser) {
		this.cresponser = cresponser;
	}	
	public Double getCcost() {
		return ccost;
	}
	public void setCcost(Double ccost) {
		this.ccost = ccost;
	}
	public Float getCbalance() {
		return cbalance;
	}
	public void setCbalance(Float cbalance) {
		this.cbalance = cbalance;
	}
	public Float getCbudget() {
		return cbudget;
	}
	public void setCbudget(Float cbudget) {
		this.cbudget = cbudget;
	}
	public Float getCmoney() {
		return cmoney;
	}
	public void setCmoney(Float cmoney) {
		this.cmoney = cmoney;
	}
	
	public String getCfuid() {
		return cfuid;
	}
	public void setCfuid(String cfuid) {
		this.cfuid = cfuid;
	}
	public String getCfpid() {
		return cfpid;
	}
	public void setCfpid(String cfpid) {
		this.cfpid = cfpid;
	}

}
