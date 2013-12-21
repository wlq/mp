package sy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wei
 *
 */
@Entity
@Table(name = "Tbokuan", schema = "")
public class Tbokuan implements java.io.Serializable{

	// Fields
	
	private String cid;
	//private String cuid;//用户id
	private String ctickets;//支出凭证的票据号
	private String cprojectid;//获得拨款的课题编号
	private Float cmoney;//拨款金额
	private Date ccountTime;//记账时间
	private String ccountId;//会计凭证号
	private String cdatei;//票据的上传地址
	
	public Tbokuan() {
	}

	public Tbokuan(String cid, String ctickets, String cprojectid,
			Float cmoney, Date ccountTime, String ccountId, String cdatei) {
		this.cid = cid;
		//this.cuid = cuid;
		this.ctickets = ctickets;
		this.cprojectid = cprojectid;
		this.cmoney = cmoney;
		this.ccountTime = ccountTime;
		this.ccountId = ccountId;
		this.cdatei = cdatei;
	}

	@Id
	@Column(name = "CID", nullable = false, length = 36)
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	/*@Column(name = "CUID", nullable = false, length = 100)
	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}*/
	
	@Column(name = "Ctickets", length = 100)
	public String getCtickets() {
		return ctickets;
	}

	public void setCtickets(String ctickets) {
		this.ctickets = ctickets;
	}

	@Column(name = "Cprojectid", length = 100)
	public String getCprojectid() {
		return cprojectid;
	}

	public void setCprojectid(String cprojectid) {
		this.cprojectid = cprojectid;
	}
	
	@Column(name = "cmoney", precision = 12, scale = 0)
	public Float getCmoney() {
		return cmoney;
	}

	public void setCmoney(Float cmoney) {
		this.cmoney = cmoney;
	}
	@Column(name = "ccounttime", nullable = false)
	public Date getCcountTime() {
		return ccountTime;
	}

	public void setCcountTime(Date ccountTime) {
		this.ccountTime = ccountTime;
	}
	@Column(name = "ccountid",length = 100)
	public String getCcountId() {
		return ccountId;
	}

	public void setCcountId(String ccountId) {
		this.ccountId = ccountId;
	}
	@Column(name = "cdatei", length = 100)
	public String getCdatei() {
		return cdatei;
	}

	public void setCdatei(String cdatei) {
		this.cdatei = cdatei;
	}
	
	

}
