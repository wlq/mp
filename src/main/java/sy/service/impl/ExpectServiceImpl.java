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
import sy.model.Texpect;
import sy.model.Trole;
import sy.model.Tuser;
import sy.model.Tusertrole;
import sy.pageModel.Expect;
import sy.pageModel.Role;
import sy.pageModel.TreeNode;
import sy.pageModel.User;
import sy.service.ExpectServiceI;
import sy.service.UserServiceI;
import sy.util.Encrypt;

/**
 * 菜单Service
 * 
 * @author 
 * 
 */
@Service("expectService")
public class ExpectServiceImpl extends BaseServiceImpl implements ExpectServiceI {

	private static final Logger logger = Logger.getLogger(ExpectServiceImpl.class);

	private BaseDaoI<Texpect> expectDao;

	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	public BaseDaoI<Texpect> getExpectDao() {
		return expectDao;
	}

	@Autowired
	public void setExpectDao(BaseDaoI<Texpect> expectDao) {
		this.expectDao = expectDao;
	}

	/**
	 * 统计当前项目下有多少子节点
	 */
	private Long countChildren(String pid) {
		return expectDao.count("select count(*) from Texpect t where t.texpect.cid = ?", new Object[] { pid });
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Expect> treegrid(Expect expect) {
		getUserInfo(expect.getTuser());
		List<Texpect> texpects;
		String userId = expect.getTuser().getCid();
	
		if(expect.getId() !=null){
			texpects = expectDao.find("from Texpect t where t.texpect.cid = ? order by t.cseq", new Object[] { expect.getId() });
		}
		else if (expect != null && userId != null) {			
			texpects = expectDao.find("from Texpect t where t.tuser.cid = ? order by t.cseq", new Object[] { userId });
		} else {
			texpects = expectDao.find("from Texpect t where t.texpect is null order by t.cseq");
		}
		return geExpectsFromTexpects(texpects);
	}

	private void getUserInfo(User user) {			
			Tuser u = userDao.get(Tuser.class, user.getCid());
			if (u != null) {
				BeanUtils.copyProperties(u , user);
			}
	}		

	private List<Expect> geExpectsFromTexpects(List<Texpect> Texpects) {
		List<Expect> expects = new ArrayList<Expect>();
		if (Texpects != null && Texpects.size() > 0) {
			for (Texpect t : Texpects) {
				Expect m = new Expect();
				BeanUtils.copyProperties(t, m,new String[] { "tuser"});
				if(t.getTuser() != null ){
				copyUserProperties(t,m);
				//Tuser tmp = new Tuser();
				m.setCuid(m.getTuser().getCid());
				m.setCuname(m.getTuser().getCname());
				}
				if (t.getTexpect() != null) {
					m.setCpid(t.getTexpect().getCid());
					m.setCpname(t.getTexpect().getCname());
				}
				if (countChildren(t.getCid()) > 0) {
					m.setState("closed");
				}
				if (t.getCiconcls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getCiconcls());
				}
				expects.add(m);
			}
		}
		return expects;
	}

