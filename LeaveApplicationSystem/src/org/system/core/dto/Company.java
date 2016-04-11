package org.system.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.system.core.model.Leave;
import org.system.core.model.Staff;

public class Company implements Serializable {

	private static final long serialVersionUID = -6816301319652475559L;
	
	private List<Staff> staffs = new ArrayList<Staff>();

	private List<Leave> leaves = new ArrayList<Leave>();

	public Company() {
		super();
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

}
