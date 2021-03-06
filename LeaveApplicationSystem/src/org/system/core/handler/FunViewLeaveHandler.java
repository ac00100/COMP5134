package org.system.core.handler;

import org.system.core.view.InternalFrameViewLeave;

public class FunViewLeaveHandler extends AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameViewLeave> implements InitView {

	public FunViewLeaveHandler(LeaveApplicationSystemHandler mainHandler, InternalFrameViewLeave view) {
		super(mainHandler, view);
	}

	@Override
	public void init() {
		super.init();

		view.setViewTableModels(leaveService.getLeaves());

	}
}
