package sy.service;

import sy.model.Ttrainlearner;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Trainlearner;

public interface TrainlearnerServiceI extends BaseServiceI{

	/**
	 * 获得数据表格
	 * 
	 * @param trainlearner
	 * @return
	 */
	public DataGrid datagrid(Trainlearner trainlearner);
	/**
	 * 添加
	 * 
	 * @param trainlearner
	 */
	public void add(Trainlearner trainlearner);
	/**
	 * 修改
	 * 
	 * @param trainlearner
	 */
	public void update(Trainlearner trainlearner);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param trainlearner
	 * @return
	 */
	public Ttrainlearner get(Trainlearner trainlearner);
	
	public void changeFlag(Trainlearner trainlearner);
}
