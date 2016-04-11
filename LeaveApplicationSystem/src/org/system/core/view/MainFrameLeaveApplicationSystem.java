package org.system.core.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.system.core.model.Staff;
import org.system.core.util.ButtonUtil;

@SuppressWarnings("serial")
public class MainFrameLeaveApplicationSystem extends AbstractMainFrame {
	private static String title = "Leave Application System";
	private JMenu mnNewMenu;

	private JMenuItem mntmAddNewStaff;
	private JMenuItem mntmDeleteStaff;
	private JMenuItem mntmAssignStaff;
	private JMenuItem mntmViewForLeave;
	private JMenuItem mntmApplyForLeave;
	private JMenuItem mntmHandleApplication;
	private JMenuItem mntmLogout;

	private InternalFrameAddStaff internalFrameAddStaff;
	private InternalFrameDeleteStaff internalFrameDeleteStaff;
	private InternalFrameAssignStaff internalFrameAssignStaff;
	private InternalFrameViewLeave internalFrameViewLeave;
	private InternalFrameApplyLeave internalFrameApplyLeave;
	private InternalFrameHandleLeave internalFrameHandleLeave;
	private JDesktopPane desktopPane;

	public MainFrameLeaveApplicationSystem() {
		super(title);
		this.setBounds(100, 100, 1024, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	protected void initComponents() {
		mnNewMenu = new JMenu("Leave Application System");
		mntmAddNewStaff = new JMenuItem("Add New Staff");
		mntmDeleteStaff = new JMenuItem("Delete Staff");
		mntmAssignStaff = new JMenuItem("Assign Staff");
		mntmViewForLeave = new JMenuItem("View for Leave");
		mntmApplyForLeave = new JMenuItem("Apply for Leave");
		mntmHandleApplication = new JMenuItem("Handle Application");
		mntmLogout = new JMenuItem("Logout");

		internalFrameAddStaff = new InternalFrameAddStaff();
		internalFrameDeleteStaff = new InternalFrameDeleteStaff();
		internalFrameAssignStaff = new InternalFrameAssignStaff();
		internalFrameViewLeave = new InternalFrameViewLeave();
		internalFrameApplyLeave = new InternalFrameApplyLeave();
		internalFrameHandleLeave = new InternalFrameHandleLeave();

		desktopPane = new JDesktopPane();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(mnNewMenu);

		mnNewMenu.add(mntmAddNewStaff);
		mnNewMenu.add(mntmDeleteStaff);
		mnNewMenu.add(mntmAssignStaff);
		mnNewMenu.add(mntmViewForLeave);
		mnNewMenu.add(mntmApplyForLeave);
		mnNewMenu.add(mntmHandleApplication);
		mnNewMenu.add(mntmLogout);

		getContentPane().setLayout(new BorderLayout(0, 0));

		getContentPane().add(desktopPane);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_desktopPane.createSequentialGroup().addGroup(
								gl_desktopPane
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_desktopPane
														.createSequentialGroup()
														.addComponent(internalFrameAddStaff,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(internalFrameDeleteStaff,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(internalFrameAssignStaff,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addComponent(internalFrameApplyLeave, GroupLayout.DEFAULT_SIZE, 1008,
												Short.MAX_VALUE)
										.addComponent(internalFrameHandleLeave, GroupLayout.PREFERRED_SIZE, 1008,
												Short.MAX_VALUE)
										.addComponent(internalFrameViewLeave, GroupLayout.DEFAULT_SIZE, 1008,
												Short.MAX_VALUE))));
		gl_desktopPane.setVerticalGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_desktopPane
						.createSequentialGroup()
						.addGroup(
								gl_desktopPane
										.createParallelGroup(Alignment.LEADING)
										.addComponent(internalFrameAddStaff, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(internalFrameDeleteStaff, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(internalFrameAssignStaff, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(internalFrameApplyLeave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(internalFrameHandleLeave, GroupLayout.PREFERRED_SIZE, 350,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(internalFrameViewLeave, GroupLayout.PREFERRED_SIZE, 350,
								GroupLayout.PREFERRED_SIZE)));
		desktopPane.setLayout(gl_desktopPane);
	}

	@Override
	public void displayView() {
		this.setVisible(true);
	}

	@Override
	public void resetView() {
		hiddenInternalView();

		mntmAddNewStaff.setEnabled(false);
		mntmDeleteStaff.setEnabled(false);
		mntmAssignStaff.setEnabled(false);
		mntmViewForLeave.setEnabled(false);
		mntmApplyForLeave.setEnabled(false);
		mntmHandleApplication.setEnabled(false);

		for (Component c : mnNewMenu.getMenuComponents()) {
			if (c instanceof JMenuItem) {
				ButtonUtil.removeAllActionListeners((JMenuItem) c);
			}
		}
	}

	public void setLoginStaff(Staff loginStaff) {
		this.setTitle(title + " (" + loginStaff.getName() + ")");
	}

	public void hiddenInternalView() {
		internalFrameAddStaff.setVisible(false);
		internalFrameDeleteStaff.setVisible(false);
		internalFrameAssignStaff.setVisible(false);
		internalFrameViewLeave.setVisible(false);
		internalFrameApplyLeave.setVisible(false);
		internalFrameHandleLeave.setVisible(false);
	}

	public JMenuItem getMntmAddNewStaff() {
		return mntmAddNewStaff;
	}

	public JMenuItem getMntmDeleteStaff() {
		return mntmDeleteStaff;
	}

	public JMenuItem getMntmAssignStaff() {
		return mntmAssignStaff;
	}

	public JMenuItem getMntmViewForLeave() {
		return mntmViewForLeave;
	}

	public JMenuItem getMntmApplyForLeave() {
		return mntmApplyForLeave;
	}

	public JMenuItem getMntmHandleApplication() {
		return mntmHandleApplication;
	}

	public InternalFrameAddStaff getInternalFrameAddStaff() {
		return internalFrameAddStaff;
	}

	public InternalFrameDeleteStaff getInternalFrameDeleteStaff() {
		return internalFrameDeleteStaff;
	}

	public InternalFrameAssignStaff getInternalFrameAssignStaff() {
		return internalFrameAssignStaff;
	}

	public InternalFrameViewLeave getInternalFrameViewLeave() {
		return internalFrameViewLeave;
	}

	public InternalFrameApplyLeave getInternalFrameApplyLeave() {
		return internalFrameApplyLeave;
	}

	public InternalFrameHandleLeave getInternalFrameHandleLeave() {
		return internalFrameHandleLeave;
	}

	public void addAddNewStaffMenuActionListener(ActionListener actionListener) {
		mntmAddNewStaff.addActionListener(actionListener);
	}

	public void addDeleteStaffMenuActionListener(ActionListener actionListener) {
		mntmDeleteStaff.addActionListener(actionListener);
	}

	public void addAssignStaffMenuActionListener(ActionListener actionListener) {
		mntmAssignStaff.addActionListener(actionListener);
	}

	public void addViewForLeaveMenuActionListener(ActionListener actionListener) {
		mntmViewForLeave.addActionListener(actionListener);
	}

	public void addApplyForLeaveMenuActionListener(ActionListener actionListener) {
		mntmApplyForLeave.addActionListener(actionListener);
	}

	public void addHandleApplicationMenuActionListener(ActionListener actionListener) {
		mntmHandleApplication.addActionListener(actionListener);
	}

	public void addLogoutActionListener(ActionListener actionListener) {
		mntmLogout.addActionListener(actionListener);
	}
}
