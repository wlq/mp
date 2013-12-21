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
import sy.model.Tkeytech;
import sy.model.Tkeytech;
import sy.model.Tproject;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Keytech;
import sy.pageModel.SessionInfo;
import sy.service.KeytechServiceI;
import sy.util.ResourceUtil;

@Service("keytechService")
public class KeytechServiceImpl extends BaseServiceImpl implements KeytechServiceI{
	private BaseDaoI<Tkeytech> keytechDao;
    
	public BaseDaoI<Tkeytech> getKeytechDao() {
		return keytechDao;
	}


	@Autowired
	public void setkeytechDao(BaseDaoI<Tkeytech> keytechDao) {
		this.keytechDao = keytechDao;
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Keytech keytech) {
		DataGrid j = new DataGrid();
		j.setRows(getkeytechsFromTkeytechs(find(keytech)));
		j.setTotal(total(keytech));
		return j;
	}
	private List<Keytech> getkeytechsFromTkeytechs(List<Tkeytech> tkeytechs) {
		List<Keytech> keytechs = new ArrayList<Keytech>();
		if (tkeytechs != null && tkeytechs.size() > 0) {
			for (Tkeytech tb : tkeytechs) {
				Keytech b = new Keytech();
				BeanUtils.copyProperties(tb, b);
				keytechs.add(b);
			}
		}
		return keytechs;
	}

	private List<Tkeytech> find(Keytech keytech) {
		String hql = "select new Tkeytech( t.cid, t.cname, t.cresponser,t.ccompany,t.cstarttime,t.cendtime, t.ckeywords,t.csummary, t.cphone, t.ctypeman,t.ctypetime, t.cflag) from Tkeytech t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(keytech, hql, values);

		if (keytech.getSort() != null && keytech.getOrder() != null) {
			hql += " order by " + keytech.getSort() + " " + keytech.getOrder();
		}
		return keytechDao.find(hql, values, keytech.getPage(), keytech.getRows());
	}

	private Long total(Keytech keytech) {
		String hql = "select count(*) from Tkeytech t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(keytech, hql, values);
		return keytechDao.count(hql, values);
	}

	private String addWhere(Keytech keytech, String hql, List<Object> values) {
		return hql;
	}

	public void add(Keytech keytech) {
		if (keytech.getCid() == null || keytech.getCid().trim().equals("")) {
			keytech.setCid(UUID.randomUUID().toString());
		}
		keytech.setCflag("1");
		Tkeytech t = new Tkeytech();	
		BeanUtils.copyProperties(keytech, t);
		keytechDao.save(t);
	}

	public void update(Keytech keytech) {
		Tkeytech t = keytechDao.get(Tkeytech.class, keytech.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(keytech, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tkeytech t = keytechDao.get(Tkeytech.class, id);
				if (t != null) {
					keytechDao.delete(t);
				}
			}
		}
	}

	public Tkeytech get(Keytech keytech) {
		Tkeytech menu = keytechDao.get(Tkeytech.class, keytech.getCid());
		return menu;
	}
	
	public void changeFlag(Keytech keytech) {
		// TODO Auto-generated method stub
		Tkeytech t = keytechDao.get(Tkeytech.class, keytech.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tkeytech c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tkeytech c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			keytechDao.updateStatus(hql,keytech.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
