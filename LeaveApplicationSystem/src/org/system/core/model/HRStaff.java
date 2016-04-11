package org.system.core.model;

public class HRStaff extends Staff {

	private static final long serialVersionUID = -9135669908423609355L;
	
	private String password;

	public HRStaff(String staffID, String name) {
		super(staffID, name); 
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return super.getName() + " (" + super.getStaffID() + ")" + " - HR Staff";
	}
	
	
}
