package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.system.base.util.ViewValidator;
import org.system.core.model.Leave;
import org.system.core.model.Leave.LeaveStatus;
import org.system.core.util.DateUtil;
import org.system.core.util.LeaveUtil;
import org.system.core.view.InternalFrameApplyLeave;

public class ApplyLeaveHandler extends AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameApplyLeave> implements LeaveView {

	public ApplyLeaveHandler(LeaveApplicationSystemHandler mainHandler, InternalFrameApplyLeave view) {
		super(mainHandler, view);
	}

	@Override
	public void init() {
		super.init();

		view.setViewTableModels(leaveService.getLeaves(loginStaff.getStaffID()));

		view.addApplyLeaveActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				applyLeave();

			}
		});

		view.addCancelLeaveActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				cancelLeave();
			}
		});
	}

	private void applyLeave() {
		ApplyLeaveValidator validator = new ApplyLeaveValidator(view);
		validator.validate();
		if (validator.hasWarningMessage()) {
			validator.popMessage();
		} else {
			Leave leave = new Leave(loginStaff.getStaffID(), DateUtil.getDate(view.getStartDateStr()),
					DateUtil.getDate(view.getStartDateStr()), loginStaff.getSupervisorID());
			leaveService.createLeave(leave);
			init();

			LeaveUtil.notice(loginStaff, leave, loginStaff.getSupervisorID(), LeaveStatus.Endorsed);
		}
	}

	private void cancelLeave() {
		ApplyLeaveValidator validator = new ApplyLeaveValidator(view);
		validator.validate(LeaveStatus.Cancelled);
		if (validator.hasWarningMessage()) {
			validator.popMessage();
		} else {
			Leave leave = leaveService.getLeave(view.getSelectedLeave().getLeaveID());
			leave.setStatus(LeaveStatus.Cancelled);

			leaveService.updateLeave(leave);
			init();

			LeaveUtil.notice(loginStaff, leave, loginStaff.getSupervisorID(), LeaveStatus.Cancelled);
		}
	}

	class ApplyLeaveValidator extends ViewValidator<InternalFrameApplyLeave> {
		public ApplyLeaveValidator(InternalFrameApplyLeave view) {
			super(view);
		}

		@Override
		public void validate() {
			String startDateStr = view.getStartDateStr();
			String endDateStr = view.getEndDateStr();

			if (startDateStr.isEmpty()) {
				addWarningMessage("Please input Start Date.");
			}

			if (endDateStr.isEmpty()) {
				addWarningMessage("Please input End Date.");
			}

			if (!startDateStr.isEmpty() && !DateUtil.isDate(startDateStr)) {
				addWarningMessage("Please enter a valid Start Date.");
			}

			if (!endDateStr.isEmpty() && !DateUtil.isDate(endDateStr)) {
				addWarningMessage("Please enter a valid End Date.");
			}
		}

		public void validate(LeaveStatus status) {
			Leave selectedLeave = view.getSelectedLeave();

			if (selectedLeave == null) {
				addWarningMessage("Please select a Leave.");
			} else if (selectedLeave.getStatus().equals(status)) {
				addWarningMessage("The Leave is " + status + ".");
			}
		}
	}
}
