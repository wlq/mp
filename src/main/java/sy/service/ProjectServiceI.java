package sy.service;

import java.util.List;

import sy.pageModel.Project;
import sy.pageModel.TreeNode;
import sy.pageModel.User;

/**
 * 项目Service
 * 
 * @author 
 * 
 */
public interface ProjectServiceI extends BaseServiceI {

	/**
	 * 获得项目treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<Project> treegrid(Project project);

	/**
	 * 获取项目树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Project project, Boolean b);

	/**
	 * 编辑项目
	 * 
	 * @param project
	 */
	public void edit(Project project);

	/**
	 * 添加项目
	 * 
	 * @param project
	 */
	public void add(Project project);

	/**
	 * 删除项目
	 * 
	 * @param project
	 */
	public void delete(Project project);
	
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
	public void editProgress(Project project);
	
	/**
	 * 更改状态
	 * 
	 * @return
	 */
	public void changeStatus(Project project);

	

}
