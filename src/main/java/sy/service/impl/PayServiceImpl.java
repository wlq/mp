package sy.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sy.comparator.PayComparator;
import sy.dao.BaseDaoI;
import sy.model.Tpay;
import sy.model.Tpay;
import sy.model.Tuser;
import sy.pageModel.Pay;
import sy.pageModel.Pay;
import sy.pageModel.TreeNode;
import sy.pageModel.User;
import sy.service.PayServiceI;

@Service("payService")
public class PayServiceImpl  extends BaseServiceImpl implements PayServiceI  {

	private static final Logger logger = Logger.getLogger(PayServiceImpl.class);

	private BaseDaoI<Tpay> payDao;

	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	public BaseDaoI<Tpay> getpayDao() {
		return payDao;
	}

	@Autowired
	public void setpayDao(BaseDaoI<Tpay> payDao) {
		this.payDao = payDao;
	}
	
	/**
	 * 统计当前项目下有多少子节点
	 */
	private Long countChildren(String pid) {
		return payDao.count("select count(*) from Tpay t where t.tpay.cid = ?", new Object[] { pid });
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pay> treegrid(Pay pay) {
		getUserInfo(pay.getTuser());
		List<Tpay> tpays;
		String userId = pay.getTuser().getCid();
		if(pay.getId() !=null){
			tpays = payDao.find("from Tpay t where t.tpay.cid = ? order by t.cseq", new Object[] { pay.getId() });
		}
		else if (pay != null && userId != null) {			
			tpays = payDao.find("from Tpay t where t.tuser.cid = ? order by t.cseq", new Object[] { userId });
		} else {
			tpays = payDao.find("from Tpay t where t.tpay is null order by t.cseq");
		}
		return gePaysFromTpays(tpays);
	}

	private void getUserInfo(User user) {			
			Tuser u = userDao.get(Tuser.class, user.getCid());
			if (u != null) {
				BeanUtils.copyProperties(u , user);
			}
	}
	
	private List<Pay> gePaysFromTpays(List<Tpay> Tpays) {
		List<Pay> pays = new ArrayList<Pay>();
		if (Tpays != null && Tpays.size() > 0) {
			for (Tpay t : Tpays) {
				Pay m = new Pay();
				BeanUtils.copyProperties(t, m,new String[] { "tuser"});
				if(t.getTuser() != null ){
				copyUserProperties(t,m);
				//Tuser tmp = new Tuser();
				m.setCuid(m.getTuser().getCid());
				m.setCuname(m.getTuser().getCname());
				}
				if (t.getTpay() != null) {
					m.setCpid(t.getTpay().getCid());
					m.setCpname(t.getTpay().getCname());
				}
				if (countChildren(t.getCid()) > 0) {
					m.setState("closed");
				}				
				pays.add(m);
			}
		}
		return pays;
	}

	private void copyUserProperties(Tpay t, Pay m) {
		User suser = new User();
		Tuser tuser = new Tuser();
		tuser = t.getTuser();
		BeanUtils.copyProperties(tuser, suser);
		m.setTuser(suser);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(Pay pay, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Tpay t where t.tpay is null order by t.cseq";
		if (pay != null && pay.getId() != null && !pay.getId().trim().equals("")) {
			hql = "from Tpay t where t.tpay.cid = ? order by t.cseq";
			param.add(pay.getId());
		}
		List<Tpay> l = payDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Tpay t : l) {
			tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Tpay t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getCid());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		node.setAttributes(attributes);
		if (t.getTpays() != null && t.getTpays().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Tpay> l = new ArrayList<Tpay>(t.getTpays());
				//Collections.sort(l, new PayComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Tpay r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
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
}
