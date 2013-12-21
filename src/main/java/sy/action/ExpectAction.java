package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.Tuser;
import sy.pageModel.Json;
import sy.pageModel.Expect;
import sy.pageModel.SessionInfo;
import sy.pageModel.User;
import sy.service.ExpectServiceI;
import sy.util.ExceptionUtil;
import sy.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单ACTION
 * 
 * @author 
 * 
 */
@Action(value = "expectAction", results = { @Result(name = "expect", location = "/admin/expect.jsp") })
public class ExpectAction extends BaseAction implements ModelDriven<Expect> {

	private static final Logger logger = Logger.getLogger(ExpectAction.class);

	private ExpectServiceI expectService;

	private Expect expect = new Expect();
	private User user = new User();

	public Expect getModel() {
		return expect;
	}

	public ExpectServiceI getExpectService() {
		return expectService;
	}

	@Autowired
	public void setExpectService(ExpectServiceI ExpectService) {
		this.expectService = ExpectService;
	}

	/**
	 * 首页获得项目树
	 */
	public void ctrlTree() {
		writeJson(expectService.tree(expect, false));
	}

	/**
	 * 跳转到项目管理页面
	 * 
	 * @return
	 */
	public String expect() {
		return "expect";
	}
	
	/**
	 * 获得项目treegrid
	 */
	public void treegrid() {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		//user.setCid(sessionInfo.getUserId());
		//expect.setUserId(sessionInfo.getUserId());
		//expect.setTuser(user.setCid(sessionInfo.getUserId()));
		user.setCid(sessionInfo.getUserId());
		expect.setTuser(user);
		writeJson(expectService.treegrid(expect));
	}

	/**
	 * 获取项目树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(expectService.tree(expect, true));
	}

	/**
	 * 所有人都有权限的
	 */
	public void expectTreeRecursive() {

		writeJson(expectService.tree(expect, true));
	}

	/**
	 * 编辑项目
	 */
	public void edit() {
		Json j = new Json();
		try {
			expectService.edit(expect);
			j.setSuccess(true);
			j.setMsg("编辑成功!请手动刷新项目管理页面！");
			j.setObj(expect.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	/**
	 * 添加项目
	 */
	public void add() {
		Json j = new Json();
		try {
			expectService.add(expect);
			j.setSuccess(true);
			j.setMsg("添加成功!请手动刷新项目管理页面！");
			j.setObj(expect.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		writeJson(j);
	}

	/**
	 * 删除一个项目
	 */
	public void delete() {
		Json j = new Json();
		try {
			expectService.delete(expect);
			j.setSuccess(true);
			j.setMsg("删除成功！请手动刷新项目管理页面！");
			j.setObj(expect.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

	/**
	 * 获得用户下拉列表
	 */
	public void expectCombobox() {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String groupId = sessionInfo.getGroupId();
		logger.info(groupId);
		writeJson(expectService.combobox(groupId));
	}
	
	/**
	 * 更改状态
	 * wei
	 */
	public void changeStatus() {
		Json j = new Json();
		try {
			expectService.changeStatus(expect);
			expectService.editProgress(expect);	
			j.setSuccess(true);
			j.setMsg("更改状态成功!请手动刷新项目管理页面！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}
	
}
