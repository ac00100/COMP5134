package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import org.system.core.model.HRStaff;
import org.system.core.model.Staff;
import org.system.core.util.ListUtil;
import org.system.core.util.SessionUtil;
import org.system.core.view.MainFrameLeaveApplicationSystem;

public class LeaveApplicationSystemHandler extends AbstractMainHandler<MainFrameLeaveApplicationSystem> {
	private FunAddStaffHandler addStaffHandler;
	private FunDeleteStaffHandler deleteStaffHandler;
	private FunAssignStaffHandler assignStaffHandler;
	private FunViewLeaveHandler viewLeaveHandler;
	private FunApplyLeaveHandler applyLeaveHandler;
	private FunHandleLeaveHandler handleLeaveHandler;

	private List<InitView> leaveViews;

	public LeaveApplicationSystemHandler(MainFrameLeaveApplicationSystem view) {
		super(view);
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ListUtil.removeTargetObjectFromList(SessionUtil.getUserHandlers(), getThis());
				getView().dispose();
			}
		});
	}

	public void init() {
		super.init();

		Staff loginStaff = getLoginStaff();
		if (loginStaff == null) {
			return;
		}

		this.authrizate(loginStaff);

		view.setLoginStaff(getLoginStaff());

		addStaffHandler = new FunAddStaffHandler(this, view.getInternalFrameAddStaff());
		deleteStaffHandler = new FunDeleteStaffHandler(this, view.getInternalFrameDeleteStaff());
		assignStaffHandler = new FunAssignStaffHandler(this, view.getInternalFrameAssignStaff());
		viewLeaveHandler = new FunViewLeaveHandler(this, view.getInternalFrameViewLeave());
		applyLeaveHandler = new FunApplyLeaveHandler(this, view.getInternalFrameApplyLeave());
		handleLeaveHandler = new FunHandleLeaveHandler(this, view.getInternalFrameHandleLeave());

		view.addAddNewStaffMenuActionListener(getShowViewActionListener(addStaffHandler));
		view.addDeleteStaffMenuActionListener(getShowViewActionListener(deleteStaffHandler));
		view.addAssignStaffMenuActionListener(getShowViewActionListener(assignStaffHandler));
		view.addViewForLeaveMenuActionListener(getShowViewActionListener(viewLeaveHandler));
		view.addApplyForLeaveMenuActionListener(getShowViewActionListener(applyLeaveHandler));
		view.addHandleApplicationMenuActionListener(getShowViewActionListener(handleLeaveHandler));

		view.addLogoutActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				logout();
			}
		});

		leaveViews = new ArrayList<InitView>();
		leaveViews.add(viewLeaveHandler);
		leaveViews.add(applyLeaveHandler);
		leaveViews.add(handleLeaveHandler);

		SessionUtil.addUserHandlers(this);
	}

	private void authrizate(Staff loginStaff) {
		if (loginStaff instanceof HRStaff) {
			view.getMntmAddNewStaff().setEnabled(true);
			view.getMntmDeleteStaff().setEnabled(true);
			view.getMntmAssignStaff().setEnabled(true);
			view.getMntmViewForLeave().setEnabled(true);
		} else {
			if (loginStaff.getSupervisorID() != null) {
				view.getMntmApplyForLeave().setEnabled(true);
			}
			if (!loginStaff.getSubordinates().isEmpty()) {
				view.getMntmHandleApplication().setEnabled(true);
			}
		}
	}

	private ActionListener getShowViewActionListener(final AbstractFunctionHandler<?, ?> handler) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				view.hiddenInternalView();
				handler.showView();
			}
		};
	}

	private void logout() {
		init();
	}

	public void initInternalLeaveView() {
		for (InitView leaveView : leaveViews) {
			leaveView.init();
		}
	}

	public LeaveApplicationSystemHandler getThis() {
		return this;
	}
}