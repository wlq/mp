package sy.service;

import sy.model.Tsoft;
import sy.pageModel.Soft;
import sy.pageModel.DataGrid;

public interface SoftServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param soft
	 * @return
	 */
	public DataGrid datagrid(Soft soft);
	/**
	 * 添加
	 * 
	 * @param soft
	 */
	public void add(Soft soft);
	/**
	 * 修改
	 * 
	 * @param soft
	 */
	public void update(Soft soft);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param soft
	 * @return
	 */
	public Tsoft get(Soft soft);
	
	/**
	 * 获得
	 * 
	 * @param soft
	 * @return
	 */
	public void changeFlag(Soft soft);
	
}