	private void copyUserProperties(Texpect t, Expect m) {
		User suser = new User();
		Tuser tuser = new Tuser();
		tuser = t.getTuser();
		BeanUtils.copyProperties(tuser, suser);
		m.setTuser(suser);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(Expect expect, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Texpect t where t.texpect is null order by t.cseq";
		if (expect != null && expect.getId() != null && !expect.getId().trim().equals("")) {
			hql = "from Texpect t where t.texpect.cid = ? order by t.cseq";
			param.add(expect.getId());
		}
		List<Texpect> l = expectDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Texpect t : l) {
			tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Texpect t, boolean recursive) {
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
		if (t.getTexpects() != null && t.getTexpects().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Texpect> l = new ArrayList<Texpect>(t.getTexpects());
				//Collections.sort(l, new ExpectComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Texpect r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void edit(Expect expect) {
		if(expect.getCuid() != null && expect.getTuser()==null){			
				User m = new User();
				m.setCid(expect.getCuid());
				getUserInfo(m);
				expect.setTuser(m);			
		}
		Texpect t = expectDao.get(Texpect.class, expect.getCid());
		//BeanUtils.copyProperties(expect, t);
		BeanUtils.copyProperties(expect, t,new String[] { "tuser"});
		if(expect.getCstarttime()!=null){
			t.setCstarttime(expect.getCstarttime());
		}
		if(expect.getCendtime()!=null){
			t.setCendtime(expect.getCendtime());
		}
		if(expect.getTuser() != null ){
			User source = expect.getTuser();
			Tuser target = new Tuser();
			BeanUtils.copyProperties(source,target);
			t.setTuser(target);
		}
		if (expect.getIconCls() != null) {
			t.setCiconcls(expect.getIconCls());
		}
		if(expect.getCstatus() != null){
			t.setCstatus(expect.getCstatus());
		}
		if (expect.getCpid() != null && !expect.getCpid().equals(expect.getCid())) {
			Texpect pt = expectDao.get(Texpect.class, expect.getCpid());
			if (pt != null) {
				if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
					Set<Texpect> texpects = t.getTexpects();// 当前要修改的权限的所有下级权限
					if (texpects != null && texpects.size() > 0) {
						for (Texpect texpect : texpects) {
							if (texpect != null) {
								texpect.setTexpect(null);
							}
						}
					}
				}
				t.setTexpect(pt);
			}

		}
		
		editStatus(expect);
		editProgress(expect);		
	   }
	
	
	//update cstatus字段
	private void editStatus(Expect expect)
	{
		String hql_1 = "update texpect c set c.cstatus = ? where c.cid = ?";
		expectDao.updatestatus(hql_1, expect.getCstatus(), expect.getCid());
		
	}
	
	//计算分子/分母的值
	public void editProgress(Expect expect)
	{
		String cpid = expect.getCpid();
		
		String hql2 = 
				"update texpect c set c.cprogress=(select t1.fenzi/t2.fenmu from (SELECT count(*)  fenzi FROM texpect  a where a.cpid=? and a.cstatus=0) t1,(SELECT count(*) fenmu FROM texpect  b where b.cpid=?) t2) where c.cid=?";
		List<Object> paramProgress = new ArrayList<Object>();
	     System.out.println(hql2);
		try {
			expect.getCpid();
			System.out.println(expect.getId());
			System.out.println(expect.getCpid());
			expectDao.updateProgress(hql2,expect.getCpid());
			
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
	private boolean isDown(Texpect t, Texpect pt) {
		if (pt.getTexpect() != null) {
			if (pt.getTexpect().getCid().equals(t.getCid())) {
				return true;
			} else {
				return isDown(t, pt.getTexpect());
			}
		}
		return false;
	}

	public void add(Expect expect) {
		Texpect t = new Texpect();
		BeanUtils.copyProperties(expect, t);
		if (expect.getIconCls() != null) {
			t.setCiconcls(expect.getIconCls());
		}
		if (expect.getCpid() != null && !expect.getCpid().equals(expect.getCid())) {
			t.setTexpect(expectDao.get(Texpect.class, expect.getCpid()));
		}
		expectDao.save(t);
	
	}

	public void delete(Expect expect) {
		del(expect.getCid());
	}

	private void del(String cid) {
		Texpect t = expectDao.get(Texpect.class, cid);
		if (t != null) {
			Set<Texpect> expects = t.getTexpects();
			if (expects != null && !expects.isEmpty()) {
				// 说明当前项目下面还有子项目
				for (Texpect texpect : expects) {
					del(texpect.getCid());
				}
			}
			expectDao.delete(t);
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
	public void changeStatus(Expect expect) {
		// TODO Auto-generated method stub
		
		//Texpect t = expectDao.get(Texpect.class, expect.getCid());		
		String  cstatus = expect.getCstatus();
		String  cid = expect.getCid();
		String hql = " ";
		
		///
		if(Integer.parseInt(cid) < 55){
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update texpect c set c.cstatus= 1 where c.cid=?";
			}else{
				hql = "update texpect c set c.cstatus= 0 where c.cid=?";
			}
		}else{
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update texpect c set c.cstatus= 1 where c.cid=?";
				String hql2 = "update texpect c set c.cprogress= 0 where c.cid=?";
				expectDao.updateStatus(hql2,expect.getCid());

			}else{
				hql = "update texpect c set c.cstatus= 0 where c.cid=?";
				String hql2 = "update texpect c set c.cprogress= 1 where c.cid=?";
				expectDao.updateStatus(hql2,expect.getCid());
			}
		}
		 
	
		try {
			expectDao.updateStatus(hql,expect.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	
}
