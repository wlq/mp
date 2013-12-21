package sy.pageModel;

import java.util.Date;

public class Log {

	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	// Fields

		private String cid;
		private String cname;
		private String cip;
		private Date clogintime;
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
		public String getCip() {
			return cip;
		}
		public void setCip(String cip) {
			this.cip = cip;
		}
		public Date getClogintime() {
			return clogintime;
		}
		public void setClogintime(Date clogintime) {
			this.clogintime = clogintime;
		}
		
}
