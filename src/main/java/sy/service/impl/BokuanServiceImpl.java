package sy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sy.dao.BaseDaoI;
import sy.model.Tbokuan;
import sy.model.Tkemu;
import sy.model.Tpay;
import sy.model.Tproject;
import sy.model.Tuser;
import sy.pageModel.DataGrid;
import sy.pageModel.Bokuan;
import sy.pageModel.Project;
import sy.pageModel.SessionInfo;
import sy.pageModel.User;
import sy.service.BokuanServiceI;
import sy.util.ResourceUtil;
@Service("bokuanService")
public class BokuanServiceImpl  extends BaseServiceImpl implements BokuanServiceI {
	private BaseDaoI<Tbokuan> bokuanDao;
	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Tpay> payDao;
	
	
	public BaseDaoI<Tbokuan> geTbokuanDao() {
		return bokuanDao;
	}

	@Autowired
	public void seTbokuanDao(BaseDaoI<Tbokuan> bokuanDao) {
		this.bokuanDao = bokuanDao;
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
	public DataGrid datagrid(Bokuan bokuan) {
		DataGrid j = new DataGrid();
		j.setRows(geTbokuansFromTbokuans(find(bokuan)));
		j.setTotal(total(bokuan));
		return j;
	}
	private List<Bokuan> geTbokuansFromTbokuans(List<Tbokuan> Tbokuans) {
		List<Bokuan> bokuans = new ArrayList<Bokuan>();
		if (Tbokuans != null && Tbokuans.size() > 0) {
			for (Tbokuan tb : Tbokuans) {
				Bokuan b = new Bokuan();
				BeanUtils.copyProperties(tb, b);
				bokuans.add(b);
			}
		}
		return bokuans;
	}

	private List<Tbokuan> find(Bokuan bokuan) {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String cprojectid  = sessionInfo.getLoginName();
		cprojectid = "'"+cprojectid +"'";
		String hql = "select new Tbokuan(t.cid,t.ctickets,t.cprojectid,t.cmoney,t.ccountTime,t.ccountId,t.cdatei) from Tbokuan t where cprojectid= "+cprojectid;

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(bokuan, hql, values);

		if (bokuan.getSort() != null && bokuan.getOrder() != null) {
			hql += " order by " + bokuan.getSort() + " " + bokuan.getOrder();
		}
		return bokuanDao.find(hql, values, bokuan.getPage(), bokuan.getRows());
	}

	private Long total(Bokuan bokuan) {
		String hql = "select count(*) from Tbokuan t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(bokuan, hql, values);
		return bokuanDao.count(hql, values);
	}

	private String addWhere(Bokuan bokuan, String hql, List<Object> values) {
		return hql;
	}

	public void add(Bokuan bokuan) {
		if (bokuan.getCid() == null || bokuan.getCid().trim().equals("")) {
			bokuan.setCid(UUID.randomUUID().toString());
		}
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String cprojectid  = sessionInfo.getLoginName();
		bokuan.setCprojectid(cprojectid);	//写入接收人
		Tbokuan t = new Tbokuan();
		BeanUtils.copyProperties(bokuan, t);
		bokuanDao.save(t);
	}

	public void update(Bokuan bokuan) {
		Tbokuan t = bokuanDao.get(Tbokuan.class, bokuan.getCid());
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String cprojectid  = sessionInfo.getLoginName();
		bokuan.setCprojectid(cprojectid);//写入接收人
		if (t != null) {			
			BeanUtils.copyProperties(bokuan, t, new String[] { "cid" });
		}		
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tbokuan t = bokuanDao.get(Tbokuan.class, id);
				if (t != null) {
					bokuanDao.delete(t);
					SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
					String username  = sessionInfo.getLoginName();
					String hql1="select new Tuser(t.cid) from Tuser t where cname=? ";//获取用户id		
					List<Tuser> t1 = userDao.find(hql1,new Object[] {username});
					String cfuid = " ";
					Tuser tu = t1.get(0);
					cfuid= tu.getCid();
				    
					String hql2="select sum(t.cmoney) as t from Tbokuan t where cprojectid=?";//获取拨款sum	
					List<Double> list = bokuanDao.finds(hql2, new Object[] {username});
					Double sum = list.get(0);			
					cfuid = "'" + cfuid +"'";
					
					//更新汇总表
					String hql3="update Tpay tpay set tpay.cmoney=" +sum+
							" where tpay.cfuid=" +cfuid+
							" and tpay.cid<55";
					String hql3_1 = "update Tpay tpay set tpay.cbalance= tpay.cmoney-tpay.ccost";
					payDao.executeHql(hql3);
					payDao.executeHql(hql3_1);	
				}
			}
		}
	}

	public Tbokuan get(Bokuan bokuan) {
		Tbokuan menu = bokuanDao.get(Tbokuan.class, bokuan.getCid());
		return menu;
	}

	public Tbokuan updatepay(Bokuan bokuan) {
		if (bokuan.getCid() == null || bokuan.getCid().trim().equals("")) {
			bokuan.setCid(UUID.randomUUID().toString());
		}
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String username  = sessionInfo.getLoginName();
		String hql1="select new Tuser(t.cid)  from Tuser t where cname=? ";//获取用户id		
		List<Tuser> t = userDao.find(hql1,new Object[] {username});
		String cfuid = " ";
		Tuser tu = t.get(0);
		cfuid= tu.getCid();		
		System.out.println("cfuid"+cfuid);
	    
		String hql2="select sum(t.cmoney) as t from Tbokuan t where cprojectid=?";//获取拨款sum	
		List<Double> list = bokuanDao.finds(hql2, new Object[] {username});
		Double sum = list.get(0);			
		cfuid = "'" + cfuid +"'";
		
		//更新汇总表
		String hql3="update Tpay tpay set tpay.cmoney=" +sum+
				" where tpay.cfuid=" +cfuid+
				" and tpay.cid<55";
		String hql3_1 = "update Tpay tpay set tpay.cbalance= tpay.cmoney-tpay.ccost";
		payDao.executeHql(hql3);
		payDao.executeHql(hql3_1);
		
		return null;
	}
	

}
