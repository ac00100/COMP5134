package org.system.core.handler;

import org.system.core.model.Staff;
import org.system.core.service.LeaveService;
import org.system.core.service.StaffService;
import org.system.core.view.AbstractFunctionalFrame;

public abstract class AbstractFunctionHandler<H extends AbstractMainHandler<?>, V extends AbstractFunctionalFrame> {
	protected H mainHandler;
	protected V view;
	protected StaffService staffService;
	protected LeaveService leaveService;

	public AbstractFunctionHandler(H mainHandler, V view) {
		this.mainHandler = mainHandler;
		this.view = (V) view;
		staffService = new StaffService();
		leaveService = new LeaveService();
	}

	public void init() {
		view.setViewStaffs(staffService.getStaffs());
		view.resetView();
		view.repaint();
		mainHandler.repaintView();
	}

	public void showView() {
		init();
		view.displayView();
	}

	public Staff getLoginStaff() {
		return mainHandler.getLoginStaff();
	}

	public void setLoginStaff(Staff loginStaff) {
		mainHandler.setLoginStaff(loginStaff);
	}
}
