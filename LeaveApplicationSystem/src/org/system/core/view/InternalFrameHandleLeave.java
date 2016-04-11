package org.system.core.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.system.core.util.ButtonUtil;

@SuppressWarnings("serial")
public class InternalFrameHandleLeave extends AbstractLeaveFunctionalFrame {
	private JButton approveLeaveButton;
	private JButton declineLeaveButton;

	public InternalFrameHandleLeave() {
		super("Handle Leave Application");
	}

	@Override
	protected void initComponents() {
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 992, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 847, 73, 67, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		approveLeaveButton = new JButton("Approve");
		GridBagConstraints gbc_approveLeaveButton = new GridBagConstraints();
		gbc_approveLeaveButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_approveLeaveButton.insets = new Insets(0, 0, 0, 5);
		gbc_approveLeaveButton.gridx = 1;
		gbc_approveLeaveButton.gridy = 0;
		panel_1.add(approveLeaveButton, gbc_approveLeaveButton);

		declineLeaveButton = new JButton("Decline");
		GridBagConstraints gbc_declineLeaveButton = new GridBagConstraints();
		gbc_declineLeaveButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_declineLeaveButton.gridx = 2;
		gbc_declineLeaveButton.gridy = 0;
		panel_1.add(declineLeaveButton, gbc_declineLeaveButton);
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void resetView() {
		ButtonUtil.removeAllActionListeners(approveLeaveButton);
		ButtonUtil.removeAllActionListeners(declineLeaveButton);
	}

	public void addApproveLeaveActionListener(ActionListener approveLeaveActionListener) {
		approveLeaveButton.addActionListener(approveLeaveActionListener);
	}

	public void addDeclineLeaveActionListener(ActionListener declineLeaveActionListener) {
		declineLeaveButton.addActionListener(declineLeaveActionListener);
	}

}
