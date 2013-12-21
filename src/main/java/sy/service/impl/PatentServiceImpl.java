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
import sy.model.Tpatent;
import sy.model.Tpatent;
import sy.model.Tproject;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Patent;
import sy.pageModel.SessionInfo;
import sy.service.PatentServiceI;
import sy.util.ResourceUtil;

@Service("patentService")
public class PatentServiceImpl extends BaseServiceImpl implements PatentServiceI{

	private BaseDaoI<Tpatent> patentDao;
    
	public BaseDaoI<Tpatent> getpatentDao() {
		return patentDao;
	}

	@Autowired
	public void setpatentDao(BaseDaoI<Tpatent> patentDao) {
		this.patentDao = patentDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Patent patent) {
		DataGrid j = new DataGrid();
		j.setRows(getpatentsFromTpatents(find(patent)));
		j.setTotal(total(patent));
		return j;
	}
	private List<Patent> getpatentsFromTpatents(List<Tpatent> tpatents) {
		List<Patent> patents = new ArrayList<Patent>();
		if (tpatents != null && tpatents.size() > 0) {
			for (Tpatent tb : tpatents) {
				Patent b = new Patent();
				BeanUtils.copyProperties(tb, b);
				patents.add(b);
			}
		}
		return patents;
	}

	private List<Tpatent> find(Patent patent) {
		String hql = "select new Tpatent( t.cid, t.ccountry, t.cname, t.cnumber, t.cclassify, t.cinvent, t.cpatentee,t.csummary, t.ctypeman,t.ctypetime, t.cflag) from Tpatent t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(patent, hql, values);

		if (patent.getSort() != null && patent.getOrder() != null) {
			hql += " order by " + patent.getSort() + " " + patent.getOrder();
		}
		return patentDao.find(hql, values, patent.getPage(), patent.getRows());
	}

	private Long total(Patent patent) {
		String hql = "select count(*) from Tpatent t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(patent, hql, values);
		return patentDao.count(hql, values);
	}
/*
 * 过滤
 * 
 */
	private String addWhere(Patent patent, String hql, List<Object> values) {
		if (patent.getCname() != null && !patent.getCname().trim().equals("")) {
			hql += " and t.cname like ? ";
			values.add("%%" + patent.getCname().trim() + "%%");
		}
		if (patent.getCinvent() != null && !patent.getCinvent().trim().equals("")) {
			hql += " and t.cinvent like ? ";
			values.add("%%" + patent.getCinvent().trim() + "%%");
		}		
		return hql;
	}

	public void add(Patent patent) {
		if (patent.getCid() == null || patent.getCid().trim().equals("")) {
			patent.setCid(UUID.randomUUID().toString());
		}
		patent.setCflag("1");
		Tpatent t = new Tpatent();	
		BeanUtils.copyProperties(patent, t);
		patentDao.save(t);
	}

	public void update(Patent patent) {
		Tpatent t = patentDao.get(Tpatent.class, patent.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(patent, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tpatent t = patentDao.get(Tpatent.class, id);
				if (t != null) {
					patentDao.delete(t);
				}
			}
		}
	}

	public Tpatent get(Patent patent) {
		Tpatent menu = patentDao.get(Tpatent.class, patent.getCid());
		return menu;
	}
	
	public void changeFlag(Patent patent) {
		// TODO Auto-generated method stub
		Tpatent t = patentDao.get(Tpatent.class, patent.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tpatent c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tpatent c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			patentDao.updateStatus(hql,patent.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
