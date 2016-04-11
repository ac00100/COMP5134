package org.system.core.util;

import org.system.core.chainofresponsibility.AbstractReporter;
import org.system.core.model.Leave;
import org.system.core.model.Leave.LeaveStatus;
import org.system.core.model.Staff;
import org.system.core.service.StaffService;

public class LeaveUtil {
	private static StaffService staffService = new StaffService();
	
	public static String constructApprovalHistory(Leave leave, Staff staff, LeaveStatus status) {
		String approvalHistory = leave.getApprovalHistory();
		return approvalHistory + ((approvalHistory.isEmpty()) ? "" : ",") +  staff.getName() + " " + status;
	}
	
	public static void notice(Staff reporter, Leave leave, String notifyStaffID, LeaveStatus changedStatus) {
		(new NoticeThread(reporter, leave, notifyStaffID, changedStatus)).start();
	}
	
	private static final class NoticeThread extends Thread {
		Staff reporter;
		Leave leave;
		String notifyStaffID;
		LeaveStatus changedStatus;

		NoticeThread(Staff reporter, Leave leave, String notifyStaffID, LeaveStatus changedStatus) {
			super();
			this.reporter = reporter;
			this.leave = leave;
			this.notifyStaffID = notifyStaffID;
			this.changedStatus = changedStatus;
		}

		@Override
		public void run() {
			Staff notifyStaff = staffService.getStaff(notifyStaffID);

			AbstractReporter absReporter = new AbstractReporter(reporter);
			AbstractReporter nextAbsReporter = (notifyStaff == null) ? null : new AbstractReporter(notifyStaff);
			absReporter.setNextChain(nextAbsReporter);
			absReporter.handleAndNoticeLeave(leave, changedStatus);
		}
	}
}
