package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.pageModel.Json;
import sy.pageModel.Log;
import sy.service.LogServiceI;
import sy.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "logAction", results = { @Result(name = "log", location = "/admin/log.jsp") })
public class LogAction extends BaseAction implements ModelDriven<Log> {

	private static final Logger logger = Logger.getLogger(LogAction.class);

	private LogServiceI logService;

	private Log log = new Log();
	
	public Log getModel() {
		// TODO Auto-generated method stub
		return log;
	}
	

	public LogServiceI getlogService() {
		return logService;
	}

	@Autowired
	public void setlogService(LogServiceI logService) {
		this.logService = logService;
	}
	
	/**
	 * 跳转到log管理页面
	 * 
	 * @return
	 */
	public String log() {
		return "log";
	}
	
	/**
	 * 获得log数据表格
	 */
	public void datagrid() {		
		writeJson(logService.datagrid(log));
	}

	/**
	 * 添加一个log
	 */
	public void add() {
		Json j = new Json();
		try {
			logService.add(log);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg("添加失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}
}
