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
import sy.model.Tsoft;
import sy.pageModel.DataGrid;
import sy.pageModel.Soft;
import sy.service.SoftServiceI;

@Service("softService")
public class SoftServiceImpl  extends BaseServiceImpl implements SoftServiceI{

	private BaseDaoI<Tsoft> softDao;
    
	public BaseDaoI<Tsoft> getsoftDao() {
		return softDao;
	}

	@Autowired
	public void setsoftDao(BaseDaoI<Tsoft> softDao) {
		this.softDao = softDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Soft soft) {
		DataGrid j = new DataGrid();
		j.setRows(getsoftsFromTsofts(find(soft)));
		j.setTotal(total(soft));
		return j;
	}
	private List<Soft> getsoftsFromTsofts(List<Tsoft> tsofts) {
		List<Soft> softs = new ArrayList<Soft>();
		if (tsofts != null && tsofts.size() > 0) {
			for (Tsoft tb : tsofts) {
				Soft b = new Soft();
				BeanUtils.copyProperties(tb, b);
				softs.add(b);
			}
		}
		return softs;
	}

	private List<Tsoft> find(Soft soft) {
		String hql = "select new Tsoft( t.cid, t.csoftid, t.csoftname,t.cpname,t.cdonetime, t.cfirsttime,t.cway,t.cright,t.ccommitid,t.cflag) from Tsoft t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(soft, hql, values);

		if (soft.getSort() != null && soft.getOrder() != null) {
			hql += " order by " + soft.getSort() + " " + soft.getOrder();
		}
		return softDao.find(hql, values, soft.getPage(), soft.getRows());
	}

	private Long total(Soft soft) {
		String hql = "select count(*) from Tsoft t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(soft, hql, values);
		return softDao.count(hql, values);
	}

	private String addWhere(Soft soft, String hql, List<Object> values) {
		return hql;
	}

    public void add(Soft soft) {
		if (soft.getCid() == null || soft.getCid().trim().equals("")) {
			soft.setCid(UUID.randomUUID().toString());
		}
		soft.setCflag("1");
		Tsoft t = new Tsoft();	
		BeanUtils.copyProperties(soft, t);
		softDao.save(t);
	}

	public void update(Soft soft) {
		Tsoft t = softDao.get(Tsoft.class, soft.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(soft, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tsoft t = softDao.get(Tsoft.class, id);
				if (t != null) {
					softDao.delete(t);
				}
			}
		}
	}

	public Tsoft get(Soft soft) {
		Tsoft menu = softDao.get(Tsoft.class, soft.getCid());
		return menu;
	}

	public void changeFlag(Soft soft) {
		// TODO Auto-generated method stub
		Tsoft t = softDao.get(Tsoft.class, soft.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tsoft c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tsoft c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			softDao.updateStatus(hql,soft.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
