package sy.pageModel;

import java.util.Date;

public class Soft implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
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
	public String getCsoftid() {
		return csoftid;
	}
	public void setCsoftid(String csoftid) {
		this.csoftid = csoftid;
	}
	public String getCsoftname() {
		return csoftname;
	}
	public void setCsoftname(String csoftname) {
		this.csoftname = csoftname;
	}
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	public Date getCdonetime() {
		return cdonetime;
	}
	public void setCdonetime(Date cdonetime) {
		this.cdonetime = cdonetime;
	}
	public Date getCfirsttime() {
		return cfirsttime;
	}
	public void setCfirsttime(Date cfirsttime) {
		this.cfirsttime = cfirsttime;
	}
	public String getCway() {
		return cway;
	}
	public void setCway(String cway) {
		this.cway = cway;
	}
	public String getCright() {
		return cright;
	}
	public void setCright(String cright) {
		this.cright = cright;
	}
	public String getCcommitid() {
		return ccommitid;
	}
	public void setCcommitid(String ccommitid) {
		this.ccommitid = ccommitid;
	}
}
