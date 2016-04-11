package org.system.core.chainofresponsibility;

import org.system.core.model.Leave;
import org.system.core.model.Leave.LeaveStatus;

public interface ReporterChain {
	public void setNextChain(AbstractReporter nextChain);

	public void handleAndNoticeLeave(Leave leave, LeaveStatus changedStatus);
}
