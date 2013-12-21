package sy.service;

import sy.model.Tresearch;
import sy.pageModel.DataGrid;
import sy.pageModel.Research;

public interface ResearchServiceI extends BaseServiceI{
	/**
	 * 获得数据表格
	 * 
	 * @param Research
	 * @return
	 */
	public DataGrid datagrid(Research research);
	/**
	 * 添加
	 * 
	 * @param Research
	 */
	public void add(Research research);
	/**
	 * 修改
	 * 
	 * @param Research
	 */
	public void update(Research research);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Research
	 * @return
	 */
	public Tresearch get(Research research);
	
	public void changeFlag(Research research);
}
