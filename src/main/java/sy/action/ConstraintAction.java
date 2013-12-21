package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.Tuser;
import sy.pageModel.Json;
import sy.pageModel.Constraint;
import sy.pageModel.SessionInfo;
import sy.pageModel.User;
import sy.service.ConstraintServiceI;
import sy.util.ExceptionUtil;
import sy.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单ACTION
 * 
 * @author 
 * 
 */
@Action(value = "constraintAction", results = { @Result(name = "constraint", location = "/admin/constraint.jsp") })
public class ConstraintAction extends BaseAction implements ModelDriven<Constraint> {

	private static final Logger logger = Logger.getLogger(ConstraintAction.class);

	private ConstraintServiceI constraintService;

	private Constraint constraint = new Constraint();
	private User user = new User();

	public Constraint getModel() {
		return constraint;
	}

	public ConstraintServiceI getconstraintService() {
		return constraintService;
	}

	@Autowired
	public void setconstraintService(ConstraintServiceI constraintService) {
		this.constraintService = constraintService;
	}

	/**
	 * 首页获得项目树
	 */
	public void ctrlTree() {
		writeJson(constraintService.tree(constraint, false));
	}

	/**
	 * 跳转到项目管理页面
	 * 
	 * @return
	 */
	public String constraint() {
		return "constraint";
	}
	
	/**
	 * 获得项目treegrid
	 */
	public void treegrid() {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		//user.setCid(sessionInfo.getUserId());
		//constraint.setUserId(sessionInfo.getUserId());
		//constraint.setTuser(user.setCid(sessionInfo.getUserId()));
		user.setCid(sessionInfo.getUserId());
		constraint.setTuser(user);
		writeJson(constraintService.treegrid(constraint));
	}

	/**
	 * 获取项目树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(constraintService.tree(constraint, true));
	}

	/**
	 * 所有人都有权限的
	 */
	public void constraintTreeRecursive() {

		writeJson(constraintService.tree(constraint, true));
	}

	/**
	 * 编辑项目
	 */
	public void edit() {
		Json j = new Json();
		try {
			constraintService.edit(constraint);
			j.setSuccess(true);
			j.setMsg("编辑成功!请手动刷新项目管理页面！");
			j.setObj(constraint.getCpid());
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
			constraintService.add(constraint);
			j.setSuccess(true);
			j.setMsg("添加成功!请手动刷新项目管理页面！");
			j.setObj(constraint.getCpid());
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
			constraintService.delete(constraint);
			j.setSuccess(true);
			j.setMsg("删除成功！请手动刷新项目管理页面！");
			j.setObj(constraint.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

	/**
	 * 获得用户下拉列表
	 */
	public void constraintCombobox() {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String groupId = sessionInfo.getGroupId();
		logger.info(groupId);
		writeJson(constraintService.combobox(groupId));
	}
	
	/**
	 * 更改状态
	 * @author wei
	 */
	public void changeStatus() {
		Json j = new Json();
		try {
			constraintService.changeStatus(constraint);
			constraintService.editProgress(constraint);	
			j.setSuccess(true);
			j.setMsg("更改状态成功!请手动刷新页面！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}
	
}
