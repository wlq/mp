package sy.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sy.dao.BaseDaoI;
/**
 * 基础数据库操作实现类
 * 
 * @author 
 * 
 */

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	public List<T> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}

	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql, Object[] param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public Long count(String hql) {
		return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public Long count(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}
	
	
	/*
	 * author zhang
	 * 更新进度
	 */	
	public Integer updateProgress(String hql2, String id) {

			
			Query q = this.getCurrentSession().createSQLQuery(hql2);

		    q.setParameter(0, id);
			q.setParameter(1, id);
			q.setParameter(2, id);
			int d=0;
			try {
				 d=q.executeUpdate();
				 System.out.println("progress:"+d);
				// this.getCurrentSession().clear();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return d;
		}
		
	/*
	 * author zhang
	 * 更新状态
	 */	
		public Integer updatestatus(String hql,String cstatus,String id)
		{
			
			Query q = this.getCurrentSession().createSQLQuery(hql);

		    q.setParameter(0, cstatus);
			q.setParameter(1, id);
			int d=0;
			try {
				 d=q.executeUpdate();
				 
				// this.getCurrentSession().clear();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return d;
		}
		/*
		 * author wei
		 * 更新状态
		 */
		public void updateStatus(String hql,String id) {
			// TODO Auto-generated method stub

			Query q = this.getCurrentSession().createSQLQuery(hql);

		    q.setParameter(0, id);
			int d=0;
			try {
				 d=q.executeUpdate();
				 
				// this.getCurrentSession().clear();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			System.out.println("status:"+d);
			
		}

		public List<Double> getSum(String hql, String username, String course) {
			// TODO Auto-generated method stub
			Query q = this.getCurrentSession().createSQLQuery(hql);
		    q.setParameter(0, username);
			q.setParameter(1, course);
						
			return q.list();
		}

		public Integer executeHql(String hql, Double param0, String param1) {
			Query q = this.getCurrentSession().createQuery(hql);			
			q.setParameter(0, param0);
			q.setParameter(1, param1);
			return q.executeUpdate();
			
		}

		public String executeHql(String hql, String param0) {
			Query q = this.getCurrentSession().createQuery(hql);			
			q.setParameter(0, param0);
			return (String) q.uniqueResult();
		}

		public Integer executeHql(String hql, String param0, String param1) {
			Query q = this.getCurrentSession().createQuery(hql);			
			q.setParameter(0, param0);
			q.setParameter(1, param1);
			return q.executeUpdate();
		}

		public Integer executeHql1(String hql3, String ccourse, String username,
				String cfuid) {
			Query q = this.getCurrentSession().createQuery(hql3);			
			q.setParameter(0, username);
			q.setParameter(1, ccourse);
			q.setParameter(2, cfuid);
			q.setParameter(3, ccourse);
			return q.executeUpdate();
		}

		public List<Double> finds(String hql, Object[] param) {
			Query q = this.getCurrentSession().createQuery(hql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
			}
			return q.list();
		}

		public String find1(String hql) {
			
			return (String) this.getCurrentSession().createQuery(hql).uniqueResult();
		}
		
}
