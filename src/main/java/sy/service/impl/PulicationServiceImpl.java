package sy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sy.dao.BaseDaoI;
import sy.model.Tpulication;
import sy.model.Tpulication;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Pulication;
import sy.service.PulicationServiceI;

@Service("pulicationService")
public class PulicationServiceImpl extends BaseServiceImpl implements PulicationServiceI{

	private BaseDaoI<Tpulication> pulicationDao;
    
	public BaseDaoI<Tpulication> getpulicationDao() {
		return pulicationDao;
	}

	@Autowired
	public void setpulicationDao(BaseDaoI<Tpulication> pulicationDao) {
		this.pulicationDao = pulicationDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Pulication pulication) {
		DataGrid j = new DataGrid();
		j.setRows(getpulicationsFromTpulications(find(pulication)));
		j.setTotal(total(pulication));
		return j;
	}
	private List<Pulication> getpulicationsFromTpulications(List<Tpulication> tpulications) {
		List<Pulication> pulications = new ArrayList<Pulication>();
		if (tpulications != null && tpulications.size() > 0) {
			for (Tpulication tb : tpulications) {
				Pulication b = new Pulication();
				BeanUtils.copyProperties(tb, b);
				pulications.add(b);
			}
		}
		return pulications;
	}

	private List<Tpulication> find(Pulication pulication) {
		String hql = "select new Tpulication( t.cid, t.cname, t.cauthor,t.cckeyword, t.cekeyword, t.cpublishhous,t.cpublishtime, t.cwords, t.cisbn, t.csummary,t.ctypeman, t.ctypetime, t.cflag) from Tpulication t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(pulication, hql, values);

		if (pulication.getSort() != null && pulication.getOrder() != null) {
			hql += " order by " + pulication.getSort() + " " + pulication.getOrder();
		}
		return pulicationDao.find(hql, values, pulication.getPage(), pulication.getRows());
	}

	private Long total(Pulication pulication) {
		String hql = "select count(*) from Tpulication t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(pulication, hql, values);
		return pulicationDao.count(hql, values);
	}

	private String addWhere(Pulication pulication, String hql, List<Object> values) {
		return hql;
	}

	public void add(Pulication pulication) {
		if (pulication.getCid() == null || pulication.getCid().trim().equals("")) {
			pulication.setCid(UUID.randomUUID().toString());
		}
		pulication.setCflag("1");
		Tpulication t = new Tpulication();	
		BeanUtils.copyProperties(pulication, t);
		pulicationDao.save(t);
	}

	public void update(Pulication pulication) {
		Tpulication t = pulicationDao.get(Tpulication.class, pulication.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(pulication, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tpulication t = pulicationDao.get(Tpulication.class, id);
				if (t != null) {
					pulicationDao.delete(t);
				}
			}
		}
	}

	public Tpulication get(Pulication pulication) {
		Tpulication menu = pulicationDao.get(Tpulication.class, pulication.getCid());
		return menu;
	}
	
	public void changeFlag(Pulication pulication) {
		// TODO Auto-generated method stub
		Tpulication t = pulicationDao.get(Tpulication.class, pulication.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tpulication c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tpulication c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			pulicationDao.updateStatus(hql,pulication.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
