package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import org.system.core.model.HRStaff;
import org.system.core.model.Staff;
import org.system.core.util.SessionUtil;
import org.system.core.view.MainFrameLeaveApplicationSystem;

public class LeaveApplicationSystemHandler extends AbstractMainHandler<MainFrameLeaveApplicationSystem> {
	private AddStaffHandler addStaffHandler;
	private DeleteStaffHandler deleteStaffHandler;
	private AssignStaffHandler assignStaffHandler;
	private ViewLeaveHandler viewLeaveHandler;
	private ApplyLeaveHandler applyLeaveHandler;
	private HandleLeaveHandler handleLeaveHandler;

	private List<LeaveView> leaveViews;

	public LeaveApplicationSystemHandler(MainFrameLeaveApplicationSystem view) {
		super(view);
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				getView().dispose();
			}
		});
	}

	public void init() {
		super.init();

		this.authrizate(getLoginStaff());

		view.setLoginStaff(getLoginStaff());

		addStaffHandler = new AddStaffHandler(this, view.getInternalFrameAddStaff());
		deleteStaffHandler = new DeleteStaffHandler(this, view.getInternalFrameDeleteStaff());
		assignStaffHandler = new AssignStaffHandler(this, view.getInternalFrameAssignStaff());
		viewLeaveHandler = new ViewLeaveHandler(this, view.getInternalFrameViewLeave());
		applyLeaveHandler = new ApplyLeaveHandler(this, view.getInternalFrameApplyLeave());
		handleLeaveHandler = new HandleLeaveHandler(this, view.getInternalFrameHandleLeave());

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

		leaveViews = new ArrayList<LeaveView>();
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
		for (LeaveView leaveView : leaveViews) {
			leaveView.init();
		}
	}
}