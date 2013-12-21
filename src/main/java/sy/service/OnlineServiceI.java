package sy.service;

import sy.model.Tonline;
import sy.pageModel.DataGrid;
import sy.pageModel.Online;

/**
 * 在线用户Service
 * 
 * @author 
 * 
 */
public interface OnlineServiceI extends BaseServiceI {

	/**
	 * 更新或插入用户在线列表
	 * 
	 * @param online
	 */
	public void updateOnline(Tonline online);

	/**
	 * 删除用户在线列表
	 * 
	 * @param loginName
	 * @param ip
	 */
	public void deleteOnline(String loginName, String ip);

	/**
	 * 获得用户在线列表datagrid
	 * 
	 * @param online
	 * @return
	 */
	public DataGrid datagrid(Online online);

}
