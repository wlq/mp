package sy.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sy.dao.BaseDaoI;
import sy.model.Tconstraint;
import sy.model.Trole;
import sy.model.Tuser;
import sy.model.Tusertrole;
import sy.pageModel.Constraint;
import sy.pageModel.Role;
import sy.pageModel.TreeNode;
import sy.pageModel.User;
import sy.service.ConstraintServiceI;
import sy.service.UserServiceI;
import sy.util.Encrypt;

/**
 * 菜单Service
 * 
 * @author 
 * 
 */
@Service("constraintService")
public class ConstraintServiceImpl extends BaseServiceImpl implements ConstraintServiceI {

	private static final Logger logger = Logger.getLogger(ConstraintServiceImpl.class);

	private BaseDaoI<Tconstraint> constraintDao;

	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	public BaseDaoI<Tconstraint> getconstraintDao() {
		return constraintDao;
	}

	@Autowired
	public void setconstraintDao(BaseDaoI<Tconstraint> constraintDao) {
		this.constraintDao = constraintDao;
	}

	/**
	 * 统计当前项目下有多少子节点
	 */
	private Long countChildren(String pid) {
		return constraintDao.count("select count(*) from Tconstraint t where t.tconstraint.cid = ?", new Object[] { pid });
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Constraint> treegrid(Constraint constraint) {
		getUserInfo(constraint.getTuser());
		List<Tconstraint> tconstraints;
		String userId = constraint.getTuser().getCid();
	
		if(constraint.getId() !=null){
			tconstraints = constraintDao.find("from Tconstraint t where t.tconstraint.cid = ? order by t.cseq", new Object[] { constraint.getId() });
		}
		else if (constraint != null && userId != null) {			
			tconstraints = constraintDao.find("from Tconstraint t where t.tuser.cid = ? order by t.cseq", new Object[] { userId });
		} else {
			tconstraints = constraintDao.find("from Tconstraint t where t.tconstraint is null order by t.cseq");
		}
		return geconstraintsFromTconstraints(tconstraints);
	}

	private void getUserInfo(User user) {			
			Tuser u = userDao.get(Tuser.class, user.getCid());
			if (u != null) {
				BeanUtils.copyProperties(u , user);
			}
	}		

	private List<Constraint> geconstraintsFromTconstraints(List<Tconstraint> Tconstraints) {
		List<Constraint> constraints = new ArrayList<Constraint>();
		if (Tconstraints != null && Tconstraints.size() > 0) {
			for (Tconstraint t : Tconstraints) {
				Constraint m = new Constraint();
				BeanUtils.copyProperties(t, m,new String[] { "tuser"});
				if(t.getTuser() != null ){
				copyUserProperties(t,m);
				//Tuser tmp = new Tuser();
				m.setCuid(m.getTuser().getCid());
				m.setCuname(m.getTuser().getCname());
				}
				if (t.getTconstraint() != null) {
					m.setCpid(t.getTconstraint().getCid());
					m.setCpname(t.getTconstraint().getCname());
				}
				if (countChildren(t.getCid()) > 0) {
					m.setState("closed");
				}
				if (t.getCiconcls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getCiconcls());
				}
				constraints.add(m);
			}
		}
		return constraints;
	}

	private void copyUserProperties(Tconstraint t, Constraint m) {
		User suser = new User();
		Tuser tuser = new Tuser();
		tuser = t.getTuser();
		BeanUtils.copyProperties(tuser, suser);
		m.setTuser(suser);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(Constraint constraint, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Tconstraint t where t.tconstraint is null order by t.cseq";
		if (constraint != null && constraint.getId() != null && !constraint.getId().trim().equals("")) {
			hql = "from Tconstraint t where t.tconstraint.cid = ? order by t.cseq";
			param.add(constraint.getId());
		}
		List<Tconstraint> l = constraintDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Tconstraint t : l) {
			tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Tconstraint t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getCid());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("url", t.getCurl());
		node.setAttributes(attributes);
		if (t.getCiconcls() != null) {
			node.setIconCls(t.getCiconcls());
		} else {
			node.setIconCls("");
		}
		if (t.getTconstraints() != null && t.getTconstraints().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Tconstraint> l = new ArrayList<Tconstraint>(t.getTconstraints());
				//Collections.sort(l, new ConstraintComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Tconstraint r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void edit(Constraint constraint) {
		if(constraint.getCuid() != null && constraint.getTuser()==null){			
				User m = new User();
				m.setCid(constraint.getCuid());
				getUserInfo(m);
				constraint.setTuser(m);			
		}
		Tconstraint t = constraintDao.get(Tconstraint.class, constraint.getCid());
		//BeanUtils.copyProperties(constraint, t);
		BeanUtils.copyProperties(constraint, t,new String[] { "tuser"});
		if(constraint.getCstarttime()!=null){
			t.setCstarttime(constraint.getCstarttime());
		}
		if(constraint.getCendtime()!=null){
			t.setCendtime(constraint.getCendtime());
		}
		if(constraint.getTuser() != null ){
			User source = constraint.getTuser();
			Tuser target = new Tuser();
			BeanUtils.copyProperties(source,target);
			t.setTuser(target);
		}
		if (constraint.getIconCls() != null) {
			t.setCiconcls(constraint.getIconCls());
		}
		if(constraint.getCstatus() != null){
			t.setCstatus(constraint.getCstatus());
		}
		if (constraint.getCpid() != null && !constraint.getCpid().equals(constraint.getCid())) {
			Tconstraint pt = constraintDao.get(Tconstraint.class, constraint.getCpid());
			if (pt != null) {
				if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
					Set<Tconstraint> tconstraints = t.getTconstraints();// 当前要修改的权限的所有下级权限
					if (tconstraints != null && tconstraints.size() > 0) {
						for (Tconstraint tconstraint : tconstraints) {
							if (tconstraint != null) {
								tconstraint.setTconstraint(null);
							}
						}
					}
				}
				t.setTconstraint(pt);
			}

		}
		
		editStatus(constraint);
		editProgress(constraint);		
	   }
	
	
	//update cstatus字段
	private void editStatus(Constraint constraint)
	{
		String hql_1 = "update tconstraint c set c.cstatus = ? where c.cid = ?";
		constraintDao.updatestatus(hql_1, constraint.getCstatus(), constraint.getCid());
		
	}
	
