package sy.service;

import java.util.List;

import sy.pageModel.Pay;
import sy.pageModel.TreeNode;

public interface PayServiceI extends BaseServiceI {
	
	/**
	 * 获得项目treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<Pay> treegrid(Pay pay);

	/**
	 * 获取项目树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Pay pay, Boolean b);


}
