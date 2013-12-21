package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.pageModel.Json;
import sy.pageModel.Pay;
import sy.pageModel.SessionInfo;
import sy.pageModel.User;
import sy.service.PayServiceI;
import sy.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "payAction", results = { @Result(name = "pay", location = "/admin/pay.jsp") })
public class PayAction extends BaseAction implements ModelDriven<Pay> {

	private static final Logger logger = Logger.getLogger(PayAction.class);

	private PayServiceI payService;

	private Pay pay = new Pay();
	private User user = new User();

	public Pay getModel() {
		return pay;
	}

	public PayServiceI getPayService() {
		return payService;
	}

	@Autowired
	public void setPayService(PayServiceI PayService) {
		this.payService = PayService;
	}

	/**
	 * 首页获得项目树
	 */
	public void ctrlTree() {
		writeJson(payService.tree(pay, false));
	}

	/**
	 * 跳转到项目管理页面
	 * 
	 * @return
	 */
	public String pay() {
		return "pay";
	}
	
	/**
	 * 获得项目treegrid
	 */
	public void treegrid() {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		//user.setCid(sessionInfo.getUserId());
		//pay.setUserId(sessionInfo.getUserId());
		//pay.setTuser(user.setCid(sessionInfo.getUserId()));
		user.setCid(sessionInfo.getUserId());
		pay.setTuser(user);
		writeJson(payService.treegrid(pay));
	}

	/**
	 * 获取项目树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(payService.tree(pay, true));
	}

	/**
	 * 所有人都有权限的
	 */
	public void payTreeRecursive() {
		writeJson(payService.tree(pay, true));
	}
}
