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
import sy.model.Ttrainlearner;
import sy.model.Ttrainlearner;
import sy.model.Tproject;
import sy.pageModel.DataGrid;
import sy.pageModel.EmpiricalResearch;
import sy.pageModel.Trainlearner;
import sy.pageModel.SessionInfo;
import sy.service.TrainlearnerServiceI;
import sy.util.ResourceUtil;

@Service("trainlearnerService")
public class TrainlearnerServiceImpl extends BaseServiceImpl implements TrainlearnerServiceI{

	private BaseDaoI<Ttrainlearner> trainlearnerDao;
    
	public BaseDaoI<Ttrainlearner> gettrainlearnerDao() {
		return trainlearnerDao;
	}

	@Autowired
	public void settrainlearnerDao(BaseDaoI<Ttrainlearner> trainlearnerDao) {
		this.trainlearnerDao = trainlearnerDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Trainlearner trainlearner) {
		DataGrid j = new DataGrid();
		j.setRows(gettrainlearnersFromTtrainlearners(find(trainlearner)));
		j.setTotal(total(trainlearner));
		return j;
	}
	private List<Trainlearner> gettrainlearnersFromTtrainlearners(List<Ttrainlearner> ttrainlearners) {
		List<Trainlearner> trainlearners = new ArrayList<Trainlearner>();
		if (ttrainlearners != null && ttrainlearners.size() > 0) {
			for (Ttrainlearner tb : ttrainlearners) {
				Trainlearner b = new Trainlearner();
				BeanUtils.copyProperties(tb, b);
				trainlearners.add(b);
			}
		}
		return trainlearners;
	}

	private List<Ttrainlearner> find(Trainlearner trainlearner) {//cID cseq ccontent cname csex cage ciden ccompany ctitle cmajor cphone cmail cresult cnum
		String hql = "select new Ttrainlearner( t.cid, t.cseq, t.ccontent, t.cname, t.csex, t.cage, t.ciden, t.ccompany, t.ctitle, t.cmajor,t.cphone, t.cmail, t.cresult,  t.cnum, t.cstyle) from Ttrainlearner t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(trainlearner, hql, values);

		if (trainlearner.getSort() != null && trainlearner.getOrder() != null) {
			hql += " order by " + trainlearner.getSort() + " " + trainlearner.getOrder();
		}
		return trainlearnerDao.find(hql, values, trainlearner.getPage(), trainlearner.getRows());
	}

	private Long total(Trainlearner trainlearner) {
		String hql = "select count(*) from Ttrainlearner t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(trainlearner, hql, values);
		return trainlearnerDao.count(hql, values);
	}

	private String addWhere(Trainlearner trainlearner, String hql, List<Object> values) {
		return hql;
	}

	public void add(Trainlearner trainlearner) {
		if (trainlearner.getCid() == null || trainlearner.getCid().trim().equals("")) {
			trainlearner.setCid(UUID.randomUUID().toString());
		}
		//trainlearner.setCflag("1");
		Ttrainlearner t = new Ttrainlearner();	
		BeanUtils.copyProperties(trainlearner, t);
		trainlearnerDao.save(t);
	}

	public void update(Trainlearner trainlearner) {
		Ttrainlearner t = trainlearnerDao.get(Ttrainlearner.class, trainlearner.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(trainlearner, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Ttrainlearner t = trainlearnerDao.get(Ttrainlearner.class, id);
				if (t != null) {
					trainlearnerDao.delete(t);
				}
			}
		}
	}

	public Ttrainlearner get(Trainlearner trainlearner) {
		Ttrainlearner menu = trainlearnerDao.get(Ttrainlearner.class, trainlearner.getCid());
		return menu;
	}
	
	public void changeFlag(Trainlearner trainlearner) {
		/* TODO Auto-generated method stub
		Ttrainlearner t = trainlearnerDao.get(Ttrainlearner.class, trainlearner.getCid());
		//String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		//if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update ttrainlearner c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update ttrainlearner c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			trainlearnerDao.updateStatus(hql,trainlearner.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }*/
	}
}
