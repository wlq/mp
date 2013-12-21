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
import sy.model.Tpaper;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Paper;
import sy.service.PaperServiceI;

@Service("paperService")
public class PaperServiceImpl extends BaseServiceImpl implements PaperServiceI{

	private BaseDaoI<Tpaper> paperDao;
    
	public BaseDaoI<Tpaper> getpaperDao() {
		return paperDao;
	}

	@Autowired
	public void setpaperDao(BaseDaoI<Tpaper> paperDao) {
		this.paperDao = paperDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Paper paper) {
		DataGrid j = new DataGrid();
		j.setRows(getpapersFromTpapers(find(paper)));
		j.setTotal(total(paper));
		return j;
	}
	private List<Paper> getpapersFromTpapers(List<Tpaper> tpapers) {
		List<Paper> papers = new ArrayList<Paper>();
		if (tpapers != null && tpapers.size() > 0) {
			for (Tpaper tb : tpapers) {
				Paper b = new Paper();
				BeanUtils.copyProperties(tb, b);
				papers.add(b);
			}
		}
		return papers;
	}

	private List<Tpaper> find(Paper paper) {
		String hql = "select new Tpaper( t.cid, t.cname,t.cckeyword, t.cekeyword,t.csummary, t.clanguage, t.cfcontactid,t.cccontactid, t.cperiodical, t.cissue,t.cstate, t.cpublishtime, t.cclassify, t.cinde,t.ctypeman, t.ctypetime, t.cflag) from Tpaper t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(paper, hql, values);

		if (paper.getSort() != null && paper.getOrder() != null) {
			hql += " order by " + paper.getSort() + " " + paper.getOrder();
		}
		return paperDao.find(hql, values, paper.getPage(), paper.getRows());
	}

	private Long total(Paper paper) {
		String hql = "select count(*) from Tpaper t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(paper, hql, values);
		return paperDao.count(hql, values);
	}

	private String addWhere(Paper paper, String hql, List<Object> values) {
		if (paper.getCname() != null && !paper.getCname().trim().equals("")) {
			hql += " and t.cname like ? ";
			values.add("%%" + paper.getCname().trim() + "%%");
		}
		if (paper.getCfcontactid() != null) {
			hql += " and t.cfcontactid like ? ";
			values.add("%%" + paper.getCfcontactid().trim() + "%%");
		}
		return hql;
	}

	public void add(Paper paper) {
		if (paper.getCid() == null || paper.getCid().trim().equals("")) {
			paper.setCid(UUID.randomUUID().toString());
		}
		paper.setCflag("1");
		Tpaper t = new Tpaper();	
		BeanUtils.copyProperties(paper, t);
		paperDao.save(t);
	}

	public void update(Paper paper) {
		Tpaper t = paperDao.get(Tpaper.class, paper.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(paper, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tpaper t = paperDao.get(Tpaper.class, id);
				if (t != null) {
					paperDao.delete(t);
				}
			}
		}
	}

	public Tpaper get(Paper paper) {
		Tpaper menu = paperDao.get(Tpaper.class, paper.getCid());
		return menu;
	}
	
	public void changeFlag(Paper paper) {
		// TODO Auto-generated method stub
		Tpaper t = paperDao.get(Tpaper.class, paper.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tpaper c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tpaper c set c.cflag= 0 where c.cid=?";
		}
		 System.out.println(hql);
	
		try {
			paperDao.updateStatus(hql,paper.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
