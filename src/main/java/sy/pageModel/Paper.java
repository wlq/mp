package sy.pageModel;

import java.util.Date;

public class Paper implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)

	private String cid;
	private String cname;
	private String cckeyword;
	private String cekeyword;
	private String csummary;
	private String clanguage;
	private String cfcontactid;
	private String cccontactid;
	private String cperiodical;
	private String cissue;
	private String cstate;
	private Date cpublishtime;
	private String cclassify;
	private String cinde;
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
	public String getCsummary() {
		return csummary;
	}
	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}
	public String getClanguage() {
		return clanguage;
	}
	public void setClanguage(String clanguage) {
		this.clanguage = clanguage;
	}
	public String getCfcontactid() {
		return cfcontactid;
	}
	public void setCfcontactid(String cfcontactid) {
		this.cfcontactid = cfcontactid;
	}
	public String getCccontactid() {
		return cccontactid;
	}
	public void setCccontactid(String cccontactid) {
		this.cccontactid = cccontactid;
	}
	public String getCperiodical() {
		return cperiodical;
	}
	public void setCperiodical(String cperiodical) {
		this.cperiodical = cperiodical;
	}
	public String getCissue() {
		return cissue;
	}
	public void setCissue(String cissue) {
		this.cissue = cissue;
	}
	public String getCstate() {
		return cstate;
	}
	public void setCstate(String cstate) {
		this.cstate = cstate;
	}
	public Date getCpublishtime() {
		return cpublishtime;
	}
	public void setCpublishtime(Date cpublishtime) {
		this.cpublishtime = cpublishtime;
	}
	public String getCclassify() {
		return cclassify;
	}
	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}
	public String getCinde() {
		return cinde;
	}
	public void setCinde(String cinde) {
		this.cinde = cinde;
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
