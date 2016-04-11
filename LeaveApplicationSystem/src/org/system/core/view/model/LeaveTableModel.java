package org.system.core.view.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.system.core.model.Leave;
import org.system.core.model.Staff;
import org.system.core.service.StaffService;
import org.system.core.util.DateUtil;

public class LeaveTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 2311543225641574988L;
	private List<Staff> staffs;

	public LeaveTableModel(List<Leave> leaves) {
		super();
		staffs = (new StaffService()).getStaffs();

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("Applicant");
		columnNames.add("Start Date");
		columnNames.add("End Date");
		columnNames.add("Status");
		columnNames.add("Approver");
		columnNames.add("Approval History");

		for (String columnName : columnNames) {
			super.addColumn(columnName);
		}

		for (Leave leave : leaves) {
			Vector<String> rowData = new Vector<String>();
			rowData.add("" + leave.getLeaveID());
			rowData.add(getStaff(leave.getApplicantID()).getName());
			rowData.add(DateUtil.getDateString(leave.getStartDate()));
			rowData.add(DateUtil.getDateString(leave.getStartDate()));
			rowData.add(leave.getStatus().toString());
			rowData.add((leave.getApproverID() == null) ? "" : getStaff(leave.getApproverID()).getName());
			rowData.add(leave.getApprovalHistory());
			super.addRow(rowData);
		}
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	private Staff getStaff(String staffID) {
		for (Staff staff : staffs) {
			if (staff.getStaffID().equals(staffID)) {
				return staff;
			}
		}
		return null;
	}

}
