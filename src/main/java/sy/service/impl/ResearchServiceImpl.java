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
import sy.model.Tresearch;
import sy.model.Tproject;
import sy.pageModel.DataGrid;
import sy.pageModel.Research;
import sy.pageModel.SessionInfo;
import sy.service.ResearchServiceI;
import sy.util.ResourceUtil;

@Service("researchService")
public class ResearchServiceImpl extends BaseServiceImpl implements ResearchServiceI{

	private BaseDaoI<Tresearch> researchDao;
    
	public BaseDaoI<Tresearch> geTresearchDao() {
		return researchDao;
	}

	@Autowired
	public void seTresearchDao(BaseDaoI<Tresearch> researchDao) {
		this.researchDao = researchDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Research research) {
		DataGrid j = new DataGrid();
		j.setRows(geTresearchsFromTresearchs(find(research)));
		j.setTotal(total(research));
		return j;
	}
	private List<Research> geTresearchsFromTresearchs(List<Tresearch> Tresearchs) {
		List<Research> researchs = new ArrayList<Research>();
		if (Tresearchs != null && Tresearchs.size() > 0) {
			for (Tresearch tb : Tresearchs) {
				Research b = new Research();
				BeanUtils.copyProperties(tb, b);
				researchs.add(b);
			}
		}
		return researchs;
	}

	private List<Tresearch> find(Research research) {
		String hql = "select new Tresearch( t.cid, t.cclassify, t.cname,t.clanguage,t.cckeyword,t.cekeyword,t.cinformation, t.cnote, t.cstorage, t.cyear,t.cunit, t.ccontactid, t.ctypeman, t.ctypetime, t.cflag) from Tresearch t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(research, hql, values);

		if (research.getSort() != null && research.getOrder() != null) {
			hql += " order by " + research.getSort() + " " + research.getOrder();
		}
		return researchDao.find(hql, values, research.getPage(), research.getRows());
	}

	private Long total(Research research) {
		String hql = "select count(*) from Tresearch t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(research, hql, values);
		return researchDao.count(hql, values);
	}

	private String addWhere(Research research, String hql, List<Object> values) {
		return hql;
	}

	public void add(Research research) {
		if (research.getCid() == null || research.getCid().trim().equals("")) {
			research.setCid(UUID.randomUUID().toString());
		}
		research.setCflag("1");
		Tresearch t = new Tresearch();	
		BeanUtils.copyProperties(research, t);
		researchDao.save(t);
	}

	public void update(Research research) {
		Tresearch t = researchDao.get(Tresearch.class, research.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(research, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tresearch t = researchDao.get(Tresearch.class, id);
				if (t != null) {
					researchDao.delete(t);
				}
			}
		}
	}

	public Tresearch get(Research research) {
		Tresearch menu = researchDao.get(Tresearch.class, research.getCid());
		return menu;
	}
	
	public void changeFlag(Research research) {
		// TODO Auto-generated method stub
		Tresearch t = researchDao.get(Tresearch.class, research.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update Tresearch c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update Tresearch c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			researchDao.updateStatus(hql,research.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
