package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.system.base.util.ViewValidator;
import org.system.core.model.Leave;
import org.system.core.model.Leave.LeaveStatus;
import org.system.core.util.LeaveUtil;
import org.system.core.view.InternalFrameHandleLeave;

public class FunHandleLeaveHandler extends AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameHandleLeave> implements InitView {

	public FunHandleLeaveHandler(LeaveApplicationSystemHandler mainHandler, InternalFrameHandleLeave view) {
		super(mainHandler, view);
	}

	@Override
	public void init() {
		super.init();

		view.setViewTableModels(leaveService.getApproverLeaves(getLoginStaff().getStaffID()));

		view.addApproveLeaveActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				approveLeave();
			}
		});

		view.addDeclineLeaveActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				declineLeave();
			}
		});
	}

	private void approveLeave() {
		HandleLeaveValidator validator = new HandleLeaveValidator(view);
		validator.validate();
		if (validator.hasWarningMessage()) {
			validator.popMessage();
		} else {
			Leave leave = leaveService.getLeave(view.getSelectedLeave().getLeaveID());
			if (getLoginStaff().getSupervisorID() == null) {
				leave.setStatus(LeaveStatus.Endorsed);
			} else {
				leave.setApproverID(getLoginStaff().getSupervisorID());
			}
			leave.setApprovalHistory(LeaveUtil.constructApprovalHistory(leave, getLoginStaff(), LeaveStatus.Endorsed));

			leaveService.updateLeave(leave);
			init();
			
			LeaveUtil.notice(getLoginStaff(), leave, getLoginStaff().getSupervisorID(), LeaveStatus.Endorsed);
		}
	}

	private void declineLeave() {
		HandleLeaveValidator validator = new HandleLeaveValidator(view);
		validator.validate();
		if (validator.hasWarningMessage()) {
			validator.popMessage();
		} else {
			Leave leave = leaveService.getLeave(view.getSelectedLeave().getLeaveID());
			LeaveStatus status = LeaveStatus.Declined;
			
			leave.setStatus(status);
			leave.setApprovalHistory(LeaveUtil.constructApprovalHistory(leave, getLoginStaff(), status));
			
			leaveService.updateLeave(leave);
			init();

			LeaveUtil.notice(getLoginStaff(), leave, leave.getApplicantID(), LeaveStatus.Declined);
		}
	}

	class HandleLeaveValidator extends ViewValidator<InternalFrameHandleLeave> {
		public HandleLeaveValidator(InternalFrameHandleLeave view) {
			super(view);
		}

		@Override
		public void validate() {
			Leave selectedLeave = view.getSelectedLeave();

			if (selectedLeave == null) {
				addWarningMessage("Please select a Leave.");
			} else if (selectedLeave.getStatus() != LeaveStatus.Pending) {
				addWarningMessage("The Leave is " + selectedLeave.getStatus() + ".");
			}
		}
	}
}
