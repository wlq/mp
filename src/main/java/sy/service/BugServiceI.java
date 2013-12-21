package sy.service;

import sy.model.Tbug;
import sy.pageModel.Bug;
import sy.pageModel.DataGrid;

/**
 * Bug Service
 * 
 * @author 
 * 
 */
public interface BugServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(Bug bug);

	/**
	 * 添加
	 * 
	 * @param bug
	 */
	public void add(Bug bug);

	/**
	 * 修改
	 * 
	 * @param bug
	 */
	public void update(Bug bug);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param bug
	 * @return
	 */
	public Tbug get(Bug bug);

}
