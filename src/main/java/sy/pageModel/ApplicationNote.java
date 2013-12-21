package sy.pageModel;

import java.util.Date;

public class ApplicationNote implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	
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
	public String getCsuitableid() {
		return csuitableid;
	}
	public void setCsuitableid(String csuitableid) {
		this.csuitableid = csuitableid;
	}
	public String getCinformation() {
		return cinformation;
	}
	public void setCinformation(String cinformation) {
		this.cinformation = cinformation;
	}
	public String getCnote() {
		return cnote;
	}
	public void setCnote(String cnote) {
		this.cnote = cnote;
	}
	public String getCevaluation() {
		return cevaluation;
	}
	public void setCevaluation(String cevaluation) {
		this.cevaluation = cevaluation;
	}
	public String getCidentify() {
		return cidentify;
	}
	public void setCidentify(String cidentify) {
		this.cidentify = cidentify;
	}
	public String getCrighted() {
		return crighted;
	}
	public void setCrighted(String crighted) {
		this.crighted = crighted;
	}
	public String getCcontactid() {
		return ccontactid;
	}
	public void setCcontactid(String ccontactid) {
		this.ccontactid = ccontactid;
	}
	public Date getCtypetime() {
		return ctypetime;
	}
	public void setCtypetime(Date ctypetime) {
		this.ctypetime = ctypetime;
	}
	
	
}
