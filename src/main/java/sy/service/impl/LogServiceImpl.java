package sy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sy.dao.BaseDaoI;
import sy.model.Tlog;
import sy.model.Tlog;
import sy.pageModel.DataGrid;
import sy.pageModel.Log;
import sy.service.LogServiceI;

/**
 * 日志Service
 * 
 * @author wei
 * 
 */
@Service("logService")
public class LogServiceImpl  extends BaseServiceImpl implements LogServiceI{

	private BaseDaoI<Tlog> logDao;
	
	public BaseDaoI<Tlog> getlogDao() {
		return logDao;
	}

	@Autowired
	public void setlogDao(BaseDaoI<Tlog> logDao) {
		this.logDao = logDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Log log) {
		DataGrid j = new DataGrid();
		j.setRows(getlogsFromTlogs(find(log)));
		j.setTotal(total(log));
		return j;
	}
	
	public void add(Log log) {
		
		Tlog t = new Tlog();	
		log.setClogintime(new Date());
		BeanUtils.copyProperties(log, t);		
		logDao.save(t);
	}	
	
	private List<Log> getlogsFromTlogs(List<Tlog> tlogs) {
		List<Log> logs = new ArrayList<Log>();
		if (tlogs != null && tlogs.size() > 0) {
			for (Tlog tb : tlogs) {
				Log b = new Log();
				BeanUtils.copyProperties(tb, b);
				logs.add(b);
			}
		}
		return logs;
	}

	private List<Tlog> find(Log log) {
		String hql = "select new Tlog(t.cid,t.cname,t.cip,t.clogintime) from Tlog t where 1=1  ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(log, hql, values);

		if (log.getSort() != null && log.getOrder() != null) {
			hql += " order by " + log.getSort() + " " + log.getOrder();
		}
		return logDao.find(hql, values, log.getPage(), log.getRows());
	}

	private Long total(Log log) {
		String hql = "select count(*) from Tlog t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(log, hql, values);
		return logDao.count(hql, values);
	}

	private String addWhere(Log log, String hql, List<Object> values) {
		return hql;
	}

}
