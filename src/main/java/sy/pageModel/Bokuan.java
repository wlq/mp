package sy.pageModel;

import java.util.Date;

public class Bokuan implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)	
	private String cname;
	private String cpame;
	private Project tproject;	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCpame() {
		return cpame;
	}
	public void setCpame(String cpame) {
		this.cpame = cpame;
	}
	public Project getTproject() {
		return tproject;
	}
	public void setTproject(Project tproject) {
		this.tproject = tproject;
	}
	
	
	private String cid;
	//private String cuid;//用户id
	private String ctickets;//支出凭证的票据号
	private String cprojectid;//获得拨款的课题编号
	private Float cmoney;//拨款金额
	private Date ccountTime;//记账时间
	private String ccountId;//会计凭证号
	private String cdatei;//票据的上传地址	
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
	/*public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}*/
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
}
