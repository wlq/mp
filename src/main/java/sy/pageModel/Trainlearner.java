package sy.pageModel;

import java.sql.Timestamp;
import java.util.Date;

public class Trainlearner implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	
	private String cid;
	private String cseq;
	private String ccontent;
	private String cname;
	private String csex;
	private String cage;
	private String ciden;
	private String ccompany;
	private String ctitle;
	private String cmajor;
	private String cphone;
	private String cmail;
	private String cresult;
	private String cnum;
	private String cstyle;

	// Constructors

	
	
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
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCseq() {
		return cseq;
	}
	public void setCseq(String cseq) {
		this.cseq = cseq;
	}
	public String getCsex() {
		return csex;
	}
	public void setCsex(String csex) {
		this.csex = csex;
	}
	public String getCiden() {
		return ciden;
	}
	public void setCiden(String ciden) {
		this.ciden = ciden;
	}
	public String getCage() {
		return cage;
	}
	public void setCage(String cage) {
		this.cage = cage;
	}
	public String getCcompany() {
		return ccompany;
	}
	public void setCcompany(String ccompany) {
		this.ccompany = ccompany;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public String getCmajor() {
		return cmajor;
	}
	public void setCmajor(String cmajor) {
		this.cmajor = cmajor;
	}
	public String getCmail() {
		return cmail;
	}
	public void setCmail(String cmail) {
		this.cmail = cmail;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getCresult() {
		return cresult;
	}
	public void setCresult(String cresult) {
		this.cresult = cresult;
	}
	public String getCstyle() {
		return cstyle;
	}
	public void setCstyle(String cstyle) {
		this.cstyle = cstyle;
	}
	

}
