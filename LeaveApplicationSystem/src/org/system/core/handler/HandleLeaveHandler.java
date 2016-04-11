package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.system.base.util.ViewValidator;
import org.system.core.model.Leave;
import org.system.core.model.Leave.LeaveStatus;
import org.system.core.util.LeaveUtil;
import org.system.core.view.InternalFrameHandleLeave;

public class HandleLeaveHandler extends AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameHandleLeave> implements LeaveView {

	public HandleLeaveHandler(LeaveApplicationSystemHandler mainHandler, InternalFrameHandleLeave view) {
		super(mainHandler, view);
	}

	@Override
	public void init() {
		super.init();

		view.setViewTableModels(leaveService.getApproverLeaves(loginStaff.getStaffID()));

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
			if (loginStaff.getSupervisorID() == null) {
				leave.setStatus(LeaveStatus.Endorsed);
			} else {
				leave.setApproverID(loginStaff.getSupervisorID());
			}
			leave.setApprovalHistory(LeaveUtil.constructApprovalHistory(leave, loginStaff, LeaveStatus.Endorsed));

			leaveService.updateLeave(leave);
			init();
			
			LeaveUtil.notice(loginStaff, leave, loginStaff.getSupervisorID(), LeaveStatus.Endorsed);
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
			leave.setApprovalHistory(LeaveUtil.constructApprovalHistory(leave, loginStaff, status));
			
			leaveService.updateLeave(leave);
			init();

			LeaveUtil.notice(loginStaff, leave, leave.getApplicantID(), LeaveStatus.Declined);
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
