package sy.service;

import sy.model.Tpaper;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Paper;

public interface PaperServiceI extends BaseServiceI{

	/**
	 * 获得数据表格
	 * 
	 * @param paper
	 * @return
	 */
	public DataGrid datagrid(Paper paper);
	/**
	 * 添加
	 * 
	 * @param paper
	 */
	public void add(Paper paper);
	/**
	 * 修改
	 * 
	 * @param paper
	 */
	public void update(Paper paper);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param paper
	 * @return
	 */
	public Tpaper get(Paper paper);
	
	public void changeFlag(Paper paper);
}
