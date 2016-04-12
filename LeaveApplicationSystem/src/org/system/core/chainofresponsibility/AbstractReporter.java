package org.system.core.chainofresponsibility;

import javax.swing.JOptionPane;

import org.system.core.handler.LeaveApplicationSystemHandler;
import org.system.core.model.Leave;
import org.system.core.model.Leave.LeaveStatus;
import org.system.core.model.Staff;
import org.system.core.service.LeaveService;
import org.system.core.service.StaffService;
import org.system.core.util.LeaveUtil;
import org.system.core.util.SessionUtil;

public class AbstractReporter extends Staff implements ReporterChain {
	private static final long serialVersionUID = 2396511744697074925L;
	private StaffService staffService;
	private LeaveService leaveService;
	private LeaveApplicationSystemHandler handler;
	private LeaveApplicationSystemHandler chainHandler;

	public AbstractReporter(Staff staff) {
		super(staff.getStaffID(), staff.getName(), staff.getSupervisorID());
		staffService = new StaffService();
		leaveService = new LeaveService();
		this.handler = SessionUtil.getUserHandler(staff.getStaffID());
	}

	private AbstractReporter chain;

	@Override
	public void setNextChain(AbstractReporter nextChain) {
		if (nextChain == null) {
			return;
		}
		this.chain = nextChain;
		this.chainHandler = SessionUtil.getUserHandler(chain.getStaffID());
	}

	@Override
	public void handleAndNoticeLeave(Leave leave, LeaveStatus changedStatus) {
		if (handler == null) {
			return;
		}

		// if request to cancel
		if (changedStatus == LeaveStatus.Cancelled) {
			leave.setStatus(changedStatus);
			leave.setApprovalHistory(LeaveUtil.constructApprovalHistory(leave, this, changedStatus));
			leaveService.updateLeave(leave);
			replyNotice(leave);
		}
		// else if request to endorse
		else if (changedStatus == LeaveStatus.Endorsed) {
			if (chain != null) {
				
				LeaveStatus chainStatus = requestEndorse(leave);
				if (chainStatus == null) {
					return;
				}

				if (chainStatus == LeaveStatus.Endorsed) {
					
					if (chain.getSupervisorID() != null) {
						LeaveUtil.notice(chain, leave, chain.getSupervisorID(), LeaveStatus.Endorsed);
					}
					else {
						leave.setStatus(LeaveStatus.Endorsed);
						leaveService.updateLeave(leave);
						replyNotice(chain, leave, leave.getApplicantID());
					}
				} else if (chainStatus == LeaveStatus.Declined) {
					updateLeave(leave, LeaveStatus.Declined);
					replyNotice(chain, leave, leave.getApplicantID());
				}
			} else {
				leave.setStatus(LeaveStatus.Endorsed);
				leaveService.updateLeave(leave);
				replyNotice(this, leave, leave.getApplicantID());
			}
		} 
		// else if request to decline
		else if (changedStatus == LeaveStatus.Declined) {
			leave.setApprovalHistory(LeaveUtil.constructApprovalHistory(leave, this, LeaveStatus.Declined));
			updateLeave(leave, LeaveStatus.Declined);
			replyNotice(this, leave, leave.getApplicantID());
		}
	}
	
	private void updateLeave(Leave leave, LeaveStatus status) {
		leave.setStatus(status);
		leaveService.updateLeave(leave);
	}
		
	private LeaveStatus requestEndorse(Leave leave) {
		if (chainHandler == null) {
			return null;
		}

		LeaveStatus status[] = new LeaveStatus[] { LeaveStatus.Endorsed, LeaveStatus.Declined };
		
		int i = JOptionPane.showOptionDialog(chainHandler.getView(),
				this.getName() + " request for approval Leave (" + leave +") \n"
						+ "requested by Applicant (" + staffService.getStaff(leave.getApplicantID()).getName() + ").",
						"Request Notice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, status, null);
		LeaveStatus chainStatus = (i >= 0) ? status[i] : null;
		
		if (chainStatus != null) {
			leave.setApprovalHistory(LeaveUtil.constructApprovalHistory(leave, chain, chainStatus));
			leaveService.updateLeave(leave);
			
			LeaveApplicationSystemHandler applicantHandler = SessionUtil.getUserHandler(leave.getApplicantID());
			if (applicantHandler != null) {
				applicantHandler.initInternalLeaveView();
			}
		}
		
		handler.initInternalLeaveView();
		chainHandler.initInternalLeaveView();
		
		return chainStatus;
	}

	
	private void replyNotice(Leave leave) {
		replyNotice(this, leave, chain);
	}
	
	private void replyNotice(AbstractReporter reporter, Leave leave, String applicantID) {
		replyNotice(reporter, leave, new AbstractReporter(staffService.getStaff(applicantID)));
	}

	private void replyNotice(AbstractReporter reporter, Leave leave, AbstractReporter receiver) {
		LeaveApplicationSystemHandler reporterHandler = SessionUtil.getUserHandler(reporter.getStaffID());
		LeaveApplicationSystemHandler receiverHandler = SessionUtil.getUserHandler(receiver.getStaffID());
		
		String subject =  (receiver.getStaffID().equals(leave.getApplicantID()) ? "Your" : "The");
		JOptionPane.showMessageDialog(receiverHandler.getView(), subject + " Leave Application (" + leave +") \n"
				+ "was " + leave.getStatus() + " by " + reporter.getName() + ".",
				leave.getStatus() + "Notice", JOptionPane.PLAIN_MESSAGE,null);
		
		reporterHandler.initInternalLeaveView();
		receiverHandler.initInternalLeaveView();
	}
}
