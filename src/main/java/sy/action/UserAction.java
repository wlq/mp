package sy.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.pageModel.Json;
import sy.pageModel.Log;
import sy.pageModel.SessionInfo;
import sy.pageModel.User;
import sy.service.LogServiceI;
import sy.service.UserServiceI;
import sy.util.ExceptionUtil;
import sy.util.IpUtil;
import sy.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户ACTION
 * 
 * @author 
 * 
 */
@Action(value = "userAction", results = { @Result(name = "user", location = "/admin/user.jsp"), @Result(name = "showUserInfo", location = "/user/userInfo.jsp") })
public class UserAction extends BaseAction implements ModelDriven<User> {

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private User user = new User();
	private UserServiceI userService;
	private Log log = new Log();
	private LogServiceI logService;
	
	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public LogServiceI getLogService() {
		return logService;
	}

	@Autowired
	public void setLogService(LogServiceI logService) {
		this.logService = logService;
	}

	public User getModel() {
		return user;
	}

	/**
	 * 用户登录
	 */	
	/*public String login() {
		
		Json j = new Json();
		try {
			ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = ServletActionContext.getRequest().getParameter("cname");
		user.setCname(name);
		User u = userService.login(user);
		
		if (u != null) {
			SessionInfo sessionInfo = saveSessionInfo(u);
			j.setSuccess(true);
			j.setMsg("用户登录成功！");
			j.setObj(sessionInfo);
			changeUserAuths(u);
			return "loginsuccess";
		} else {
			j.setMsg("用户名或密码错误!");
			writeJson(j);
			return "";
		}
		
	}*/
	public void login() {
		Json j = new Json();
		User u = userService.login(user);
		if (u != null) {
			SessionInfo sessionInfo = saveSessionInfo(u);
			log.setCid(UUID.randomUUID().toString());
			log.setCip(IpUtil.getIpAddr(ServletActionContext.getRequest()));
			log.setClogintime(new Date());
			log.setCname(u.getCname());
			logService.add(log);
			j.setSuccess(true);
			j.setMsg("用户登录成功！");
			j.setObj(sessionInfo);
			changeUserAuths(u);
		} else {
			j.setMsg("用户名或密码错误!");
		}
		writeJson(j);
	}

	/**
	 * 改变用户的权限列表
	 * 
	 * @param u
	 */
	private void changeUserAuths(User u) {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		sessionInfo.setAuths(userService.getAuths(u));
	}

	/**
	 * 登录成功将用户信息保存到SESSION中
	 * 
	 * @param u
	 *            用户对象
	 * @return
	 */
	private SessionInfo saveSessionInfo(User u) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setUserId(u.getCid());
		sessionInfo.setLoginName(u.getCname());
		sessionInfo.setLoginPassword(user.getCpwd());
		sessionInfo.setIp(IpUtil.getIpAddr(ServletActionContext.getRequest()));
		sessionInfo.setGroupId(u.getCgroupid());
		sessionInfo.setCcd(String.valueOf(u.getCno()));
		
		ServletActionContext.getRequest().getSession().setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
		return sessionInfo;
	}

	/**
	 * 退出系统
	 */
	public void logout() {
		Json j = new Json();
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 表格方式用户登录
	 * 
	 * 该函数关联east.jsp。2013-3-11日弃用（未删除）。
	 * 
	 */
	public void loginDatagrid() {
		if (user.getQ() != null && !user.getQ().trim().equals("")) {
			user.setCname(user.getQ());
		}
		writeJson(userService.datagrid(user));
	}

	/**
	 * 补全方式用户登录
	 */
	public void loginCombobox() {
		writeJson(userService.loginCombobox(user));
	}

	/**
	 * 用户注册
	 */
	public void reg() {
		Json j = new Json();
		try {
			userService.reg(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg("用户名已存在！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */
	public String user() {
		return "user";
	}
	
    public String loginsuccess()
    {
    	return "loginsuccess";
    	
    }
	/**
	 * 获得用户datagrid
	 */
	public void datagrid() {
		writeJson(userService.datagrid(user));
	}
	
	/**
	 * 用户详细信息
	 */
	
	public void userInfo(){
		
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		user.setCid(sessionInfo.getUserId());
		writeJson(userService.userInfo(user));
	}

	/**
	 * 添加一个用户
	 */
	public void add() {
		Json j = new Json();
		try {
			userService.add(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg("用户名已存在！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}
//
	public void editUserInfo() {
		Json j = new Json();
		try {
			userService.editUserInfo(user);
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("修改失败！");
		}
		super.writeJson(j);
	}
	
	/**
	 * 编辑一个用户
	 */	
	public void edit() {
		Json j = new Json();
		try {
			userService.update(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败，用户名已存在！");
		}
		writeJson(j);
	}

	/**
	 * 删除一个或多个用户
	 */
	public void delete() {
		Json j = new Json();
		userService.delete(user.getIds());
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 批量修改用户密码
	 */
	public void modifyPwd() {
		Json j = new Json();
		userService.modifyPwd(user);
		j.setSuccess(true);
		j.setMsg("密码修改成功！");
		writeJson(j);
	}

	/**
	 * 跳转到当前用户信息页面
	 * 
	 * @return
	 */
	public String showUserInfo() {
		return "showUserInfo";
	}


	/**
	 * 修改自己的密码
	 */
	public void modifySelfPwd() {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		user.setCid(sessionInfo.getUserId());
		if (userService.modifySelfPwd(user)) {
			j.setSuccess(true);
			j.setMsg("密码修改成功！");
		} else {
			j.setMsg("修改密码失败！原密码错误！");
		}
		writeJson(j);
	}

	/**
	 * 批量修改用户角色
	 */
	public void modifyUserRole() {
		Json j = new Json();
		userService.modifyUserRole(user);
		j.setSuccess(true);
		j.setMsg("角色修改成功！");
		writeJson(j);
	}
}
