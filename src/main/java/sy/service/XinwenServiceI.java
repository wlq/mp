package sy.service;


import sy.model.Txinwen;
import sy.pageModel.DataGrid;
import sy.pageModel.Xinwen;

public interface XinwenServiceI extends BaseServiceI  {

	/**
	 * 获得数据表格
	 * 
	 * @param Xinwen
	 * @return
	 */
	public DataGrid datagrid(Xinwen xinwen);

	/**
	 * 添加
	 * 
	 * @param xinwen
	 */
	public void add(Xinwen xinwen);

	/**
	 * 修改
	 * 
	 * @param xinwen
	 */
	public void update(Xinwen xinwen);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param xinwen
	 * @return
	 */
	public Txinwen get(Xinwen xinwen);

	
}
