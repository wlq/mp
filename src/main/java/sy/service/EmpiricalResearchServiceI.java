package sy.service;

import sy.model.TempiricalResearch;
import sy.pageModel.DataGrid;
import sy.pageModel.Device;
import sy.pageModel.EmpiricalResearch;

public interface EmpiricalResearchServiceI extends BaseServiceI{
	/**
	 * 获得数据表格
	 * 
	 * @param empiricalResearch
	 * @return
	 */
	public DataGrid datagrid(EmpiricalResearch empiricalResearch);
	/**
	 * 添加
	 * 
	 * @param empiricalResearch
	 */
	public void add(EmpiricalResearch empiricalResearch);
	/**
	 * 修改
	 * 
	 * @param empiricalResearch
	 */
	public void update(EmpiricalResearch empiricalResearch);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param empiricalResearch
	 * @return
	 */
	public TempiricalResearch get(EmpiricalResearch empiricalResearch);
	
	public void changeFlag(EmpiricalResearch empiricalResearch);
}
