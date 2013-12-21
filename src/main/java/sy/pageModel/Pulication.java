package sy.pageModel;

import java.util.Date;

public class Pulication implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	// Fields

	private String cid;
	private String cname;
	private String cauthor;
	private String cckeyword;
	private String cekeyword;
	private String cpublishhous;
	private Date cpublishtime;
	private Integer cwords;
	private String cisbn;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCauthor() {
		return cauthor;
	}
	public void setCauthor(String cauthor) {
		this.cauthor = cauthor;
	}
	public String getCckeyword() {
		return cckeyword;
	}
	public void setCckeyword(String cckeyword) {
		this.cckeyword = cckeyword;
	}
	public String getCekeyword() {
		return cekeyword;
	}
	public void setCekeyword(String cekeyword) {
		this.cekeyword = cekeyword;
	}
	public String getCpublishhous() {
		return cpublishhous;
	}
	public void setCpublishhous(String cpublishhous) {
		this.cpublishhous = cpublishhous;
	}
	public Date getCpublishtime() {
		return cpublishtime;
	}
	public void setCpublishtime(Date cpublishtime) {
		this.cpublishtime = cpublishtime;
	}
	public Integer getCwords() {
		return cwords;
	}
	public void setCwords(Integer cwords) {
		this.cwords = cwords;
	}
	public String getCisbn() {
		return cisbn;
	}
	public void setCisbn(String cisbn) {
		this.cisbn = cisbn;
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
