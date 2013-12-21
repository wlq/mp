package sy.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sy.dao.BaseDaoI;
import sy.model.Tkemu;
import sy.model.Tmenu;
import sy.model.Tpay;
import sy.model.Tproject;
import sy.model.Tuser;
import sy.pageModel.DataGrid;
import sy.pageModel.Kemu;
import sy.pageModel.SessionInfo;
import sy.service.KemuServiceI;
import sy.util.ResourceUtil;

@Service("kemuService")
public class KemuServiceImpl extends BaseServiceImpl implements KemuServiceI {

	private BaseDaoI<Tkemu> kemuDao;
	private BaseDaoI<Tproject> projectDao;
	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Tpay> payDao;

	public BaseDaoI<Tkemu> getKemuDao() {
		return kemuDao;
	}

	public BaseDaoI<Tproject> getProjectDao() {
		return projectDao;
	}

	@Autowired
	public void setkemuDao(BaseDaoI<Tkemu> kemuDao) {
		this.kemuDao = kemuDao;
	}

	@Autowired
	public void setProjectDao(BaseDaoI<Tproject> projectDao) {
		this.projectDao = projectDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setPayDao(BaseDaoI<Tpay> payDao) {
		this.payDao = payDao;
	}

	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Kemu kemu) {
		DataGrid j = new DataGrid();
		j.setRows(getKemusFromTkemus(find(kemu)));
		j.setTotal(total(kemu));
		////跳转
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo.getCourse()!=null){
			sessionInfo.setCourse(null);
		}
		return j;
	}
	private List<Kemu> getKemusFromTkemus(List<Tkemu> tkemus) {
		List<Kemu> kemus = new ArrayList<Kemu>();
		if (tkemus != null && tkemus.size() > 0) {
			for (Tkemu tb : tkemus) {
				Kemu b = new Kemu();
				BeanUtils.copyProperties(tb, b);
				kemus.add(b);
			}
		}
		return kemus;
	}

	private List<Tkemu> find(Kemu kemu) {
		
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String cprojectid  = sessionInfo.getLoginName();
		cprojectid = "'"+cprojectid +"'";
		/////跳转过滤 
		String hql=" ";
		if(sessionInfo.getCourse()!=null){
			hql = "select new Tkemu(	t.cid,t.cname,t.ccountId,t.ccountTime,t.ccourse,t.cmoney,t.ctickets,t.cprojectid) from Tkemu t where cprojectid= "+cprojectid+" and t.ccourse= "+"'"+sessionInfo.getCourse()+"'";
		}
		else{
			hql = "select new Tkemu(	t.cid,t.cname,t.ccountId,t.ccountTime,t.ccourse,t.cmoney,t.ctickets,t.cprojectid) from Tkemu t where cprojectid= "+cprojectid;			
		}
		//String hql = "select new Tkemu(	t.cid,t.cname,t.ccountId,t.ccountTime,t.ccourse,t.cmoney,t.ctickets,t.cprojectid) from Tkemu t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(kemu, hql, values);

		if (kemu.getSort() != null && kemu.getOrder() != null) {
			hql += " order by " + kemu.getSort() + " " + kemu.getOrder();
		}
		return kemuDao.find(hql, values, kemu.getPage(), kemu.getRows());
	}

	private Long total(Kemu kemu) {
		String hql = "select count(*) from Tkemu t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(kemu, hql, values);
		return kemuDao.count(hql, values);
	}

	private String addWhere(Kemu kemu, String hql, List<Object> values) {
		if (kemu.getCprojectid() != null && !kemu.getCprojectid().trim().equals("")) {
			hql += " and t.cprojectid like ? ";
			values.add("%%" + kemu.getCprojectid().trim() + "%%");
		}
		if (kemu.getCcourse() != null && !kemu.getCcourse().trim().equals("")) {
			hql += " and t.ccourse like ? ";
			values.add("%%" + kemu.getCcourse().trim() + "%%");
		}
		if (kemu.getCcountTimeStart() != null) {
			hql += " and t.ccountTime>=? ";
			values.add(kemu.getCcountTimeStart());
		}
		//System.out.println(kemu.getCcountTimeStart());
		if (kemu.getCcountTimeEnd() != null) {
			hql += " and t.ccountTime<=? ";
			values.add(kemu.getCcountTimeEnd());
		}
		return hql;
	}

	public void add(Kemu kemu) {
		if (kemu.getCid() == null || kemu.getCid().trim().equals("")) {
			kemu.setCid(UUID.randomUUID().toString());
		}
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String cprojectid  = sessionInfo.getLoginName();
		kemu.setCprojectid(cprojectid);			
		Tkemu t = new Tkemu();
		BeanUtils.copyProperties(kemu, t);
		kemuDao.save(t);
	}

	public void update(Kemu kemu) {
		Tkemu t = kemuDao.get(Tkemu.class, kemu.getCid());
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String username  = sessionInfo.getLoginName();
		kemu.setCprojectid(username);
		if (t != null) {			
			BeanUtils.copyProperties(kemu, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tkemu t = kemuDao.get(Tkemu.class, id);
				if (t != null) {
					String ccourse=t.getCcourse();
					kemuDao.delete(t);
					SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
					String username  = sessionInfo.getLoginName();				
					String hql1="select new Tuser(t.cid) from Tuser t where cname=? ";//获取用户id		
					List<Tuser> ty = userDao.find(hql1,new Object[] {username});
					String cfuid = " ";
					Tuser tu = ty.get(0);
					cfuid= tu.getCid();
				    
					String hql2="select sum(t.cmoney) as t from Tkemu t where cprojectid=? and ccourse=?";//获取sum	
					List<Double> list = kemuDao.finds(hql2, new Object[] {username,ccourse});
					Double sum = list.get(0);			
					cfuid = "'" + cfuid +"'";
					ccourse = "'" + ccourse +"'";
					//更新科目支出总额
					String hql3="update Tpay tpay set tpay.ccost=" +sum+
							" where tpay.cfuid=" +cfuid+
							" and tpay.cname=" +ccourse;
					
					payDao.executeHql(hql3);
					/*String hql3="update Tpay tpay set tpay.ccost=?  where tpay.cfuid= ? and tpay.cname=?";
					payDao.executeHql(hql3,new Object[] {sum,cfuid,ccourse});*/
					//获取上级节点编号
					String hql4="select new Tpay(t.cfpid) from Tpay t where cname=" +ccourse+
							" and cfuid= "+cfuid;
					List<Tpay> tpl = payDao.find(hql4);
					String cfpid = " ";
					Tpay tp = tpl.get(0);
					cfpid= tp.getCfpid();
					while (cfpid !=null){
						String hql5="select sum(t.ccost) as t from Tpay t where  t.cfpid=?";	
						List<Double> list1 = kemuDao.finds(hql5, new Object[] {cfpid});
						Double sum1 = list1.get(0);//获取sum
						cfpid = "'" + cfpid +"'";
						String hql="update Tpay tpay set tpay.ccost=" +sum1 +
								"  where tpay.cid= "+ cfpid;			
						payDao.executeHql(hql);//更新上级支出总额
						/*String hql_1 = "update Tpay tpay set tpay.cbalance= tpay.cmoney-tpay.ccost";
						payDao.executeHql(hql_1);*/
						String hql6="select new Tpay(t.cfpid) from Tpay t where cid=" +cfpid;
						List<Tpay> tpl1 = payDao.find(hql6);
						Tpay tp1 = tpl1.get(0);
						cfpid= tp1.getCfpid();//获取上级节点id
					}
					String hql_1 = "update Tpay tpay set tpay.cbalance= tpay.cmoney-tpay.ccost";
					payDao.executeHql(hql_1);
				}
			}			
		}
	}

	public Tkemu get(Kemu kemu) {
		Tkemu menu = kemuDao.get(Tkemu.class, kemu.getCid());
		return menu;
	}

	public Tkemu updatepay(Kemu kemu) {
		if (kemu.getCid() == null || kemu.getCid().trim().equals("")) {
			kemu.setCid(UUID.randomUUID().toString());
		}
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String username  = sessionInfo.getLoginName();
		String ccourse=kemu.getCcourse();
		String hql1="select new Tuser(t.cid) from Tuser t where cname=? ";//获取用户id		
		List<Tuser> t = userDao.find(hql1,new Object[] {username});
		String cfuid = " ";
		Tuser tu = t.get(0);
		cfuid= tu.getCid();	
	    
		String hql2="select sum(t.cmoney) as t from Tkemu t where cprojectid=? and ccourse=?";//获取sum	
		List<Double> list = kemuDao.finds(hql2, new Object[] {username,ccourse});
		Double sum = list.get(0);			
		cfuid = "'" + cfuid +"'";
		ccourse = "'" + ccourse +"'";
		//更新科目支出总额
		String hql3="update Tpay tpay set tpay.ccost=" +sum+
				" where tpay.cfuid=" +cfuid+
				" and tpay.cname=" +ccourse;
		//String hql3_1 = "update Tpay tpay set tpay.cbalance= tpay.cmoney-tpay.ccost";
		payDao.executeHql(hql3);
		//payDao.executeHql(hql3_1);
		/*String hql3="update Tpay tpay set tpay.ccost=?  where tpay.cfuid= ? and tpay.cname=?";
		payDao.executeHql(hql3,new Object[] {sum,cfuid,ccourse});*/
		//获取上级节点编号
		String hql4="select new Tpay(t.cfpid) from Tpay t where cname=" +ccourse+
				" and cfuid= "+cfuid;
		List<Tpay> tpl = payDao.find(hql4);
		String cfpid = " ";
		Tpay tp = tpl.get(0);
		cfpid= tp.getCfpid();
		while (cfpid !=null){
			String hql5="select sum(t.ccost) as t from Tpay t where  t.cfpid=?";	
			List<Double> list1 = kemuDao.finds(hql5, new Object[] {cfpid});
			Double sum1 = list1.get(0);//获取sum
			cfpid = "'" + cfpid +"'";
			String hql="update Tpay tpay set tpay.ccost=" +sum1 +
					"  where tpay.cid= "+ cfpid;			
			payDao.executeHql(hql);//更新上级支出总额			
			String hql6="select new Tpay(t.cfpid) from Tpay t where cid=" +cfpid;
			List<Tpay> tpl1 = payDao.find(hql6);
			Tpay tp1 = tpl1.get(0);
			cfpid= tp1.getCfpid();//获取上级节点id
		}
		String hql_1 = "update Tpay tpay set tpay.cbalance= tpay.cmoney-tpay.ccost";
		payDao.executeHql(hql_1);
		return null;
	}	
}
