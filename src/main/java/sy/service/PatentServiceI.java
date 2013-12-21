package sy.service;

import sy.model.Tpatent;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Patent;

public interface PatentServiceI extends BaseServiceI{

	/**
	 * 获得数据表格
	 * 
	 * @param patent
	 * @return
	 */
	public DataGrid datagrid(Patent patent);
	/**
	 * 添加
	 * 
	 * @param patent
	 */
	public void add(Patent patent);
	/**
	 * 修改
	 * 
	 * @param patent
	 */
	public void update(Patent patent);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param patent
	 * @return
	 */
	public Tpatent get(Patent patent);
	
	public void changeFlag(Patent patent);
}
