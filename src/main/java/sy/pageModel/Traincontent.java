package sy.pageModel;

import java.math.BigDecimal;
import java.util.Date;

import sy.model.Ttraincontent;
import sy.model.Tuser;

/**
 * 
 * @author
 * 
 */

public class Traincontent implements java.io.Serializable {

	// 自定义属性
	private String id;
	private String state = "open";// 是否展开(open,closed)
	private String cpid;
	private String cpname;
	private String iconCls;
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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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
	private String ccontent;
	private String cstyle;
	private String cplace;
	private String cnum;
	private String cmaterial;
	private String cassess;
	
	
	public Float getCprogress() {
		return cprogress;
	}

	public void setCprogress(Float cprogress) {
		this.cprogress = cprogress;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCiconcls() {
		return ciconcls;
	}

	public void setCiconcls(String ciconcls) {
		this.ciconcls = ciconcls;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public BigDecimal getCseq() {
		return cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public Date getCstarttime() {
		return cstarttime;
	}

	public void setCstarttime(Date cstarttime) {
		this.cstarttime = cstarttime;
	}

	public Date getCendtime() {
		return cendtime;
	}

	public void setCendtime(Date cendtime) {
		this.cendtime = cendtime;
	}

	public String getCresponsecompany() {
		return cresponsecompany;
	}

	public void setCresponsecompany(String cresponsecompany) {
		this.cresponsecompany = cresponsecompany;
	}

	public String getCresponser() {
		return cresponser;
	}

	public void setCresponser(String cresponser) {
		this.cresponser = cresponser;
	}

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	public Float getCbudget() {
		return cbudget;
	}

	public void setCbudget(Float cbudget) {
		this.cbudget = cbudget;
	}
	
	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	
	public String getCstyle() {
		return cstyle;
	}

	public void setCstyle(String cstyle) {
		this.cstyle = cstyle;
	}

	public String getCplace() {
		return cplace;
	}

	public void setCplace(String cplace) {
		this.cplace = cplace;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public String getCmaterial() {
		return cmaterial;
	}

	public void setCmaterial(String cmaterial) {
		this.cmaterial = cmaterial;
	}

	public String getCassess() {
		return cassess;
	}

	public void setCassess(String cassess) {
		this.cassess = cassess;
	}

}
