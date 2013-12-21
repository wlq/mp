package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.Tuser;
import sy.pageModel.Json;
import sy.pageModel.Traincontent;
import sy.pageModel.SessionInfo;
import sy.pageModel.User;
import sy.service.TraincontentServiceI;
import sy.util.ExceptionUtil;
import sy.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单ACTION
 * 
 * @author 
 * 
 */
@Action(value = "traincontentAction", results = { @Result(name = "traincontent", location = "/admin/traincontent.jsp") })
public class TraincontentAction extends BaseAction implements ModelDriven<Traincontent> {

	private static final Logger logger = Logger.getLogger(TraincontentAction.class);

	private TraincontentServiceI traincontentService;

	private Traincontent traincontent = new Traincontent();
	private User user = new User();

	public Traincontent getModel() {
		return traincontent;
	}

	public TraincontentServiceI gettraincontentService() {
		return traincontentService;
	}

	@Autowired
	public void settraincontentService(TraincontentServiceI traincontentService) {
		this.traincontentService = traincontentService;
	}

	/**
	 * 首页获得项目树
	 */
	public void ctrlTree() {
		writeJson(traincontentService.tree(traincontent, false));
	}

	/**
	 * 跳转到项目管理页面
	 * 
	 * @return
	 */
	public String traincontent() {
		return "traincontent";
	}
	
	/**
	 * 获得项目treegrid
	 */
	public void treegrid() {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		//user.setCid(sessionInfo.getUserId());
		//traincontent.setUserId(sessionInfo.getUserId());
		//traincontent.setTuser(user.setCid(sessionInfo.getUserId()));
		user.setCid(sessionInfo.getUserId());
		traincontent.setTuser(user);
		writeJson(traincontentService.treegrid(traincontent));
	}

	/**
	 * 获取项目树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(traincontentService.tree(traincontent, true));
	}

	/**
	 * 所有人都有权限的
	 */
	public void traincontentTreeRecursive() {

		writeJson(traincontentService.tree(traincontent, true));
	}

	/**
	 * 编辑项目
	 */
	public void edit() {
		Json j = new Json();
		try {
			traincontentService.edit(traincontent);
			j.setSuccess(true);
			j.setMsg("编辑成功!请手动刷新项目管理页面！");
			j.setObj(traincontent.getCpid());
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
			traincontentService.add(traincontent);
			j.setSuccess(true);
			j.setMsg("添加成功!请手动刷新项目管理页面！");
			j.setObj(traincontent.getCpid());
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
			traincontentService.delete(traincontent);
			j.setSuccess(true);
			j.setMsg("删除成功！请手动刷新项目管理页面！");
			j.setObj(traincontent.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

	/**
	 * 获得用户下拉列表
	 */
	public void traincontentCombobox() {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String groupId = sessionInfo.getGroupId();
		logger.info(groupId);
		writeJson(traincontentService.combobox(groupId));
	}
	
	/**
	 * 更改状态
	 * @author wei
	 */
	public void changeStatus() {
		Json j = new Json();
		try {
			traincontentService.changeStatus(traincontent);
			traincontentService.editProgress(traincontent);	
			j.setSuccess(true);
			j.setMsg("更改状态成功!请手动刷新页面！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}
	
}
