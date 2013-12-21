package sy.pageModel;

import java.util.Date;

public class Kemu implements java.io.Serializable {

	// 自己添加的属性
	private Date ccountTimeStart;//记账时间
	private Date ccountTimeEnd;//记账时间
	private String ids;
	public Date getCcountTimeStart() {
		return ccountTimeStart;
	}
	public void setCcountTimeStart(Date ccountTimeStart) {
		this.ccountTimeStart = ccountTimeStart;
	}
	public Date getCcountTimeEnd() {
		return ccountTimeEnd;
	}
	public void setCcountTimeEnd(Date ccountTimeEnd) {
		this.ccountTimeEnd = ccountTimeEnd;
	}
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	
	private String cid;
	private String cname;//科目支出明细
	private String ctickets;//支出凭证的票据号
	private String cprojectid;//所属课题编号
	private Float cmoney;//金额
	private Date ccountTime;//记账时间
	private String ccountId;//会计凭证号
	private String cdatei;//票据的上传地址
	private String ccourse;//科目名称
	
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
	public String getCtickets() {
		return ctickets;
	}
	public void setCtickets(String ctickets) {
		this.ctickets = ctickets;
	}
	public String getCprojectid() {
		return cprojectid;
	}
	public void setCprojectid(String cprojectid) {
		this.cprojectid = cprojectid;
	}
	public Float getCmoney() {
		return cmoney;
	}
	public void setCmoney(Float cmoney) {
		this.cmoney = cmoney;
	}
	public Date getCcountTime() {
		return ccountTime;
	}
	public void setCcountTime(Date ccountTime) {
		this.ccountTime = ccountTime;
	}
	public String getCcountId() {
		return ccountId;
	}
	public void setCcountId(String ccountId) {
		this.ccountId = ccountId;
	}
	public String getCdatei() {
		return cdatei;
	}
	public void setCdatei(String cdatei) {
		this.cdatei = cdatei;
	}
	public String getCcourse() {
		return ccourse;
	}
	public void setCcourse(String ccourse) {
		this.ccourse = ccourse;
	}
	
}
