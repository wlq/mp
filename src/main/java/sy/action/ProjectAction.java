package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.Tuser;
import sy.pageModel.Json;
import sy.pageModel.Project;
import sy.pageModel.SessionInfo;
import sy.pageModel.User;
import sy.service.ProjectServiceI;
import sy.util.ExceptionUtil;
import sy.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单ACTION
 * 
 * @author 
 * 
 */
@Action(value = "projectAction", results = { @Result(name = "project", location = "/admin/project.jsp") })
public class ProjectAction extends BaseAction implements ModelDriven<Project> {

	private static final Logger logger = Logger.getLogger(ProjectAction.class);

	private ProjectServiceI projectService;

	private Project project = new Project();
	private User user = new User();

	public Project getModel() {
		return project;
	}

	public ProjectServiceI getProjectService() {
		return projectService;
	}

	@Autowired
	public void setProjectService(ProjectServiceI ProjectService) {
		this.projectService = ProjectService;
	}

	/**
	 * 首页获得项目树
	 */
	public void ctrlTree() {
		writeJson(projectService.tree(project, false));
	}

	/**
	 * 跳转到项目管理页面
	 * 
	 * @return
	 */
	public String project() {
		return "project";
	}
	
	/**
	 * 获得项目treegrid
	 */
	public void treegrid() {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		//user.setCid(sessionInfo.getUserId());
		//project.setUserId(sessionInfo.getUserId());
		//project.setTuser(user.setCid(sessionInfo.getUserId()));
		user.setCid(sessionInfo.getUserId());
		project.setTuser(user);
		writeJson(projectService.treegrid(project));
	}

	/**
	 * 获取项目树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(projectService.tree(project, true));
	}

	/**
	 * 所有人都有权限的
	 */
	public void projectTreeRecursive() {

		writeJson(projectService.tree(project, true));
	}

	/**
	 * 编辑项目
	 */
	public void edit() {
		Json j = new Json();
		try {
			projectService.edit(project);
			j.setSuccess(true);
			j.setMsg("编辑成功!请手动刷新项目管理页面！");
			j.setObj(project.getCpid());
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
			projectService.add(project);
			j.setSuccess(true);
			j.setMsg("添加成功!请手动刷新任务管理页面！");
			j.setObj(project.getCpid());
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
			projectService.delete(project);
			j.setSuccess(true);
			j.setMsg("删除成功！请手动刷新项目管理页面！");
			j.setObj(project.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

	/**
	 * 获得用户下拉列表
	 */
	public void projectCombobox() {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String groupId = sessionInfo.getGroupId();
		logger.info(groupId);
		writeJson(projectService.combobox(groupId));
	}
	
	/**
	 * 更改状态
	 * wei
	 */
	public void changeStatus() {
		Json j = new Json();
		try {
			projectService.changeStatus(project);
			projectService.editProgress(project);	
			j.setSuccess(true);
			j.setMsg("更改状态成功!请手动刷新项目管理页面！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}
	
}
