package sy.service;

import java.util.List;

import sy.pageModel.Traincontent;
import sy.pageModel.TreeNode;
import sy.pageModel.User;

public interface TraincontentServiceI extends BaseServiceI {

	/**
	 * 获得项目treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<Traincontent> treegrid(Traincontent traincontent);

	/**
	 * 获取项目树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Traincontent traincontent, Boolean b);

	/**
	 * 编辑项目
	 * 
	 * @param traincontent
	 */
	public void edit(Traincontent traincontent);

	/**
	 * 添加项目
	 * 
	 * @param traincontent
	 */
	public void add(Traincontent traincontent);

	/**
	 * 删除项目
	 * 
	 * @param traincontent
	 */
	public void delete(Traincontent traincontent);
	
	/**
	 * 获得用户groupid
	 * 
	 * @return
	 */
	public List<User> combobox(String groupId);
	
	/**
	 * 获得进度
	 * 
	 * @return
	 */
	public void editProgress(Traincontent traincontent);
	
	/**
	 * 更改状态
	 * 
	 * @return
	 */
	public void changeStatus(Traincontent traincontent);

	

}
