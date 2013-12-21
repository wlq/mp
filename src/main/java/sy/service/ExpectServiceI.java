package sy.service;

import java.util.List;

import sy.pageModel.Expect;
import sy.pageModel.TreeNode;
import sy.pageModel.User;

/**
 * 项目Service
 * 
 * @author 
 * 
 */
public interface ExpectServiceI extends BaseServiceI {

	/**
	 * 获得项目treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<Expect> treegrid(Expect expect);

	/**
	 * 获取项目树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Expect expect, Boolean b);

	/**
	 * 编辑项目
	 * 
	 * @param expect
	 */
	public void edit(Expect expect);

	/**
	 * 添加项目
	 * 
	 * @param expect
	 */
	public void add(Expect expect);

	/**
	 * 删除项目
	 * 
	 * @param expect
	 */
	public void delete(Expect expect);
	
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
	public void editProgress(Expect expect);
	
	/**
	 * 更改状态
	 * 
	 * @return
	 */
	public void changeStatus(Expect expect);

	

}
