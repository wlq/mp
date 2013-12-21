package sy.service;

import java.util.List;

import sy.pageModel.Constraint;
import sy.pageModel.TreeNode;
import sy.pageModel.User;

public interface ConstraintServiceI extends BaseServiceI {

	/**
	 * 获得项目treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<Constraint> treegrid(Constraint constraint);

	/**
	 * 获取项目树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Constraint constraint, Boolean b);

	/**
	 * 编辑项目
	 * 
	 * @param constraint
	 */
	public void edit(Constraint constraint);

	/**
	 * 添加项目
	 * 
	 * @param constraint
	 */
	public void add(Constraint constraint);

	/**
	 * 删除项目
	 * 
	 * @param constraint
	 */
	public void delete(Constraint constraint);
	
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
	public void editProgress(Constraint constraint);
	
	/**
	 * 更改状态
	 * 
	 * @return
	 */
	public void changeStatus(Constraint constraint);

	

}
