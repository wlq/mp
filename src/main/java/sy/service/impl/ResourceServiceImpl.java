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
import sy.model.Tresource;
import sy.model.Tresource;
import sy.model.Tproject;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Resource;
import sy.pageModel.SessionInfo;
import sy.service.ResourceServiceI;
import sy.util.ResourceUtil;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceServiceI{

	private BaseDaoI<Tresource> resourceDao;
    
	public BaseDaoI<Tresource> getresourceDao() {
		return resourceDao;
	}

	@Autowired
	public void setresourceDao(BaseDaoI<Tresource> resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Resource resource) {
		DataGrid j = new DataGrid();
		j.setRows(getresourcesFromTresources(find(resource)));
		j.setTotal(total(resource));
		return j;
	}
	private List<Resource> getresourcesFromTresources(List<Tresource> tresources) {
		List<Resource> resources = new ArrayList<Resource>();
		if (tresources != null && tresources.size() > 0) {
			for (Tresource tb : tresources) {
				Resource b = new Resource();
				BeanUtils.copyProperties(tb, b);
				resources.add(b);
			}
		}
		return resources;
	}

	private List<Tresource> find(Resource resource) {
		String hql = "select new Tresource( t.cid, t.cname, t.cclassify,t.csummary,t.cmount, t.cinformation,t.cstorage, t.cunit, t.cprinciple,t.ccontactid, t.cbackup1, t.cbackup2, t.cflag) from Tresource t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(resource, hql, values);

		if (resource.getSort() != null && resource.getOrder() != null) {
			hql += " order by " + resource.getSort() + " " + resource.getOrder();
		}
		return resourceDao.find(hql, values, resource.getPage(), resource.getRows());
	}

	private Long total(Resource resource) {
		String hql = "select count(*) from Tresource t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(resource, hql, values);
		return resourceDao.count(hql, values);
	}

	private String addWhere(Resource resource, String hql, List<Object> values) {
		return hql;
	}

	public void add(Resource resource) {
		if (resource.getCid() == null || resource.getCid().trim().equals("")) {
			resource.setCid(UUID.randomUUID().toString());
		}
		resource.setCflag("1");
		Tresource t = new Tresource();	
		BeanUtils.copyProperties(resource, t);
		resourceDao.save(t);
	}

	public void update(Resource resource) {
		Tresource t = resourceDao.get(Tresource.class, resource.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(resource, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tresource t = resourceDao.get(Tresource.class, id);
				if (t != null) {
					resourceDao.delete(t);
				}
			}
		}
	}

	public Tresource get(Resource resource) {
		Tresource menu = resourceDao.get(Tresource.class, resource.getCid());
		return menu;
	}
	
	public void changeFlag(Resource resource) {
		// TODO Auto-generated method stub
		Tresource t = resourceDao.get(Tresource.class, resource.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tresource c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tresource c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			resourceDao.updateStatus(hql,resource.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
