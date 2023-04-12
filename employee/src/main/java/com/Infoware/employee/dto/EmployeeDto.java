package com.Infoware.employee.dto;

import com.Infoware.employee.model.EmployeeData;
import com.Infoware.employee.model.EmployeeForm;
import com.Infoware.employee.pojo.EmployeePojo;
import com.Infoware.employee.service.ApiException;
import com.Infoware.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDto {

    @Autowired
    private EmployeeService service;

    public void add(EmployeeForm form) throws ApiException {
        EmployeePojo p = convert(form);
        service.add(p);
    }

    public void delete(int id) {
        service.delete(id);
    }


    public EmployeeData get(int id) throws ApiException {
        EmployeePojo p = service.get(id);
        return convert(p);
    }

    public List<EmployeeData> getAll() {
        List<EmployeePojo> list = service.getAll();
        List<EmployeeData> list2 = new ArrayList<EmployeeData>();
        for (EmployeePojo p : list) {
            list2.add(convert(p));
        }
        return list2;
    }

    public List<EmployeeData> getLimited(int pageNo, String name) {
        List<EmployeePojo> list = service.getLimited(pageNo,name);
        List<EmployeeData> list2 = new ArrayList<EmployeeData>();
        for (EmployeePojo p : list) {
            list2.add(convert(p));
        }
        return list2;
    }


    public void update(int id, EmployeeForm f) throws ApiException {
        EmployeePojo p = convert(f);
        service.update(id, p);
    }

    public int totalEmployees(){
        return service.totalEmployees();
    }



    private static EmployeeData convert(EmployeePojo p) {
        EmployeeData d = new EmployeeData();
        d.setId(p.getId());
        d.setName(p.getName());
        d.setJob_title(p.getJob_title());
        d.setPhone_number(p.getPhone_number());
        d.setEmail(p.getEmail());
        d.setAddress(p.getAddress());
        d.setCity(p.getCity());
        d.setState(p.getState());
        d.setPrimary_emergency_contact(p.getPrimary_emergency_contact());
        d.setPrimary_phone_number(p.getPrimary_phone_number());
        d.setPrimary_relationship(p.getPrimary_relationship());
        d.setSecondary_emergency_contact(p.getSecondary_emergency_contact());
        d.setSecondary_phone_number(p.getSecondary_phone_number());
        d.setSecondary_relationship(p.getSecondary_relationship());
        return d;
    }

    private static EmployeePojo convert(EmployeeForm f) {

        EmployeePojo p = new EmployeePojo();
        p.setName(f.getName());
        p.setJob_title(f.getJob_title());
        p.setPhone_number(f.getPhone_number());
        p.setEmail(f.getEmail());
        p.setAddress(f.getAddress());
        p.setCity(f.getCity());
        p.setState(f.getState());
        p.setPrimary_emergency_contact(f.getPrimary_emergency_contact());
        p.setPrimary_phone_number(f.getPrimary_phone_number());
        p.setPrimary_relationship(f.getPrimary_relationship());
        p.setSecondary_emergency_contact(f.getSecondary_emergency_contact());
        p.setSecondary_phone_number(f.getSecondary_phone_number());
        p.setSecondary_relationship(f.getSecondary_relationship());
        return p;
    }
}
