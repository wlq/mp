package sy.pageModel;

import java.sql.Timestamp;
import java.util.Date;

public class Patent implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	
	private String cid;
	private String ccountry;
	private String cname;
	private String cnumber;
	private String cclassify;
	private String cinvent;
	private String cpatentee;
	private String csummary;
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
	public String getCcountry() {
		return ccountry;
	}
	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public String getCclassify() {
		return cclassify;
	}
	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}
	public String getCinvent() {
		return cinvent;
	}
	public void setCinvent(String cinvent) {
		this.cinvent = cinvent;
	}
	public String getCpatentee() {
		return cpatentee;
	}
	public void setCpatentee(String cpatentee) {
		this.cpatentee = cpatentee;
	}
	public String getCsummary() {
		return csummary;
	}
	public void setCsummary(String csummary) {
		this.csummary = csummary;
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
