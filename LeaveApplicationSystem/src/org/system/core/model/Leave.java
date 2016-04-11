package org.system.core.model;

import java.io.Serializable;
import java.util.Date;

import org.system.core.util.DateUtil;

public class Leave implements Cloneable, Serializable {

	private static final long serialVersionUID = 2418921438190716547L;

	private long leaveID;

	private String applicantID;

	private Date startDate;

	private Date endDate;

	private LeaveStatus status;

	private String approverID;

	private String approvalHistory;

	public Leave(Date startDate, Date endDate) {
		new Leave(null, startDate, endDate, null);
	}

	public Leave(String applicantID, Date startDate, Date endDate, String approverID) {
		this.applicantID = applicantID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = LeaveStatus.Pending;
		this.approverID = approverID;
		this.approvalHistory = "";
	}
	
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Leave) {
			Leave that = (Leave) o;
			return this.leaveID == that.leaveID;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Leave ID: " + leaveID + ", Start Date: " + DateUtil.getDateString(startDate) + ", End Date: " + DateUtil.getDateString(endDate);
	}

	public long getLeaveID() {
		return leaveID;
	}

	public void setLeaveID(long leaveID) {
		this.leaveID = leaveID;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public String getApproverID() {
		return approverID;
	}

	public void setApproverID(String approverID) {
		this.approverID = approverID;
	}

	public String getApprovalHistory() {
		return approvalHistory;
	}

	public void setApprovalHistory(String approvalHistory) {
		this.approvalHistory = approvalHistory;
	}

	public enum LeaveStatus {
		Pending, Endorsed, Cancelled, Declined
	}
}
