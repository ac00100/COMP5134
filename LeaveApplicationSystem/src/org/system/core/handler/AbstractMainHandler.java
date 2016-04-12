package org.system.core.handler;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.system.core.model.HRStaff;
import org.system.core.model.Staff;
import org.system.core.service.StaffService;
import org.system.core.view.AbstractMainFrame;

public abstract class AbstractMainHandler<V extends AbstractMainFrame> {
	protected V view;
	protected StaffService staffService;

	private Staff loginStaff;

	public AbstractMainHandler(V view) {
		this.view = view;
		this.staffService = new StaffService();
	}

	public void init() {
		view.resetView();
		view.repaint();
		view.displayView();
		staffLogout();
	}

	public void repaintView() {
		view.repaint();
	}

	public Staff getLoginStaff() {
		return (loginStaff != null) ? loginStaff : exeuteVirtualLogin();
	}

	public void setLoginStaff(Staff loginStaff) {
		this.loginStaff = loginStaff;
	}
	
	public void refreshLoginStaff() {
		if (loginStaff instanceof HRStaff) {
			return;
		}
		loginStaff = staffService.getStaff(loginStaff.getStaffID());
	}

	public void staffLogout() {
		loginStaff = null;
	}

	private Staff exeuteVirtualLogin() {
		List<Object> staffs = new ArrayList<Object>();
		for (Staff staff : staffService.getStaffs()) {
			staffs.add(staff);
		}
		staffs.add(0, new HRStaff("HR_ADMIN", "HR Admin"));

		Object[] staffArray = new Object[staffs.size()];
		staffArray = staffs.toArray(staffArray);
		if (staffArray.length > 0) {
			loginStaff = (Staff) JOptionPane.showInputDialog(view, "Please select Staff to login.", "User Login",
					JOptionPane.QUESTION_MESSAGE, null, staffArray, staffArray[0]);
		}

		if (loginStaff == null) {
			this.view.dispose();
		}

		return (Staff) loginStaff;
	}

	public V getView() {
		return view;
	}
}