	//计算分子/分母的值
	public void editProgress(Constraint constraint)
	{
		String cpid = constraint.getCpid();
		
		String hql2 = 
				"update tconstraint c set c.cprogress=(select t1.fenzi/t2.fenmu from (SELECT count(*)  fenzi FROM tconstraint  a where a.cpid=? and a.cstatus=0) t1,(SELECT count(*) fenmu FROM tconstraint  b where b.cpid=?) t2) where c.cid=?";
		List<Object> paramProgress = new ArrayList<Object>();
	     System.out.println(hql2);
		try {
			constraint.getCpid();
			System.out.println(constraint.getId());
			System.out.println(constraint.getCpid());
			constraintDao.updateProgress(hql2,constraint.getCpid());
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(Tconstraint t, Tconstraint pt) {
		if (pt.getTconstraint() != null) {
			if (pt.getTconstraint().getCid().equals(t.getCid())) {
				return true;
			} else {
				return isDown(t, pt.getTconstraint());
			}
		}
		return false;
	}

	public void add(Constraint constraint) {
		Tconstraint t = new Tconstraint();
		BeanUtils.copyProperties(constraint, t);
		if (constraint.getIconCls() != null) {
			t.setCiconcls(constraint.getIconCls());
		}
		if (constraint.getCpid() != null && !constraint.getCpid().equals(constraint.getCid())) {
			t.setTconstraint(constraintDao.get(Tconstraint.class, constraint.getCpid()));
		}
		constraintDao.save(t);
	
	}

	public void delete(Constraint constraint) {
		del(constraint.getCid());
	}

	private void del(String cid) {
		Tconstraint t = constraintDao.get(Tconstraint.class, cid);
		if (t != null) {
			Set<Tconstraint> constraints = t.getTconstraints();
			if (constraints != null && !constraints.isEmpty()) {
				// 说明当前项目下面还有子项目
				for (Tconstraint tconstraint : constraints) {
					del(tconstraint.getCid());
				}
			}
			constraintDao.delete(t);
		}
	}

	private List<User> getUsersFromTusers(List<Tuser> tusers) {
		List<User> users = new ArrayList<User>();
		if (tusers != null && tusers.size() > 0) {
			for (Tuser tu : tusers) {
				User u = new User();
				BeanUtils.copyProperties(tu, u);
				users.add(u);
			}
		}
		return users;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> combobox(String groupId) {
		return getUsersFromTusers(findAllTuser(groupId));
	}

	private List<Tuser> findAllTuser(String groupId) {
		String hql = "from Tuser t where t.cgroupid=?";
		return userDao.find(hql,new Object[] { groupId });
	}

	//更改状态
	public void changeStatus(Constraint constraint) {

		//Tconstraint t = constraintDao.get(Tconstraint.class, constraint.getCid());		
		String  cstatus = constraint.getCstatus();
		String  cid = constraint.getCid();
		String hql = " ";
		
		///
		if(Integer.parseInt(cid) < 55){
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update tconstraint c set c.cstatus= 1 where c.cid=?";
			}else{
				hql = "update tconstraint c set c.cstatus= 0 where c.cid=?";
			}
		}else{
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update tconstraint c set c.cstatus= 1 where c.cid=?";
				String hql2 = "update tconstraint c set c.cprogress= 0 where c.cid=?";
				constraintDao.updateStatus(hql2,constraint.getCid());

			}else{
				hql = "update tconstraint c set c.cstatus= 0 where c.cid=?";
				String hql2 = "update tconstraint c set c.cprogress= 1 where c.cid=?";
				constraintDao.updateStatus(hql2,constraint.getCid());
			}
		}
		try {
			constraintDao.updateStatus(hql,constraint.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	
}
