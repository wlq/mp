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

import sy.comparator.ProjectComparator;
import sy.dao.BaseDaoI;
import sy.model.Tproject;
import sy.model.Trole;
import sy.model.Tuser;
import sy.model.Tusertrole;
import sy.pageModel.Project;
import sy.pageModel.Role;
import sy.pageModel.TreeNode;
import sy.pageModel.User;
import sy.service.ProjectServiceI;
import sy.service.UserServiceI;
import sy.util.Encrypt;

/**
 * 菜单Service
 * 
 * @author 
 * 
 */
@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl implements ProjectServiceI {

	private static final Logger logger = Logger.getLogger(ProjectServiceImpl.class);

	private BaseDaoI<Tproject> projectDao;

	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	public BaseDaoI<Tproject> getProjectDao() {
		return projectDao;
	}

	@Autowired
	public void setProjectDao(BaseDaoI<Tproject> projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * 统计当前项目下有多少子节点
	 */
	private Long countChildren(String pid) {
		return projectDao.count("select count(*) from Tproject t where t.tproject.cid = ?", new Object[] { pid });
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Project> treegrid(Project project) {
		getUserInfo(project.getTuser());
		List<Tproject> tprojects;
		String userId = project.getTuser().getCid();
	
		if(project.getId() !=null){
			tprojects = projectDao.find("from Tproject t where t.tproject.cid = ? order by t.cseq", new Object[] { project.getId() });
		}
		else if (project != null && userId != null) {			
			tprojects = projectDao.find("from Tproject t where t.tuser.cid = ? order by t.cseq", new Object[] { userId });
		} else {
			tprojects = projectDao.find("from Tproject t where t.tproject is null order by t.cseq");
		}
		return geProjectsFromTprojects(tprojects);
	}

	private void getUserInfo(User user) {			
			Tuser u = userDao.get(Tuser.class, user.getCid());
			if (u != null) {
				BeanUtils.copyProperties(u , user);
			}
	}		

	private List<Project> geProjectsFromTprojects(List<Tproject> Tprojects) {
		List<Project> projects = new ArrayList<Project>();
		if (Tprojects != null && Tprojects.size() > 0) {
			for (Tproject t : Tprojects) {
				Project m = new Project();
				BeanUtils.copyProperties(t, m,new String[] { "tuser"});
				if(t.getTuser() != null ){
				copyUserProperties(t,m);
				//Tuser tmp = new Tuser();
				m.setCuid(m.getTuser().getCid());
				m.setCuname(m.getTuser().getCname());
				}
				if (t.getTproject() != null) {
					m.setCpid(t.getTproject().getCid());
					m.setCpname(t.getTproject().getCname());
				}
				if (countChildren(t.getCid()) > 0) {
					m.setState("closed");
				}
				if (t.getCiconcls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getCiconcls());
				}
				projects.add(m);
			}
		}
		return projects;
	}

	private void copyUserProperties(Tproject t, Project m) {
		User suser = new User();
		Tuser tuser = new Tuser();
		tuser = t.getTuser();
		BeanUtils.copyProperties(tuser, suser);
		m.setTuser(suser);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(Project project, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Tproject t where t.tproject is null order by t.cseq";
		if (project != null && project.getId() != null && !project.getId().trim().equals("")) {
			hql = "from Tproject t where t.tproject.cid = ? order by t.cseq";
			param.add(project.getId());
		}
		List<Tproject> l = projectDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Tproject t : l) {
			tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Tproject t, boolean recursive) {
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
		if (t.getTprojects() != null && t.getTprojects().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Tproject> l = new ArrayList<Tproject>(t.getTprojects());
				Collections.sort(l, new ProjectComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Tproject r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void edit(Project project) {
		if(project.getCuid() != null && project.getTuser()==null){			
				User m = new User();
				m.setCid(project.getCuid());
				getUserInfo(m);
				project.setTuser(m);			
		}
		Tproject t = projectDao.get(Tproject.class, project.getCid());
		//BeanUtils.copyProperties(project, t);
		BeanUtils.copyProperties(project, t,new String[] { "tuser"});
		if(project.getCstarttime()!=null){
			t.setCstarttime(project.getCstarttime());
		}
		if(project.getCendtime()!=null){
			t.setCendtime(project.getCendtime());
		}
		if(project.getTuser() != null ){
			User source = project.getTuser();
			Tuser target = new Tuser();
			BeanUtils.copyProperties(source,target);
			t.setTuser(target);
		}
		if (project.getIconCls() != null) {
			t.setCiconcls(project.getIconCls());
		}
		if(project.getCstatus() != null){
			t.setCstatus(project.getCstatus());
		}
		if (project.getCpid() != null && !project.getCpid().equals(project.getCid())) {
			Tproject pt = projectDao.get(Tproject.class, project.getCpid());
			if (pt != null) {
				if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
					Set<Tproject> tprojects = t.getTprojects();// 当前要修改的权限的所有下级权限
					if (tprojects != null && tprojects.size() > 0) {
						for (Tproject tproject : tprojects) {
							if (tproject != null) {
								tproject.setTproject(null);
							}
						}
					}
				}
				t.setTproject(pt);
			}

		}
		
		editStatus(project);
		editProgress(project);		
	   }
	
	
	//update cstatus字段
	private void editStatus(Project project)
	{
		String hql_1 = "update tproject c set c.cstatus = ? where c.cid = ?";
		projectDao.updatestatus(hql_1, project.getCstatus(), project.getCid());
		
	}
	
	//计算分子/分母的值
	public void editProgress(Project project)
	{
		String cpid = project.getCpid();
		
		String hql2 = 
				"update tproject c set c.cprogress=(select t1.fenzi/t2.fenmu from (SELECT count(*)  fenzi FROM tproject  a where a.cpid=? and a.cstatus=0) t1,(SELECT count(*) fenmu FROM tproject  b where b.cpid=?) t2) where c.cid=?";
		List<Object> paramProgress = new ArrayList<Object>();
	     System.out.println(hql2);
		try {
			project.getCpid();
			System.out.println(project.getId());
			System.out.println(project.getCpid());
			projectDao.updateProgress(hql2,project.getCpid());
			
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
	private boolean isDown(Tproject t, Tproject pt) {
		if (pt.getTproject() != null) {
			if (pt.getTproject().getCid().equals(t.getCid())) {
				return true;
			} else {
				return isDown(t, pt.getTproject());
			}
		}
		return false;
	}

	public void add(Project project) {
		Tproject t = new Tproject();
		BeanUtils.copyProperties(project, t);
		if (project.getIconCls() != null) {
			t.setCiconcls(project.getIconCls());
		}
		if (project.getCpid() != null && !project.getCpid().equals(project.getCid())) {
			t.setTproject(projectDao.get(Tproject.class, project.getCpid()));
		}
		projectDao.save(t);
	
	}

	public void delete(Project project) {
		del(project.getCid());
	}

	private void del(String cid) {
		Tproject t = projectDao.get(Tproject.class, cid);
		if (t != null) {
			Set<Tproject> projects = t.getTprojects();
			if (projects != null && !projects.isEmpty()) {
				// 说明当前项目下面还有子项目
				for (Tproject tproject : projects) {
					del(tproject.getCid());
				}
			}
			projectDao.delete(t);
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
	public void changeStatus(Project project) {
		// TODO Auto-generated method stub
		
		//Tproject t = projectDao.get(Tproject.class, project.getCid());		
		String  cstatus = project.getCstatus();
		String  cid = project.getCid();
		String hql = " ";
		
		///
		if(Integer.parseInt(cid) < 55){
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update tproject c set c.cstatus= 1 where c.cid=?";
			}else{
				hql = "update tproject c set c.cstatus= 0 where c.cid=?";
			}
		}else{
			if (Integer.parseInt(cstatus) == 0)
			{
				hql = "update tproject c set c.cstatus= 1 where c.cid=?";
				String hql2 = "update tproject c set c.cprogress= 0 where c.cid=?";
				projectDao.updateStatus(hql2,project.getCid());

			}else{
				hql = "update tproject c set c.cstatus= 0 where c.cid=?";
				String hql2 = "update tproject c set c.cprogress= 1 where c.cid=?";
				projectDao.updateStatus(hql2,project.getCid());
			}
		}
		
		 
	
		try {
			projectDao.updateStatus(hql,project.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	
}
