package sy.service;

import sy.model.Tdevice;
import sy.pageModel.ApplicationNote;
import sy.pageModel.DataGrid;
import sy.pageModel.Device;

public interface DeviceServiceI extends BaseServiceI{

	/**
	 * 获得数据表格
	 * 
	 * @param device
	 * @return
	 */
	public DataGrid datagrid(Device device);
	/**
	 * 添加
	 * 
	 * @param device
	 */
	public void add(Device device);
	/**
	 * 修改
	 * 
	 * @param device
	 */
	public void update(Device device);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param device
	 * @return
	 */
	public Tdevice get(Device device);
	
	public void changeFlag(Device device);
}
