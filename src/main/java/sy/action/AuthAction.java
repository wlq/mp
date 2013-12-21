package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.pageModel.Auth;
import sy.pageModel.Json;
import sy.service.AuthServiceI;
import sy.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 权限ACTION
 * 
 * @author 
 * 
 */
@Action(value = "authAction", results = { @Result(name = "auth", location = "/admin/auth.jsp") })
public class AuthAction extends BaseAction implements ModelDriven<Auth> {

	private static final Logger logger = Logger.getLogger(AuthAction.class);

	private Auth auth = new Auth();
	private AuthServiceI authService;

	public AuthServiceI getAuthService() {
		return authService;
	}

	@Autowired
	public void setAuthService(AuthServiceI authService) {
		this.authService = authService;
	}

	public Auth getModel() {
		return auth;
	}

	public String auth() {
		return "auth";
	}

	/**
	 * 获得权限treegrid
	 */
	public void treegrid() {
		writeJson(authService.treegrid(auth));
	}

	/**
	 * 删除一个权限
	 */
	public void delete() {
		Json j = new Json();
		try {
			authService.delete(auth);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(auth.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

	/**
	 * 获取权限树
	 */
	public void tree() {
		writeJson(authService.tree(auth, false));
	}

	/**
	 * 获取权限树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(authService.tree(auth, true));
	}

	/**
	 * 权限树,所有人都有权限访问这个
	 */
	public void authTreeRecursive() {
		writeJson(authService.tree(auth, true));
	}

	/**
	 * 编辑权限
	 */
	public void edit() {
		Json j = new Json();
		try {
			authService.edit(auth);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
			j.setObj(auth.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	/**
	 * 添加权限
	 */
	public void add() {
		Json j = new Json();
		try {
			authService.add(auth);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(auth.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		writeJson(j);
	}

}
