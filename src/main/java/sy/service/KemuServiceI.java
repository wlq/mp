package sy.service;

import sy.model.Tkemu;
import sy.pageModel.Kemu;
import sy.pageModel.DataGrid;

public interface KemuServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param Kemu
	 * @return
	 */
	public DataGrid datagrid(Kemu kemu);
	/**
	 * 添加
	 * 
	 * @param kemu
	 */
	public void add(Kemu kemu);
	/**
	 * 修改
	 * 
	 * @param kemu
	 */
	public void update(Kemu kemu);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param kemu
	 * @return
	 */
	public Tkemu get(Kemu kemu);
	/**
	 * 获得
	 * 
	 * @param 更新汇总表
	 * @return
	 */
	public Tkemu updatepay(Kemu kemu);
}
