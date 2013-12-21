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
import sy.model.TempiricalResearch;
import sy.model.Tproject;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.SessionInfo;
import sy.service.EmpiricalResearchServiceI;
import sy.util.ResourceUtil;

@Service("empiricalResearchService")
public class EmpiricalResearchServiceImpl extends BaseServiceImpl implements EmpiricalResearchServiceI{

	private BaseDaoI<TempiricalResearch> empiricalResearchDao;
    
	public BaseDaoI<TempiricalResearch> getempiricalResearchDao() {
		return empiricalResearchDao;
	}

	@Autowired
	public void setempiricalResearchDao(BaseDaoI<TempiricalResearch> empiricalResearchDao) {
		this.empiricalResearchDao = empiricalResearchDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(EmpiricalResearch empiricalResearch) {
		DataGrid j = new DataGrid();
		j.setRows(getempiricalResearchsFromTempiricalResearchs(find(empiricalResearch)));
		j.setTotal(total(empiricalResearch));
		return j;
	}
	private List<EmpiricalResearch> getempiricalResearchsFromTempiricalResearchs(List<TempiricalResearch> tempiricalResearchs) {
		List<EmpiricalResearch> empiricalResearchs = new ArrayList<EmpiricalResearch>();
		if (tempiricalResearchs != null && tempiricalResearchs.size() > 0) {
			for (TempiricalResearch tb : tempiricalResearchs) {
				EmpiricalResearch b = new EmpiricalResearch();
				BeanUtils.copyProperties(tb, b);
				empiricalResearchs.add(b);
			}
		}
		return empiricalResearchs;
	}

	private List<TempiricalResearch> find(EmpiricalResearch empiricalResearch) {
		String hql = "select new TempiricalResearch( t.cid, t.cclassify, t.cname,t.clanguage,t.cckeyword,t.cekeyword,t.cinformation, t.cnote, t.cstorage, t.cyear,t.cunit, t.ccontactid, t.ctypeman, t.ctypetime, t.cflag) from TempiricalResearch t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(empiricalResearch, hql, values);

		if (empiricalResearch.getSort() != null && empiricalResearch.getOrder() != null) {
			hql += " order by " + empiricalResearch.getSort() + " " + empiricalResearch.getOrder();
		}
		return empiricalResearchDao.find(hql, values, empiricalResearch.getPage(), empiricalResearch.getRows());
	}

	private Long total(EmpiricalResearch empiricalResearch) {
		String hql = "select count(*) from TempiricalResearch t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(empiricalResearch, hql, values);
		return empiricalResearchDao.count(hql, values);
	}

	private String addWhere(EmpiricalResearch empiricalResearch, String hql, List<Object> values) {
		return hql;
	}

	public void add(EmpiricalResearch empiricalResearch) {
		if (empiricalResearch.getCid() == null || empiricalResearch.getCid().trim().equals("")) {
			empiricalResearch.setCid(UUID.randomUUID().toString());
		}
		empiricalResearch.setCflag("1");
		TempiricalResearch t = new TempiricalResearch();	
		BeanUtils.copyProperties(empiricalResearch, t);
		empiricalResearchDao.save(t);
	}

	public void update(EmpiricalResearch empiricalResearch) {
		TempiricalResearch t = empiricalResearchDao.get(TempiricalResearch.class, empiricalResearch.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(empiricalResearch, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				TempiricalResearch t = empiricalResearchDao.get(TempiricalResearch.class, id);
				if (t != null) {
					empiricalResearchDao.delete(t);
				}
			}
		}
	}

	public TempiricalResearch get(EmpiricalResearch empiricalResearch) {
		TempiricalResearch menu = empiricalResearchDao.get(TempiricalResearch.class, empiricalResearch.getCid());
		return menu;
	}
	
	public void changeFlag(EmpiricalResearch empiricalResearch) {
		// TODO Auto-generated method stub
		TempiricalResearch t = empiricalResearchDao.get(TempiricalResearch.class, empiricalResearch.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tempiricalResearch c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tempiricalResearch c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			empiricalResearchDao.updateStatus(hql,empiricalResearch.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
