package org.system;

import org.system.core.model.Leave;
import org.system.core.model.Leave.LeaveStatus;
import org.system.core.service.LeaveService;

public class Test {

	public Test() {

	}

	public static void main(String[] args) {
		LeaveService s = new LeaveService();
		Leave leave = s.getLeave(19);
		leave.setApprovalHistory("Kenny " + LeaveStatus.Endorsed + "," + "Candy " + LeaveStatus.Endorsed + ","
 + "Benny " + LeaveStatus.Endorsed);
		s.updateLeave(leave);
	}

}
