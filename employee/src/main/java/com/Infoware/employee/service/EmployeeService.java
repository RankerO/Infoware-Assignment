package com.Infoware.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Infoware.employee.dao.EmployeeDao;
import com.Infoware.employee.pojo.EmployeePojo;
import com.Infoware.employee.util.StringUtil;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	@Transactional(rollbackOn = ApiException.class)
	public void add(EmployeePojo p) throws ApiException {
		normalize(p);
		if(StringUtil.isEmpty(p.getName())) {
			throw new ApiException("name cannot be empty");
		}
		dao.insert(p);
	}

	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}

	@Transactional(rollbackOn = ApiException.class)
	public EmployeePojo get(int id) throws ApiException {
		return getCheck(id);
	}

	@Transactional
	public List<EmployeePojo> getAll() {
		return dao.selectAll();
	}

	@Transactional
	public List<EmployeePojo> getLimited(int pageNo, String name){
		return dao.selectLimited(pageNo,name);
	}

	@Transactional(rollbackOn  = ApiException.class)
	public void update(int id, EmployeePojo p) throws ApiException {
		normalize(p);
		EmployeePojo updatedPojo = getCheck(id);

		updatedPojo.setName(p.getName());
		updatedPojo.setJob_title(p.getJob_title());
		updatedPojo.setPhone_number(p.getPhone_number());
		updatedPojo.setEmail(p.getEmail());
		updatedPojo.setAddress(p.getAddress());
		updatedPojo.setCity(p.getCity());
		updatedPojo.setState(p.getState());
		updatedPojo.setPrimary_emergency_contact(p.getPrimary_emergency_contact());
		updatedPojo.setPrimary_phone_number(p.getPrimary_phone_number());
		updatedPojo.setPrimary_relationship(p.getPrimary_relationship());
		updatedPojo.setSecondary_emergency_contact(p.getSecondary_emergency_contact());
		updatedPojo.setSecondary_phone_number(p.getSecondary_phone_number());
		updatedPojo.setSecondary_relationship(p.getSecondary_relationship());

	}

	@Transactional
	public EmployeePojo getCheck(int id) throws ApiException {
		EmployeePojo p = dao.select(id);
		if (p == null) {
			throw new ApiException("Employee with given ID does not exit, id: " + id);
		}
		return p;
	}

	@Transactional
	public int totalEmployees(){
		return dao.totalRows();
	}

	protected static void normalize(EmployeePojo p) {
		p.setName(StringUtil.toLowerCase(p.getName()));
		p.setJob_title(StringUtil.toLowerCase(p.getJob_title()));
		p.setEmail(StringUtil.toLowerCase(p.getEmail()));
		p.setAddress(StringUtil.toLowerCase(p.getAddress()));
		p.setCity(StringUtil.toLowerCase(p.getCity()));
		p.setState(StringUtil.toLowerCase(p.getState()));
		p.setPrimary_emergency_contact(StringUtil.toLowerCase(p.getPrimary_emergency_contact()));
		p.setPrimary_relationship(StringUtil.toLowerCase(p.getPrimary_relationship()));
		p.setSecondary_emergency_contact(StringUtil.toLowerCase(p.getSecondary_emergency_contact()));
		p.setSecondary_relationship(StringUtil.toLowerCase(p.getSecondary_relationship()));
	}
}
