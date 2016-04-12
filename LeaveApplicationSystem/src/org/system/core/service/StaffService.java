package org.system.core.service;

import java.util.List;

import org.system.base.service.AbstractService;
import org.system.core.dto.Company;
import org.system.core.model.Staff;
import org.system.core.util.ListUtil;

public class StaffService extends AbstractService {

	public boolean isIdExist(String staffID) {
		Company company = super.loadCompany();
		for (Staff staff : company.getStaffs()) {
			if (staff.getStaffID().equals(staffID)) {
				return true;
			}
		}
		return false;
	}

	public Staff getStaff(String staffID) {
		Company company = super.loadCompany();
		return getStaff(staffID, company);
	}

	private Staff getStaff(String staffID, Company company) {
		for (Staff staff : company.getStaffs()) {
			if (staff.getStaffID().equals(staffID)) {
				return staff;
			}
		}
		return null;
	}

	public List<Staff> getStaffs() {
		return loadCompany().getStaffs();
	}

	public void createStaff(Staff newStaff) {
		Company company = super.loadCompany();
		List<Staff> staffs = company.getStaffs();

		// supervisor can be null if new staff is CEO
		Staff supervisor;
		if (newStaff.getSupervisorID() == null) {
			supervisor = null;
		} else {
			supervisor = getStaff(newStaff.getSupervisorID(), company);
			supervisor.getSubordinates().add(newStaff);
		}

		staffs.add(newStaff);

		super.saveCompany(company);
	}
	
	public void assignStafftoSupervisor(String staffID, String supervisorID) {
		Company company = super.loadCompany();
		Staff staff = getStaff(staffID, company);
		Staff supervisor = getStaff(supervisorID, company);

		ListUtil.removeTargetObjectFromList(getStaff(staffID, company).getSubordinates(), staff);
		staff.setSupervisorID(supervisorID);
		supervisor.getSubordinates().add(staff);

		super.saveCompany(company);
	}

	public void deleteStaff(String deleteStaffID) {
		Company company = super.loadCompany();
		Staff deleteStaff = getStaff(deleteStaffID, company);
		Staff supervisor = getStaff(deleteStaff.getSupervisorID(), company);
		
		if (supervisor != null) {
			ListUtil.removeTargetObjectFromList(supervisor.getSubordinates(), deleteStaff);
		}
		ListUtil.removeTargetObjectFromList(company.getStaffs(), deleteStaff);
		
		super.saveCompany(company);
	}
}
