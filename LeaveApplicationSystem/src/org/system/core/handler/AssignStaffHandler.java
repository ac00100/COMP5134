package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.system.base.util.ViewValidator;
import org.system.core.model.Staff;
import org.system.core.view.InternalFrameAssignStaff;

public class AssignStaffHandler extends
		AbstractFunctionHandler<LeaveApplicationSystemHandler, InternalFrameAssignStaff> {

	public AssignStaffHandler(LeaveApplicationSystemHandler mainHandler, InternalFrameAssignStaff view) {
		super(mainHandler, view);
	}

	@Override
	public void init() {
		super.init();

		view.addAssignStaffActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				assignStaff();
			}
		});
	}

	private void assignStaff() {
		AssignStaffValidator validator = new AssignStaffValidator(view);
		validator.validate();
		if (validator.hasWarningMessage()) {
			validator.popMessage();
		} else {
			staffService.assignStafftoSupervisor(view.getStaffID(), view.getSupervisorID());
			init();
		}
	}

	class AssignStaffValidator extends ViewValidator<InternalFrameAssignStaff> {

		public AssignStaffValidator(InternalFrameAssignStaff view) {
			super(view);
		}

		@Override
		public void validate() {
			String supervisorID = view.getSupervisorID();
			String subordinateID = view.getStaffID();

			if (subordinateID == null) {
				addWarningMessage("Please select Supervisor assign to.");
			}

			if (subordinateID == null) {
				addWarningMessage("Please select Subordinate to be assigned.");
			}

			if (supervisorID != null && subordinateID != null) {
				Staff supervisor = staffService.getStaff(supervisorID);
				Staff subordinate = staffService.getStaff(subordinateID);

				if (supervisor.equals(subordinate)) {
					addWarningMessage("Supervisor and Subordinate cannot be the same.");
				}

				if (isSupervisorHasSupervisorX(supervisor.getStaffID(), subordinate.getStaffID())) {
					addWarningMessage("Subordinate cannot be assigned. Because " + subordinate.getName() + "("
							+ subordinate.getStaffID() + ") is more high-level than " + supervisor.getName() + "("
							+ supervisor.getStaffID() + ").");
				}
			}

		}

		private boolean isSupervisorHasSupervisorX(String supervisorID, String xID) {
			Staff supervisor = staffService.getStaff(supervisorID);

			if (supervisor.getSupervisorID() == null) {
				return false;
			} else {
				return supervisor.getSupervisorID().equals(xID)
						|| isSupervisorHasSupervisorX(supervisor.getSupervisorID(), xID);
			}
		}
	}
}
