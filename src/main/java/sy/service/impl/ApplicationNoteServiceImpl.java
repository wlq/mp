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
import sy.model.TapplicationNote;
import sy.model.TapplicationNote;
import sy.pageModel.DataGrid;
import sy.pageModel.ApplicationNote;
import sy.service.ApplicationNoteServiceI;

@Service("applicationNoteService")
public class ApplicationNoteServiceImpl  extends BaseServiceImpl implements ApplicationNoteServiceI{

	private BaseDaoI<TapplicationNote> applicationNoteDao;
    
	public BaseDaoI<TapplicationNote> getapplicationNoteDao() {
		return applicationNoteDao;
	}

	@Autowired
	public void setapplicationNoteDao(BaseDaoI<TapplicationNote> applicationNoteDao) {
		this.applicationNoteDao = applicationNoteDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(ApplicationNote applicationNote) {
		DataGrid j = new DataGrid();
		j.setRows(getapplicationNotesFromTapplicationNotes(find(applicationNote)));
		j.setTotal(total(applicationNote));
		return j;
	}
	private List<ApplicationNote> getapplicationNotesFromTapplicationNotes(List<TapplicationNote> tapplicationNotes) {
		List<ApplicationNote> applicationNotes = new ArrayList<ApplicationNote>();
		if (tapplicationNotes != null && tapplicationNotes.size() > 0) {
			for (TapplicationNote tb : tapplicationNotes) {
				ApplicationNote b = new ApplicationNote();
				BeanUtils.copyProperties(tb, b);
				applicationNotes.add(b);
			}
		}
		return applicationNotes;
	}

	private List<TapplicationNote> find(ApplicationNote applicationNote) {
		String hql = "select new TapplicationNote( t.cid, t.cname, t.csuitableid,t.cinformation, t.cnote, t.cevaluation,t.cidentify, t.crighted, t.ccontactid, t.ctypetime, t.cflag) from TapplicationNote t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(applicationNote, hql, values);

		if (applicationNote.getSort() != null && applicationNote.getOrder() != null) {
			hql += " order by " + applicationNote.getSort() + " " + applicationNote.getOrder();
		}
		return applicationNoteDao.find(hql, values, applicationNote.getPage(), applicationNote.getRows());
	}

	private Long total(ApplicationNote applicationNote) {
		String hql = "select count(*) from TapplicationNote t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(applicationNote, hql, values);
		return applicationNoteDao.count(hql, values);
	}

	private String addWhere(ApplicationNote applicationNote, String hql, List<Object> values) {
		return hql;
	}

	public void add(ApplicationNote applicationNote) {
		if (applicationNote.getCid() == null || applicationNote.getCid().trim().equals("")) {
			applicationNote.setCid(UUID.randomUUID().toString());
		}
		applicationNote.setCflag("1");
		TapplicationNote t = new TapplicationNote();	
		BeanUtils.copyProperties(applicationNote, t);
		applicationNoteDao.save(t);
	}

	public void update(ApplicationNote applicationNote) {
		TapplicationNote t = applicationNoteDao.get(TapplicationNote.class, applicationNote.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(applicationNote, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				TapplicationNote t = applicationNoteDao.get(TapplicationNote.class, id);
				if (t != null) {
					applicationNoteDao.delete(t);
				}
			}
		}
	}

	public TapplicationNote get(ApplicationNote applicationNote) {
		TapplicationNote menu = applicationNoteDao.get(TapplicationNote.class, applicationNote.getCid());
		return menu;
	}

	public void changeFlag(ApplicationNote applicationNote) {
		// TODO Auto-generated method stub
		TapplicationNote t = applicationNoteDao.get(TapplicationNote.class, applicationNote.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tapplicationNote c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tapplicationNote c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			applicationNoteDao.updateStatus(hql,applicationNote.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
