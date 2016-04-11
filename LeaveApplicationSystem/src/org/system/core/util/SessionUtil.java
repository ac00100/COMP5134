package org.system.core.util;

import java.util.ArrayList;
import java.util.List;

import org.system.core.dto.Company;
import org.system.core.handler.LeaveApplicationSystemHandler;

public class SessionUtil {
	private static Company company;

	private static List<LeaveApplicationSystemHandler> userHandlers;
	
	public static void init() {
		SessionUtil.company = new Company();
		SessionUtil.userHandlers = new ArrayList<LeaveApplicationSystemHandler>();
	}
	
	public static Company getCompany() {
		if (SessionUtil.company == null) {
			SessionUtil.company = new Company();
		}
		return SessionUtil.company;
	}

	public static void setCompany(Company company) {
			SessionUtil.company = company;
	}

	public static List<LeaveApplicationSystemHandler> getUserHandlers() {
		return userHandlers;
	}

	public static void addUserHandlers(LeaveApplicationSystemHandler leaveApplicationSystemHandler) {
		if (SessionUtil.userHandlers == null) {
			SessionUtil.userHandlers = new ArrayList<LeaveApplicationSystemHandler>();
		}
		SessionUtil.userHandlers.add(leaveApplicationSystemHandler);
	}
	
	public static LeaveApplicationSystemHandler getUserHandler(String loginStaffID) {
		for (LeaveApplicationSystemHandler userHandler : SessionUtil.userHandlers) {
			if (loginStaffID.equals(userHandler.getLoginStaff().getStaffID())) {
				return userHandler;
			}
		}
		return null;
	}

}
