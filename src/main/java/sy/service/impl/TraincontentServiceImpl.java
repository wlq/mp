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
import sy.model.Ttraincontent;
import sy.model.Trole;
import sy.model.Tuser;
import sy.model.Tusertrole;
import sy.pageModel.Traincontent;
import sy.pageModel.Role;
import sy.pageModel.TreeNode;
import sy.pageModel.User;
import sy.service.TraincontentServiceI;
import sy.service.UserServiceI;
import sy.util.Encrypt;

/**
 * 菜单Service
 * 
 * @author 
 * 
 */
@Service("traincontentService")
public class TraincontentServiceImpl extends BaseServiceImpl implements TraincontentServiceI {

	private static final Logger logger = Logger.getLogger(TraincontentServiceImpl.class);

	private BaseDaoI<Ttraincontent> traincontentDao;

	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	public BaseDaoI<Ttraincontent> gettraincontentDao() {
		return traincontentDao;
	}

	@Autowired
	public void settraincontentDao(BaseDaoI<Ttraincontent> traincontentDao) {
		this.traincontentDao = traincontentDao;
	}

	/**
	 * 统计当前项目下有多少子节点
	 */
	private Long countChildren(String pid) {
		return traincontentDao.count("select count(*) from Ttraincontent t where t.ttraincontent.cid = ?", new Object[] { pid });
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Traincontent> treegrid(Traincontent traincontent) {
		getUserInfo(traincontent.getTuser());
		List<Ttraincontent> ttraincontents;
		String userId = traincontent.getTuser().getCid();
	
		if(traincontent.getId() !=null){
			ttraincontents = traincontentDao.find("from Ttraincontent t where t.ttraincontent.cid = ? order by t.cseq", new Object[] { traincontent.getId() });
		}
		else if (traincontent != null && userId != null) {			
			ttraincontents = traincontentDao.find("from Ttraincontent t where t.tuser.cid = ? order by t.cseq", new Object[] { userId });
		} else {
			ttraincontents = traincontentDao.find("from Ttraincontent t where t.ttraincontent is null order by t.cseq");
		}
		return getraincontentsFromTtraincontents(ttraincontents);
	}

	private void getUserInfo(User user) {			
			Tuser u = userDao.get(Tuser.class, user.getCid());
			if (u != null) {
				BeanUtils.copyProperties(u , user);
			}
	}		

	private List<Traincontent> getraincontentsFromTtraincontents(List<Ttraincontent> Ttraincontents) {
		List<Traincontent> traincontents = new ArrayList<Traincontent>();
		if (Ttraincontents != null && Ttraincontents.size() > 0) {
			for (Ttraincontent t : Ttraincontents) {
				Traincontent m = new Traincontent();
				BeanUtils.copyProperties(t, m,new String[] { "tuser"});
				if(t.getTuser() != null ){
				copyUserProperties(t,m);
				//Tuser tmp = new Tuser();
				m.setCuid(m.getTuser().getCid());
				m.setCuname(m.getTuser().getCname());
				}
				if (t.getTtraincontent() != null) {
					m.setCpid(t.getTtraincontent().getCid());
					m.setCpname(t.getTtraincontent().getCname());
				}
				if (countChildren(t.getCid()) > 0) {
					m.setState("closed");
				}
				if (t.getCiconcls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getCiconcls());
				}
				traincontents.add(m);
			}
		}
		return traincontents;
	}

