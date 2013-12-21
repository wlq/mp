package sy.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sy.dao.BaseDaoI;
import sy.model.Tauth;
import sy.model.Tnotice;
import sy.model.Trole;
import sy.model.Troletauth;
import sy.model.Tuser;
import sy.model.Tusertrole;
import sy.pageModel.Auth;
import sy.pageModel.DataGrid;
import sy.pageModel.User;
import sy.service.UserServiceI;
import sy.util.Encrypt;

/**
 * 用户Service
 * 
 * @author 
 * 
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserServiceI {

	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Trole> roleDao;
	private BaseDaoI<Tusertrole> userroleDao;
	private BaseDaoI<Tnotice> noticeDao;
	
	public BaseDaoI<Trole> getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(BaseDaoI<Trole> roleDao) {
		this.roleDao = roleDao;
	}

	public BaseDaoI<Tusertrole> getUserroleDao() {
		return userroleDao;
	}

	@Autowired
	public void setUserroleDao(BaseDaoI<Tusertrole> userroleDao) {
		this.userroleDao = userroleDao;
	}

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}
	
	public BaseDaoI<Tnotice> getNoticeDao() {
		return noticeDao;
	}

	@Autowired
	public void setNoticeDao(BaseDaoI<Tnotice> noticeDao) {
		this.noticeDao = noticeDao;
	}

	/**
	 * 用户登录
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public User login(User user) {
		Tuser u = userDao.get("from Tuser t where t.cname=? and t.cpwd=?", new Object[] { user.getCname(), Encrypt.e(user.getCpwd()) });		
		String cget = u.getCname();
		cget="'"+cget+"'";
		String hql ="select count(*) from Tnotice t where t.cget= "+cget;
		Integer n = Integer.parseInt(String.valueOf(noticeDao.count(hql)));
		u.setCno(n);
		if (u != null) {
			User t = new User();
			BeanUtils.copyProperties(u, t);
			return t;
		}
		return null;
	}	

	/**
	 * 获得用户datagrid
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(User user) {
		DataGrid j = new DataGrid();
		j.setRows(getUsersFromTusers(find(user)));
		j.setTotal(total(user));
		return j;
	}

	private List<User> getUsersFromTusers(List<Tuser> tusers) {
		List<User> users = new ArrayList<User>();
		if (tusers != null && tusers.size() > 0) {
			for (Tuser tu : tusers) {
				User u = new User();
				BeanUtils.copyProperties(tu, u);

				Set<Tusertrole> tusertroles = tu.getTusertroles();
				String roleIds = "";
				String roleNames = "";
				if (tusertroles != null && tusertroles.size() > 0) {
					for (Tusertrole tusertrole : tusertroles) {
						if (tusertrole.getTrole() != null) {
							roleIds += "," + tusertrole.getTrole().getCid();
							roleNames += "," + tusertrole.getTrole().getCname();
						}
					}
				}
				if (roleIds.equals("")) {
					u.setRoleIds("");
					u.setRoleNames("");
				} else {
					u.setRoleIds(roleIds.substring(1));
					u.setRoleNames(roleNames.substring(1));
				}

				users.add(u);
			}
		}
		return users;
	}
	
	
	/**
	 * author:onefish
	 * 3-12-2013
	 */
	public User userInfo(User user) {
		
		Tuser u = userDao.get(Tuser.class, user.getCid());
		if (u != null) {
			User t = new User();
			BeanUtils.copyProperties(u, t);
			return t;
		}
		return null;
	}

	private List<Tuser> find(User user) {
		String hql = "from Tuser t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);

		if (user.getSort() != null && user.getOrder() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return userDao.find(hql, values, user.getPage(), user.getRows());
	}

	private Long total(User user) {
		String hql = "select count(*) from Tuser t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);
		return userDao.count(hql, values);
	}

	private String addWhere(User user, String hql, List<Object> values) {
		if (user.getCname() != null && !user.getCname().trim().equals("")) {
			hql += " and t.cname like ? ";
			values.add("%%" + user.getCname().trim() + "%%");
		}
		if (user.getCcreatedatetimeStart() != null) {
			hql += " and t.ccreatedatetime>=? ";
			values.add(user.getCcreatedatetimeStart());
		}
		if (user.getCcreatedatetimeEnd() != null) {
			hql += " and t.ccreatedatetime<=? ";
			values.add(user.getCcreatedatetimeEnd());
		}
		if (user.getCmodifydatetimeStart() != null) {
			hql += " and t.cmodifydatetime>=? ";
			values.add(user.getCmodifydatetimeStart());
		}
		if (user.getCmodifydatetimeEnd() != null) {
			hql += " and t.cmodifydatetime<=? ";
			values.add(user.getCmodifydatetimeEnd());
		}
		return hql;
	}

	/**
	 * 获得用户下拉列表
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> loginCombobox(User user) {
		String q = "";
		if (user != null && user.getQ() != null) {
			q = user.getQ().trim();
		}
		return getUsersFromTusers(userDao.find("from Tuser t where t.cname like ?", new Object[] { "%%" + q.trim() + "%%" }, 1, 10));
	}

	/**
	 * 用户注册
	 */
	public void reg(User user) {
		Tuser u = new Tuser();
		u.setCid(UUID.randomUUID().toString());
		u.setCname(user.getCname());
		u.setCpwd(Encrypt.e(user.getCpwd()));
		u.setCcreatedatetime(new Date());
		userDao.save(u);
	}

	/**
	 * 添加用户
	 */
	public void add(User user) {
		Tuser u = new Tuser();
		u.setCid(user.getCid());
		u.setCname(user.getCname());
		u.setCpwd(Encrypt.e(user.getCpwd()));
		u.setCcreatedatetime(new Date());
		userDao.save(u);

		saveUserRole(user, u);
	}

	/**
	 * 保存用户和角色的关系
	 * 
	 * @param user
	 * @param u
	 */
	private void saveUserRole(User user, Tuser u) {
		if (user.getRoleIds() != null) {
			for (String id : user.getRoleIds().split(",")) {
				Tusertrole tusertrole = new Tusertrole();
				tusertrole.setCid(UUID.randomUUID().toString());
				tusertrole.setTrole(roleDao.get(Trole.class, id.trim()));
				tusertrole.setTuser(u);
				userroleDao.save(tusertrole);
			}
		}
	}

	/**
	 * 更新用户
	 */
	public void update(User user) {
		Tuser u = userDao.get(Tuser.class, user.getCid());
		BeanUtils.copyProperties(user, u, new String[] { "cid", "cpwd" });
		if (user.getCcreatedatetime() == null) {
			u.setCcreatedatetime(new Date());
		}
		if (user.getCmodifydatetime() == null) {
			u.setCmodifydatetime(new Date());
		}

		userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", new Object[] { u });
		saveUserRole(user, u);
	}

	/**
	 * 删除一个或多个用户
	 */
	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				if (!id.trim().equals("0")) {
					Tuser user = userDao.get(Tuser.class, id.trim());
					userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", new Object[] { user });
					userDao.delete(user);
				}
			}
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Auth> getAuths(User user) {
		List<Auth> auths = new ArrayList<Auth>();
		Tuser u = userDao.get(Tuser.class, user.getCid());
		if (u != null) {
			Set<Tusertrole> tusertroles = u.getTusertroles();
			if (tusertroles != null && tusertroles.size() > 0) {
				for (Tusertrole tusertrole : tusertroles) {
					Trole trole = tusertrole.getTrole();
					if (trole != null) {
						Set<Troletauth> troletauths = trole.getTroletauths();
						if (troletauths != null && troletauths.size() > 0) {
							for (Troletauth troletauth : troletauths) {
								Tauth tauth = troletauth.getTauth();
								if (tauth != null) {
									Auth a = new Auth();
									a.setCname(tauth.getCname());
									a.setCurl(tauth.getCurl());
									auths.add(a);
								}
							}
						}
					}
				}
			}
		}
		return auths;
	}

	public void modifyPwd(User user) {
		if (user != null && user.getIds() != null) {
			for (String id : user.getIds().split(",")) {
				Tuser u = userDao.get(Tuser.class, id.trim());
				u.setCpwd(Encrypt.e(user.getCpwd()));
				u.setCmodifydatetime(new Date());
			}
		}
	}

	public void modifyUserRole(User user) {
		if (user != null && user.getIds() != null) {
			for (String id : user.getIds().split(",")) {
				Tuser u = userDao.get(Tuser.class, id.trim());
				u.setCmodifydatetime(new Date());

				userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", new Object[] { u });
				saveUserRole(user, u);
			}
		}
	}

	public boolean modifySelfPwd(User user) {
		boolean b = false;
		if (user != null && user.getCid() != null && user.getOldPwd() != null && user.getCpwd() != null) {
			Tuser u = userDao.get(Tuser.class, user.getCid().trim());

			if (u != null && u.getCpwd().equals(Encrypt.e(user.getOldPwd()))) {// 如果找到了这个用户，并且原密码正确，那么就修改新密码
				u.setCpwd(Encrypt.e(user.getCpwd()));
				u.setCmodifydatetime(new Date());

				b = true;
			}
		}
		return b;
	}

	public void editUserInfo(User user) {
		if (user.getCpwd() != null && !user.getCpwd().trim().equals("")) {
			Tuser t = userDao.get(Tuser.class, user.getCid());
			t.setCpwd(Encrypt.e(user.getCpwd()));
		}
	}



}
