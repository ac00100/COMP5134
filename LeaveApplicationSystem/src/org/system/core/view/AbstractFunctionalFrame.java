package org.system.core.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import org.system.core.model.Staff;

@SuppressWarnings("serial")
public abstract class AbstractFunctionalFrame extends JInternalFrame {

	private List<Staff> viewStaffs;

	public AbstractFunctionalFrame(String title) {
		super(title);
		setClosable(true);
		setVisible(false);
		initComponents();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	protected abstract void initComponents();

	public abstract void resetView();

	public void displayView() {
		this.setVisible(true);
	}

	public List<Staff> getViewStaffs() {
		return viewStaffs;
	}

	public void setViewStaffs(List<Staff> viewStaffs) {
		this.viewStaffs = viewStaffs;
	}
}
