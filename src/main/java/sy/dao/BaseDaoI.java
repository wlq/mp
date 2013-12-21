package sy.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 基础数据库操作类
 * 
 * @author 
 * 
 */
public interface BaseDaoI<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page, Integer rows);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);
	
	/**
	 * 执行HQL语句
	 * 更新进度
	 * @param hql
	 * @param param
	 * @author zhang
	 * @return
	 */
	public Integer updateProgress(String hql2, String id);
	
	/**
	 * 执行HQL语句
	 * 更新状态
	 * @param hql
	 * @param param
	 * @return
	 * @author zhang
	 */	
	public Integer updatestatus(String hql,String cstatus,String id);

	/**
	 * 
	 * @author zhang
	 */	
	public void updateStatus(String hql,String id);
	
	/**
	 * 执行HQL语句
	 * 获得sum值
	 * @param hql
	 * @param param
	 * @return
	 * @author wei
	 */	
	public List<Double> getSum(String hql,String username,String course);
	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 * @author wei
	 */
	public Integer executeHql(String hql, Double  ccost, String  param1);
	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return cpid
	 * @author wei
	 */
	public String executeHql(String hql,  String  param1);
	/**
	 * 执行HQL语句
	 * 更新cost
	 * @param hql
	 * @param param
	 * @author wei
	 */
	public Integer executeHql(String hql,  String  param0,  String  param1);

	public Integer executeHql1(String hql3, String ccourse, String username,
			String ccourse2);
	
	public List<Double> finds(String hql, Object[] param);
	public String find1(String hql);
}
