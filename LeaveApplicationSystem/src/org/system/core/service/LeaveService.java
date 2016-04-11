package org.system.core.service;

import java.util.ArrayList;
import java.util.List;

import org.system.core.dto.Company;
import org.system.core.model.Leave;

public class LeaveService extends AbstractService {

	public Leave getLeave(long LeaveID) {
		Company company = super.loadCompany();
		return getLeave(LeaveID, company);
	}

	private Leave getLeave(long leaveID, Company company) {
		for (Leave leave : company.getLeaves()) {
			if (leave.getLeaveID() == leaveID) {
				return leave;
			}
		}
		return null;
	}

	public List<Leave> getLeaves() {
		Company company = super.loadCompany();
		return company.getLeaves();
	}

	public List<Leave> getLeaves(String staffID) {
		List<Leave> leaves = new ArrayList<Leave>();
		Company company = super.loadCompany();

		for (Leave leave : company.getLeaves()) {
			if (staffID.equals(leave.getApplicantID())) {
				leaves.add(leave);
			}
		}
		return leaves;
	}

	public List<Leave> getApproverLeaves(String approverID) {
		List<Leave> leaves = new ArrayList<Leave>();
		Company company = super.loadCompany();

		for (Leave leave : company.getLeaves()) {
			if (approverID.equals(leave.getApproverID())) {
				leaves.add(leave);
			}
		}
		return leaves;
	}

	public void createLeave(Leave newLeave) {
		Company company = super.loadCompany();
		List<Leave> leaves = company.getLeaves();

		newLeave.setLeaveID(leaves.size() + 1);
		leaves.add(newLeave);

		super.saveCompany(company);
	}

	public void updateLeave(Leave leave) {
		Company company = super.loadCompany();
		List<Leave> leaves = company.getLeaves();

		for (int i = 0; i < leaves.size(); i++) {
			if (leaves.get(i).getLeaveID() == leave.getLeaveID()) {
				company.getLeaves().set(i, leave);
			}
		}

		super.saveCompany(company);
	}
}
