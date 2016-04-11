package org.system.core.view;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.system.core.util.ButtonUtil;

@SuppressWarnings("serial")
public class MainFrameMainSystem extends AbstractMainFrame {
	private JButton leaveApplicationBySessionButton;
	private JButton leaveApplicationByFileButton;

	public MainFrameMainSystem() {
		super("Leave Application System");
		setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void initComponents() {
		leaveApplicationBySessionButton = new JButton("Leave Application System (Session)");
		leaveApplicationByFileButton = new JButton("Leave Application System (File System)");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(46)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																leaveApplicationByFileButton,
																GroupLayout.PREFERRED_SIZE,
																341,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																leaveApplicationBySessionButton,
																GroupLayout.PREFERRED_SIZE,
																341,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(55, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(29)
						.addComponent(leaveApplicationBySessionButton,
								GroupLayout.PREFERRED_SIZE, 84,
								GroupLayout.PREFERRED_SIZE)
						.addGap(33)
						.addComponent(leaveApplicationByFileButton,
								GroupLayout.PREFERRED_SIZE, 84,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(43, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void resetView() {
	}

	public void setLeaveApplicationBySessionActionListener(ActionListener actionListener) {
		ButtonUtil.removeAllActionListeners(leaveApplicationBySessionButton);
		leaveApplicationBySessionButton.addActionListener(actionListener);
	}

	public void setLeaveApplicationByFileActionListener(ActionListener actionListener) {
		ButtonUtil.removeAllActionListeners(leaveApplicationByFileButton);
		leaveApplicationByFileButton.addActionListener(actionListener);
	}
}
