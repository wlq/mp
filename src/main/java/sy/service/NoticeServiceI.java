package sy.service;

import sy.model.Tnotice;
import sy.pageModel.DataGrid;
import sy.pageModel.Notice;

public interface NoticeServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param notice
	 * @return
	 */
	public DataGrid datagrid(Notice notice);
	/**
	 * 添加
	 * 
	 * @param notice
	 */
	public void add(Notice notice);
	/**
	 * 修改
	 * 
	 * @param notice
	 */
	public void update(Notice notice);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param notice
	 * @return
	 */
	public Tnotice get(Notice notice);
}
