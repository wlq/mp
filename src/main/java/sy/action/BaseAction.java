package sy.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import sy.util.ExceptionUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础ACTION,其他ACTION继承此ACTION来获得writeJson和ActionSupport的功能
 * 
 * @author 
 * 
 */
@ParentPackage("defaultPackage")
@Namespace("/")
public class BaseAction extends ActionSupport {

	private static final Logger logger = Logger.getLogger(BaseAction.class);

	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(Object object) {
		try {
			// SerializeConfig serializeConfig = new SerializeConfig();
			// serializeConfig.setAsmEnable(false);
			// String json = JSON.toJSONString(object);
			// String json = JSON.toJSONString(object, serializeConfig, SerializerFeature.PrettyFormat);
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd");
			// String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", SerializerFeature.PrettyFormat);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
		} catch (IOException e) {
			logger.debug(ExceptionUtil.getExceptionMessage(e));
		}
	}
}
