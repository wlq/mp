package sy.pageModel;

import java.util.Date;

public class Device implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	// Fields

	private String cid;
	private String cnumber;
	private String csource;
	private String ccname;
	private String cename;
	private Date cresearchtime;
	private String cversion;
	private String cfield;
	private String cfactory;
	private Double cprice;
	private String cnote;
	private String cunit;
	private String ccontactid;
	private String cbackup1;
	private String cbackup2;
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
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public String getCsource() {
		return csource;
	}
	public void setCsource(String csource) {
		this.csource = csource;
	}
	public String getCcname() {
		return ccname;
	}
	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	public String getCename() {
		return cename;
	}
	public void setCename(String cename) {
		this.cename = cename;
	}
	public Date getCresearchtime() {
		return cresearchtime;
	}
	public void setCresearchtime(Date cresearchtime) {
		this.cresearchtime = cresearchtime;
	}
	public String getCversion() {
		return cversion;
	}
	public void setCversion(String cversion) {
		this.cversion = cversion;
	}
	public String getCfield() {
		return cfield;
	}
	public void setCfield(String cfield) {
		this.cfield = cfield;
	}
	public String getCfactory() {
		return cfactory;
	}
	public void setCfactory(String cfactory) {
		this.cfactory = cfactory;
	}
	public Double getCprice() {
		return cprice;
	}
	public void setCprice(Double cprice) {
		this.cprice = cprice;
	}
	public String getCnote() {
		return cnote;
	}
	public void setCnote(String cnote) {
		this.cnote = cnote;
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
	public String getCbackup1() {
		return cbackup1;
	}
	public void setCbackup1(String cbackup1) {
		this.cbackup1 = cbackup1;
	}
	public String getCbackup2() {
		return cbackup2;
	}
	public void setCbackup2(String cbackup2) {
		this.cbackup2 = cbackup2;
	}

}
