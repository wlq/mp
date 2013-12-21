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
import sy.model.Tdevice;
import sy.model.Tdevice;
import sy.pageModel.ApplicationNote;
import sy.pageModel.DataGrid;
import sy.pageModel.Device;
import sy.service.DeviceServiceI;

@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl implements DeviceServiceI{

	private BaseDaoI<Tdevice> deviceDao;
    
	public BaseDaoI<Tdevice> getdeviceDao() {
		return deviceDao;
	}

	@Autowired
	public void setdeviceDao(BaseDaoI<Tdevice> deviceDao) {
		this.deviceDao = deviceDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Device device) {
		DataGrid j = new DataGrid();
		j.setRows(getdevicesFromTdevices(find(device)));
		j.setTotal(total(device));
		return j;
	}
	private List<Device> getdevicesFromTdevices(List<Tdevice> tdevices) {
		List<Device> devices = new ArrayList<Device>();
		if (tdevices != null && tdevices.size() > 0) {
			for (Tdevice tb : tdevices) {
				Device b = new Device();
				BeanUtils.copyProperties(tb, b);
				devices.add(b);
			}
		}
		return devices;
	}

	private List<Tdevice> find(Device device) {
		String hql = "from Tdevice t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(device, hql, values);

		if (device.getSort() != null && device.getOrder() != null) {
			hql += " order by " + device.getSort() + " " + device.getOrder();
		}
		return deviceDao.find(hql, values, device.getPage(), device.getRows());
	}

	private Long total(Device device) {
		String hql = "select count(*) from Tdevice t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(device, hql, values);
		return deviceDao.count(hql, values);
	}

	private String addWhere(Device device, String hql, List<Object> values) {
		return hql;
	}

	public void add(Device device) {
		if (device.getCid() == null || device.getCid().trim().equals("")) {
			device.setCid(UUID.randomUUID().toString());
		}
		device.setCflag("1");
		Tdevice t = new Tdevice();	
		BeanUtils.copyProperties(device, t);
		deviceDao.save(t);
	}

	public void update(Device device) {
		Tdevice t = deviceDao.get(Tdevice.class, device.getCid());
		if (t != null) {			
			BeanUtils.copyProperties(device, t, new String[] { "cid" });
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tdevice t = deviceDao.get(Tdevice.class, id);
				if (t != null) {
					deviceDao.delete(t);
				}
			}
		}
	}

	public Tdevice get(Device device) {
		Tdevice menu = deviceDao.get(Tdevice.class, device.getCid());
		return menu;
	}
	
	public void changeFlag(Device device) {
		// TODO Auto-generated method stub
		Tdevice t = deviceDao.get(Tdevice.class, device.getCid());
		String  cstatus = t.getCflag();
		String hql = " ";
		
		///
		if (Integer.parseInt(cstatus) == 0)
		{
			hql = "update tdevice c set c.cflag= 1 where c.cid=?";
		}else{
			hql = "update tdevice c set c.cflag= 0 where c.cid=?";
		}
		 
	
		try {
			deviceDao.updateStatus(hql,device.getCid());
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
}
