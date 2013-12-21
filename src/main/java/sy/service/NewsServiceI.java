package sy.service;

import sy.model.Tnews;
import sy.pageModel.News;
import sy.pageModel.DataGrid;

public interface NewsServiceI extends BaseServiceI  {

	/**
	 * 获得数据表格
	 * 
	 * @param News
	 * @return
	 */
	public DataGrid datagrid(News news);

	/**
	 * 添加
	 * 
	 * @param news
	 */
	public void add(News news);

	/**
	 * 修改
	 * 
	 * @param news
	 */
	public void update(News news);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param news
	 * @return
	 */
	public Tnews get(News news);

	
}