	private void copyUserProperties(Ttraincontent t, Traincontent m) {
		User suser = new User();
		Tuser tuser = new Tuser();
		tuser = t.getTuser();
		BeanUtils.copyProperties(tuser, suser);
		m.setTuser(suser);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(Traincontent traincontent, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Ttraincontent t where t.ttraincontent is null order by t.cseq";
		if (traincontent != null && traincontent.getId() != null && !traincontent.getId().trim().equals("")) {
			hql = "from Ttraincontent t where t.ttraincontent.cid = ? order by t.cseq";
			param.add(traincontent.getId());
		}
		List<Ttraincontent> l = traincontentDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Ttraincontent t : l) {
			tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Ttraincontent t, boolean recursive) {
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
		if (t.getTtraincontents() != null && t.getTtraincontents().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Ttraincontent> l = new ArrayList<Ttraincontent>(t.getTtraincontents());
				//Collections.sort(l, new TraincontentComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Ttraincontent r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void edit(Traincontent traincontent) {
		if(traincontent.getCuid() != null && traincontent.getTuser()==null){			
				User m = new User();
				m.setCid(traincontent.getCuid());
				getUserInfo(m);
				traincontent.setTuser(m);			
		}
		Ttraincontent t = traincontentDao.get(Ttraincontent.class, traincontent.getCid());
		//BeanUtils.copyProperties(traincontent, t);
		BeanUtils.copyProperties(traincontent, t,new String[] { "tuser"});
		if(traincontent.getCstarttime()!=null){
			t.setCstarttime(traincontent.getCstarttime());
		}
		if(traincontent.getCendtime()!=null){
			t.setCendtime(traincontent.getCendtime());
		}
		if(traincontent.getTuser() != null ){
			User source = traincontent.getTuser();
			Tuser target = new Tuser();
			BeanUtils.copyProperties(source,target);
			t.setTuser(target);
		}
		if (traincontent.getIconCls() != null) {
			t.setCiconcls(traincontent.getIconCls());
		}
		if(traincontent.getCstatus() != null){
			t.setCstatus(traincontent.getCstatus());
		}
		if (traincontent.getCpid() != null && !traincontent.getCpid().equals(traincontent.getCid())) {
			Ttraincontent pt = traincontentDao.get(Ttraincontent.class, traincontent.getCpid());
			if (pt != null) {
				if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
					Set<Ttraincontent> ttraincontents = t.getTtraincontents();// 当前要修改的权限的所有下级权限
					if (ttraincontents != null && ttraincontents.size() > 0) {
						for (Ttraincontent ttraincontent : ttraincontents) {
							if (ttraincontent != null) {
								ttraincontent.setTtraincontent(null);
							}
						}
					}
				}
				t.setTtraincontent(pt);
			}

		}
		
		editStatus(traincontent);
		editProgress(traincontent);		
	   }
	
	
	//update cstatus字段
	private void editStatus(Traincontent traincontent)
	{
		String hql_1 = "update ttraincontent c set c.cstatus = ? where c.cid = ?";
		traincontentDao.updatestatus(hql_1, traincontent.getCstatus(), traincontent.getCid());
		
	}
	
	//计算分子/分母的值
	public void editProgress(Traincontent traincontent)
	{
		String cpid = traincontent.getCpid();
		
		String hql2 = 
				"update ttraincontent c set c.cprogress=(select t1.fenzi/t2.fenmu from (SELECT count(*)  fenzi FROM ttraincontent  a where a.cpid=? and a.cstatus=0) t1,(SELECT count(*) fenmu FROM ttraincontent  b where b.cpid=?) t2) where c.cid=?";
		List<Object> paramProgress = new ArrayList<Object>();
	     System.out.println(hql2);
		try {
			traincontent.getCpid();
			System.out.println(traincontent.getId());
			System.out.println(traincontent.getCpid());
			traincontentDao.updateProgress(hql2,traincontent.getCpid());
			
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
	private boolean isDown(Ttraincontent t, Ttraincontent pt) {
		if (pt.getTtraincontent() != null) {
			if (pt.getTtraincontent().getCid().equals(t.getCid())) {
				return true;
			} else {
				return isDown(t, pt.getTtraincontent());
			}
		}
		return false;
	}

	public void add(Traincontent traincontent) {
		Ttraincontent t = new Ttraincontent();
		BeanUtils.copyProperties(traincontent, t);
		if (traincontent.getIconCls() != null) {
			t.setCiconcls(traincontent.getIconCls());
		}
		if (traincontent.getCpid() != null && !traincontent.getCpid().equals(traincontent.getCid())) {
			t.setTtraincontent(traincontentDao.get(Ttraincontent.class, traincontent.getCpid()));
		}
		traincontentDao.save(t);
	
	}

	public void delete(Traincontent traincontent) {
		del(traincontent.getCid());
	}

	private void del(String cid) {
		Ttraincontent t = traincontentDao.get(Ttraincontent.class, cid);
		if (t != null) {
			Set<Ttraincontent> traincontents = t.getTtraincontents();
			if (traincontents != null && !traincontents.isEmpty()) {
				// 说明当前项目下面还有子项目
				for (Ttraincontent ttraincontent : traincontents) {
					del(ttraincontent.getCid());
				}
			}
			traincontentDao.delete(t);
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
	public void changeStatus(Traincontent traincontent) {

		//Ttraincontent t = traincontentDao.get(Ttraincontent.class, traincontent.getCid());		
		String  cstatus = traincontent.getCstatus();
		String  cid = traincontent.getCid();
		String hql = " ";
		
		///
		if(Integer.parseInt(cid) < 55){
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update ttraincontent c set c.cstatus= 1 where c.cid=?";
			}else{
				hql = "update ttraincontent c set c.cstatus= 0 where c.cid=?";
			}
		}else{
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update ttraincontent c set c.cstatus= 1 where c.cid=?";
				String hql2 = "update ttraincontent c set c.cprogress= 0 where c.cid=?";
				traincontentDao.updateStatus(hql2,traincontent.getCid());

			}else{
				hql = "update ttraincontent c set c.cstatus= 0 where c.cid=?";
				String hql2 = "update ttraincontent c set c.cprogress= 1 where c.cid=?";
				traincontentDao.updateStatus(hql2,traincontent.getCid());
			}
		}
		try {
			traincontentDao.updateStatus(hql,traincontent.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	
}
