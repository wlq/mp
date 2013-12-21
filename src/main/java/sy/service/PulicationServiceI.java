package sy.service;

import sy.model.Tpulication;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Pulication;

public interface PulicationServiceI extends BaseServiceI{

	/**
	 * 获得数据表格
	 * 
	 * @param pulication
	 * @return
	 */
	public DataGrid datagrid(Pulication pulication);
	/**
	 * 添加
	 * 
	 * @param pulication
	 */
	public void add(Pulication pulication);
	/**
	 * 修改
	 * 
	 * @param pulication
	 */
	public void update(Pulication pulication);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param pulication
	 * @return
	 */
	public Tpulication get(Pulication pulication);
	
	public void changeFlag(Pulication pulication);
}
