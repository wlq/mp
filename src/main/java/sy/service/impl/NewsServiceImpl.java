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
import sy.model.Tnews;
import sy.pageModel.DataGrid;
import sy.pageModel.News;
import sy.service.BugServiceI;
import sy.service.NewsServiceI;

/**
 * Bug Service
 * 
 * @author 
 * 
 */
@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl implements NewsServiceI {

	private BaseDaoI<Tnews> bugDao;

	public BaseDaoI<Tnews> getBugDao() {
		return bugDao;
	}

	@Autowired
	public void setBugDao(BaseDaoI<Tnews> bugDao) {
		this.bugDao = bugDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(News news) {
		DataGrid j = new DataGrid();
		j.setRows(getNewssFromTnews(find(news)));
		j.setTotal(total(news));
		return j;
	}

	private List<News> getNewssFromTnews(List<Tnews> tnewss) {
		List<News> newss = new ArrayList<News>();
		if (tnewss != null && tnewss.size() > 0) {
			for (Tnews tb : tnewss) {
				News b = new News();
				BeanUtils.copyProperties(tb, b);
				newss.add(b);
			}
		}
		return newss;
	}

	private List<Tnews> find(News news) {
		
		String hql = "select new Tnews(t.cid,t.cname,t.cpname,t.ccreatedatetime) from Tnews t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(news, hql, values);

		if (news.getSort() != null && news.getOrder() != null) {
			hql += " order by " + news.getSort() + " " + news.getOrder();
		}
		return bugDao.find(hql, values, news.getPage(), news.getRows());
	}

	private Long total(News news) {
		String hql = "select count(*) from Tnews t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(news, hql, values);
		return bugDao.count(hql, values);
	}

	private String addWhere(News news, String hql, List<Object> values) {
		return hql;
	}

	public void add(News news) {
		if (news.getCid() == null || news.getCid().trim().equals("")) {
			news.setCid(UUID.randomUUID().toString());
		}
		if (news.getCcreatedatetime() == null) {
			news.setCcreatedatetime(new Date());
		}
		Tnews t = new Tnews();
		BeanUtils.copyProperties(news, t);
		bugDao.save(t);
	}

	public void update(News news) {
		Tnews t = bugDao.get(Tnews.class, news.getCid());
		if (t != null) {
			if (news.getCcreatedatetime() == null) {
				news.setCcreatedatetime(new Date());
			}
			BeanUtils.copyProperties(news, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tnews t = bugDao.get(Tnews.class, id);
				if (t != null) {
					bugDao.delete(t);
				}
			}
		}
	}

	public Tnews get(News news) {
		return bugDao.get(Tnews.class, news.getCid());
	}

}
