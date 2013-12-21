package sy.service;

import sy.pageModel.DataGrid;
import sy.pageModel.Log;

/**
 * 用户Service
 * 
 * @author wei
 * 
 */
public interface LogServiceI extends BaseServiceI{

	/**
	 * 获得数据表格
	 * 
	 * @param log
	 * @return
	 */
	public DataGrid datagrid(Log log);
	
	/**
	 * 添加用户
	 * 
	 * @param log
	 */
	public void add(Log log);
}
