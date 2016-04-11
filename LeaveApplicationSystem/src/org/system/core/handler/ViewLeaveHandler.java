package org.system.core.handler;

import org.system.core.view.InternalFrameViewLeave;

public class ViewLeaveHandler extends AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameViewLeave> implements LeaveView {

	public ViewLeaveHandler(LeaveApplicationSystemHandler mainHandler, InternalFrameViewLeave view) {
		super(mainHandler, view);
	}

	@Override
	public void init() {
		super.init();

		view.setViewTableModels(leaveService.getLeaves());

	}
}
