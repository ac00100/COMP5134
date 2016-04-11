package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.system.base.util.ViewValidator;
import org.system.core.view.InternalFrameDeleteStaff;

public class DeleteStaffHandler extends AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameDeleteStaff> {

	public DeleteStaffHandler(LeaveApplicationSystemHandler mainHandler, InternalFrameDeleteStaff view) {
		super(mainHandler, view);
	}

	@Override
	public void init() {
		super.init();

		view.addDeleteStaffActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				deleteStaff();
			}
		});
	}

	private void deleteStaff() {
		DeleteStaffValidator validator = new DeleteStaffValidator(view);
		validator.validate();
		if (validator.hasWarningMessage()) {
			validator.popMessage();
		} else {
			staffService.deleteStaff(view.getDeleteStaffID());
			init();
		}
	}

	class DeleteStaffValidator extends ViewValidator<InternalFrameDeleteStaff> {

		public DeleteStaffValidator(InternalFrameDeleteStaff view) {
			super(view);
		}

		@Override
		public void validate() {
			String staffID = view.getDeleteStaffID();

			if (staffID == null) {
				addWarningMessage("Please select a Staff to delete.");
				return;
			}

			if (!staffService.getStaff(staffID).getSubordinates().isEmpty()) {
				addWarningMessage("Supervisor who has subordinate(s) cannot be deleted.");
			}
		}
	}
}
