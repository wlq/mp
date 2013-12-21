package sy.pageModel;

import java.util.Date;

public class Notice  implements java.io.Serializable {

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	
	private String cid;
	private String csend;//通知发送人
	private String cget;//通知接收人
	private Date csendtime;//发送时间
	private String cdatei;//通知内容
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
	public String getCsend() {
		return csend;
	}
	public void setCsend(String csend) {
		this.csend = csend;
	}
	public String getCget() {
		return cget;
	}
	public void setCget(String cget) {
		this.cget = cget;
	}
	public Date getCsendtime() {
		return csendtime;
	}
	public void setCsendtime(Date csendtime) {
		this.csendtime = csendtime;
	}
	public String getCdatei() {
		return cdatei;
	}
	public void setCdatei(String cdatei) {
		this.cdatei = cdatei;
	}
}
