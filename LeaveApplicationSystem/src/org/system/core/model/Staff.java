package org.system.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff implements Cloneable, Serializable {

	private static final long serialVersionUID = 3427049276543157190L;

	private String staffID;

	private String name;

	private String supervisorID;

	private List<Staff> subordinates = new ArrayList<Staff>();

	public Staff(String staffID, String name) {
		this(staffID, name, null);
	}

	public Staff(String staffID, String name, String supervisorID) {
		this.staffID = staffID;
		this.name = name;
		this.supervisorID = supervisorID;
	}

	public List<Staff> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Staff> subordinates) {
		this.subordinates = subordinates;
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
		if (o instanceof Staff) {
			Staff that = (Staff) o;
			return this.staffID.equals(that.staffID);
		}
		return false;
	}

	@Override
	public String toString() {
		String str = name + " (" + staffID + ")";
		return (supervisorID == null) ? str + " - CEO" : str + " - Supervisor ID: (" + supervisorID + ")";
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}
}
