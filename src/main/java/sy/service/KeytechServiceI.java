package sy.service;

import sy.model.Tkeytech;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Keytech;

public interface KeytechServiceI extends BaseServiceI{
	/**
	 * 获得数据表格
	 * 
	 * @param keytech
	 * @return
	 */
	public DataGrid datagrid(Keytech keytech);
	/**
	 * 添加
	 * 
	 * @param keytech
	 */
	public void add(Keytech keytech);
	/**
	 * 修改
	 * 
	 * @param keytech
	 */
	public void update(Keytech keytech);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param keytech
	 * @return
	 */
	public Tkeytech get(Keytech keytech);
	
	public void changeFlag(Keytech keytech);
}
