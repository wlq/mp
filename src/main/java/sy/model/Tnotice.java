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
@Table(name = "Tnotice", schema = "")
public class Tnotice implements java.io.Serializable {

	// Fields
	
	private String cid;
	private String csend;//通知发送人
	private String cget;//通知接收人
	private Date csendtime;//发送时间
	private String cdatei;//通知内容
	

	/** default constructor */
	public Tnotice() {
	}
	
	public Tnotice(String cid, String csend, String cget, Date csendtime,
			String cdatei) {
		this.cid = cid;
		this.csend = csend;
		this.cget = cget;
		this.csendtime = csendtime;
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
	
	@Column(name = "Csend", nullable = false, length = 100)
	public String getCsend() {
		return csend;
	}

	public void setCsend(String csend) {
		this.csend = csend;
	}
	@Column(name = "Cget", nullable = false, length = 100)

	public String getCget() {
		return cget;
	}

	public void setCget(String cget) {
		this.cget = cget;
	}
	@Column(name = "csendtime", nullable = false)
	public Date getCsendtime() {
		return csendtime;
	}

	public void setCsendtime(Date csendtime) {
		this.csendtime = csendtime;
	}

	public String getCdatei() {
		return cdatei;
	}
	@Column(name = "cdatei", nullable = false, length = 65532)
	public void setCdatei(String cdatei) {
		this.cdatei = cdatei;
	}
	
	
}
