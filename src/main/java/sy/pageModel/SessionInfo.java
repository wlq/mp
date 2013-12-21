package sy.pageModel;

import java.util.List;

/**
 * sessionInfo模型
 * 
 * @author
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private String userId;// 用户ID
	private String loginName;// 用户登录名称
	private String loginPassword;// 登录密码
	private String ip;// IP地址
	private List<Auth> auths;// 用户拥有的权限
	private String groupId;//用户工作组
	private String course;//科目跳转 9.12
	private String ccd;//存放通知数

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return loginName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCcd() {
		return ccd;
	}

	public void setCcd(String ccd) {
		this.ccd = ccd;
	}

}
