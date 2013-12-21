package sy.pageModel;

import java.util.Date;

public class EmpiricalResearch implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)

	// Fields

	private String cid;
	private String cclassify;
	private String cname;
	private String clanguage;
	private String cckeyword;
	private String cekeyword;
	private String cinformation;
	private String cnote;
	private String cstorage;
	private String cyear;
	private String cunit;
	private String ccontactid;
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
	public String getCclassify() {
		return cclassify;
	}
	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getClanguage() {
		return clanguage;
	}
	public void setClanguage(String clanguage) {
		this.clanguage = clanguage;
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
	public String getCstorage() {
		return cstorage;
	}
	public void setCstorage(String cstorage) {
		this.cstorage = cstorage;
	}
	public String getCyear() {
		return cyear;
	}
	public void setCyear(String cyear) {
		this.cyear = cyear;
	}
	public String getCunit() {
		return cunit;
	}
	public void setCunit(String cunit) {
		this.cunit = cunit;
	}
	public String getCcontactid() {
		return ccontactid;
	}
	public void setCcontactid(String ccontactid) {
		this.ccontactid = ccontactid;
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
