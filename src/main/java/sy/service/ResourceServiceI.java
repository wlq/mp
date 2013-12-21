package sy.service;

import sy.model.Tresource;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Resource;

public interface ResourceServiceI extends BaseServiceI{

	/**
	 * 获得数据表格
	 * 
	 * @param resource
	 * @return
	 */
	public DataGrid datagrid(Resource resource);
	/**
	 * 添加
	 * 
	 * @param resource
	 */
	public void add(Resource resource);
	/**
	 * 修改
	 * 
	 * @param resource
	 */
	public void update(Resource resource);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param resource
	 * @return
	 */
	public Tresource get(Resource resource);
	
	public void changeFlag(Resource resource);
}

