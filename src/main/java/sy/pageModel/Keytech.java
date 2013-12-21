package sy.pageModel;

import java.util.Date;

public class Keytech implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	
	private String cid;
	private String cname;
	private String cresponser;
	private String ccompany;
	private Date cstarttime;
	private Date cendtime;
	private String ckeywords;
	private String csummary;
	private String cphone;
	private String ctypeman;
	private Date ctypetime;
	private String cflag;

	// Constructors

	public String getCflag() {
		return cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
	public String getCcompany() {
		return ccompany;
	}
	public void setCcompany(String ccompany) {
		this.ccompany = ccompany;
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
	public String getCkeywords() {
		return ckeywords;
	}
	public void setCkeywords(String ckeywords) {
		this.ckeywords = ckeywords;
	}
	public String getCsummary() {
		return csummary;
	}
	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCtypeman() {
		return ctypeman;
	}
	public void setCtypeman(String ctypeman) {
		this.ctypeman = ctypeman;
	}
	public Date getCtypetime() {
		return ctypetime;
	}
	public void setCtypetime(Date ctypetime) {
		this.ctypetime = ctypetime;
	}
	
}
