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
import sy.model.Tnotice;
import sy.model.Tmenu;
import sy.model.Tpay;
import sy.model.Tproject;
import sy.model.Tuser;
import sy.pageModel.DataGrid;
import sy.pageModel.Notice;
import sy.pageModel.SessionInfo;
import sy.service.NoticeServiceI;
import sy.util.ResourceUtil;

@Service("noticeService")
public class NoticeServiceImpl extends BaseServiceImpl implements NoticeServiceI {

	private BaseDaoI<Tnotice> noticeDao;
	private BaseDaoI<Tproject> projectDao;
	
	public BaseDaoI<Tnotice> getnoticeDao() {
		return noticeDao;
	}

	public BaseDaoI<Tproject> getProjectDao() {
		return projectDao;
	}

	@Autowired
	public void setnoticeDao(BaseDaoI<Tnotice> noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Autowired
	public void setProjectDao(BaseDaoI<Tproject> projectDao) {
		this.projectDao = projectDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Notice notice) {
		DataGrid j = new DataGrid();
		j.setRows(getnoticesFromTnotices(find(notice)));
		j.setTotal(total(notice));
		return j;
	}
	private List<Notice> getnoticesFromTnotices(List<Tnotice> tnotices) {
		List<Notice> notices = new ArrayList<Notice>();
		if (tnotices != null && tnotices.size() > 0) {
			for (Tnotice tb : tnotices) {
				Notice b = new Notice();
				BeanUtils.copyProperties(tb, b);
				notices.add(b);
			}
		}
		return notices;
	}

	private List<Tnotice> find(Notice notice) {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String username  = sessionInfo.getLoginName();
		username = "'"+username+"'";		
		String hql = "select new Tnotice(t.cid,t.cget,t.csend,t.csendtime,t.cdatei) from Tnotice t where csend=" +
				username +
				" or cget="+username;

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(notice, hql, values);

		if (notice.getSort() != null && notice.getOrder() != null) {
			hql += " order by " + notice.getSort() + " " + notice.getOrder();
		}
		return noticeDao.find(hql, values, notice.getPage(), notice.getRows());
	}

	private Long total(Notice notice) {
		String hql = "select count(*) from Tnotice t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(notice, hql, values);
		return noticeDao.count(hql, values);
	}

	private String addWhere(Notice notice, String hql, List<Object> values) {
		return hql;
	}

	public void add(Notice notice) {
		if (notice.getCid() == null || notice.getCid().trim().equals("")) {
			notice.setCid(UUID.randomUUID().toString());
		}
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String cprojectid  = sessionInfo.getLoginName();
		notice.setCsend(cprojectid);			
		Tnotice t = new Tnotice();
		BeanUtils.copyProperties(notice, t);
		noticeDao.save(t);
	}

	public void update(Notice notice) {
		Tnotice t = noticeDao.get(Tnotice.class, notice.getCid());
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String username  = sessionInfo.getLoginName();
		notice.setCsend(username);
		if (t != null) {			
			BeanUtils.copyProperties(notice, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tnotice t = noticeDao.get(Tnotice.class, id);
				if (t != null) {
					noticeDao.delete(t);					
				}
			}			
		}
	}

	public Tnotice get(Notice notice) {
		Tnotice menu = noticeDao.get(Tnotice.class, notice.getCid());
		return menu;
	}

	
}
