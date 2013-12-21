package sy.pageModel;

public class Resource implements java.io.Serializable{

	// 自己添加的属性
	private String ids;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	
	// Fields

	private String cid;
	private String cname;
	private String cclassify;
	private String csummary;
	private Integer cmount;
	private String cinformation;
	private String cstorage;
	private String cunit;
	private String cprinciple;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCclassify() {
		return cclassify;
	}
	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}
	public String getCsummary() {
		return csummary;
	}
	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}
	public Integer getCmount() {
		return cmount;
	}
	public void setCmount(Integer cmount) {
		this.cmount = cmount;
	}
	public String getCinformation() {
		return cinformation;
	}
	public void setCinformation(String cinformation) {
		this.cinformation = cinformation;
	}
	public String getCstorage() {
		return cstorage;
	}
	public void setCstorage(String cstorage) {
		this.cstorage = cstorage;
	}
	public String getCunit() {
		return cunit;
	}
	public void setCunit(String cunit) {
		this.cunit = cunit;
	}
	public String getCprinciple() {
		return cprinciple;
	}
	public void setCprinciple(String cprinciple) {
		this.cprinciple = cprinciple;
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
