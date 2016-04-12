package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.system.base.util.ViewValidator;
import org.system.core.model.Staff;
import org.system.core.view.InternalFrameAddStaff;

public class FunAddStaffHandler extends AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameAddStaff> {

	public FunAddStaffHandler(LeaveApplicationSystemHandler handler, InternalFrameAddStaff view) {
		super(handler, view);
	}

	public void init() {
		super.init();

		view.addCreateStaffActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				addNewStaff();
			}
		});
	}

	private void addNewStaff() {
		AddStaffValidator validator = new AddStaffValidator(view);
		validator.validate();
		if (validator.hasWarningMessage()) {
			validator.popMessage();
		} else {
			staffService.createStaff(view.getNewStaff());
			init();
		}
	}

	class AddStaffValidator extends ViewValidator<InternalFrameAddStaff> {

		public AddStaffValidator(InternalFrameAddStaff view) {
			super(view);
		}

		@Override
		public void validate() {
			Staff staff = view.getNewStaff();

			if (staff.getStaffID().isEmpty()) {
				addWarningMessage("Please input the Staff ID.");
			}

			if (staff.getName().isEmpty()) {
				addWarningMessage("Please input the Staff Name.");
			}

			if (!staff.getStaffID().isEmpty() && staffService.isIdExist(staff.getStaffID())) {
				addWarningMessage("Staff ID has already been used.");
			}
		}
	}
}
