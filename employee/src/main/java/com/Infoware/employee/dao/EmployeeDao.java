package com.Infoware.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Infoware.employee.pojo.EmployeePojo;

@Repository
public class EmployeeDao extends AbstractDao {

	private int pageSize=10;
	private static String delete_id = "delete from EmployeePojo p where id=:id";
	private static String select_id = "select p from EmployeePojo p where id=:id";
	private static String select_all = "select p from EmployeePojo p order by id desc";
	private static String select_limited = "select p from EmployeePojo p where name LIKE :name order by id desc";
	private static String TOTAL_ROWS = "Select count(*) from EmployeePojo p";
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void insert(EmployeePojo p) {
		em.persist(p);
	}

	public int delete(int id) {
		Query query = em.createQuery(delete_id);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	public EmployeePojo select(int id) {
		TypedQuery<EmployeePojo> query = getQuery(select_id, EmployeePojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}

	public List<EmployeePojo> selectAll() {
		TypedQuery<EmployeePojo> query = getQuery(select_all, EmployeePojo.class);
		return query.getResultList();
	}

	public List<EmployeePojo> selectLimited(int pageNo, String name){
		TypedQuery<EmployeePojo> query = getQuery(select_limited, EmployeePojo.class);
		query.setParameter("name", "%"+name+"%");
		query.setFirstResult(pageSize*(pageNo-1));
		query.setMaxResults(pageSize);
		return query.getResultList();
	}


	// Used in getting total records
	public int totalRows(){
		TypedQuery<Long> query=em.createQuery(TOTAL_ROWS, Long.class);
		int rows=Integer.parseInt(String.valueOf(getSingle(query)));
		return rows;
	}
}
