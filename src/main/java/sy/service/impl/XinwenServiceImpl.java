package sy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sy.dao.BaseDaoI;
import sy.model.Txinwen;
import sy.pageModel.DataGrid;
import sy.pageModel.Xinwen;
import sy.service.XinwenServiceI;

/**
 * Bug Service
 * 
 * @author 
 * 
 */
@Service("xinwenService")
public class XinwenServiceImpl extends BaseServiceImpl implements XinwenServiceI {

	private BaseDaoI<Txinwen> xinwenDao;

	public BaseDaoI<Txinwen> getXinwenDao() {
		return xinwenDao;
	}

	@Autowired
	public void setXinwenDao(BaseDaoI<Txinwen> xinwenDao) {
		this.xinwenDao = xinwenDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Xinwen xinwen) {
		DataGrid j = new DataGrid();
		j.setRows(getXinwensFromTxinwen(find(xinwen)));
		j.setTotal(total(xinwen));
		return j;
	}

	private List<Xinwen> getXinwensFromTxinwen(List<Txinwen> txinwens) {
		List<Xinwen> xinwens = new ArrayList<Xinwen>();
		if (txinwens != null && txinwens.size() > 0) {
			for (Txinwen tb : txinwens) {
				Xinwen b = new Xinwen();
				BeanUtils.copyProperties(tb, b);
				xinwens.add(b);
			}
		}
		return xinwens;
	}

	private List<Txinwen> find(Xinwen xinwen) {
		
		String hql = "select new Txinwen(t.cid,t.cname,t.cabstract,t.ccreatedatetime) from Txinwen t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(xinwen, hql, values);

		if (xinwen.getSort() != null && xinwen.getOrder() != null) {
			hql += " order by " + xinwen.getSort() + " " + xinwen.getOrder();
		}
		return xinwenDao.find(hql, values, xinwen.getPage(), xinwen.getRows());
	}

	private Long total(Xinwen xinwen) {
		String hql = "select count(*) from Txinwen t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(xinwen, hql, values);
		return xinwenDao.count(hql, values);
	}

	private String addWhere(Xinwen xinwen, String hql, List<Object> values) {
		return hql;
	}

	public void add(Xinwen xinwen) {
		if (xinwen.getCid() == null || xinwen.getCid().trim().equals("")) {
			xinwen.setCid(UUID.randomUUID().toString());
		}
		if (xinwen.getCcreatedatetime() == null) {
			xinwen.setCcreatedatetime(new Date());
		}
		Txinwen t = new Txinwen();
		BeanUtils.copyProperties(xinwen, t);
		xinwenDao.save(t);
	}

	public void update(Xinwen xinwen) {
		Txinwen t = xinwenDao.get(Txinwen.class, xinwen.getCid());
		if (t != null) {
			if (xinwen.getCcreatedatetime() == null) {
				xinwen.setCcreatedatetime(new Date());
			}
			BeanUtils.copyProperties(xinwen, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Txinwen t = xinwenDao.get(Txinwen.class, id);
				if (t != null) {
					xinwenDao.delete(t);
				}
			}
		}
	}

	public Txinwen get(Xinwen xinwen) {
		return xinwenDao.get(Txinwen.class, xinwen.getCid());
	}

}
