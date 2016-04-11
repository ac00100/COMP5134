package org.system.core.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.system.core.CoreConstants;
import org.system.core.util.ButtonUtil;

@SuppressWarnings("serial")
public class InternalFrameApplyLeave extends AbstractLeaveFunctionalFrame {
	private JTextField startDateField;
	private JTextField endDateField;
	private JButton applyLeaveButton;
	private JButton cancelLeaveButton;

	public InternalFrameApplyLeave() {
		super("Apply Leave");
	}

	@Override
	protected void initComponents() {
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
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
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblStartDate = new JLabel("Start Date (" + CoreConstants.DATE_FORMAT_DISPLAY + "):");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.anchor = GridBagConstraints.EAST;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 0;
		panel_1.add(lblStartDate, gbc_lblStartDate);

		startDateField = new JTextField();
		GridBagConstraints gbc_startDateField = new GridBagConstraints();
		gbc_startDateField.gridwidth = 3;
		gbc_startDateField.insets = new Insets(0, 0, 5, 0);
		gbc_startDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_startDateField.gridx = 1;
		gbc_startDateField.gridy = 0;
		panel_1.add(startDateField, gbc_startDateField);
		startDateField.setColumns(10);

		JLabel lblEndDate = new JLabel("End Date (" + CoreConstants.DATE_FORMAT_DISPLAY + "):");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.anchor = GridBagConstraints.EAST;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 1;
		panel_1.add(lblEndDate, gbc_lblEndDate);

		endDateField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_1.add(endDateField, gbc_textField_1);
		endDateField.setColumns(10);

		applyLeaveButton = new JButton("Apply Leave");
		GridBagConstraints gbc_applyLeaveButton = new GridBagConstraints();
		gbc_applyLeaveButton.insets = new Insets(0, 0, 0, 5);
		gbc_applyLeaveButton.anchor = GridBagConstraints.EAST;
		gbc_applyLeaveButton.gridx = 2;
		gbc_applyLeaveButton.gridy = 2;
		panel_1.add(applyLeaveButton, gbc_applyLeaveButton);

		cancelLeaveButton = new JButton("Cancel Leave");
		GridBagConstraints gbc_cancelLeaveButton = new GridBagConstraints();
		gbc_cancelLeaveButton.gridx = 3;
		gbc_cancelLeaveButton.gridy = 2;
		panel_1.add(cancelLeaveButton, gbc_cancelLeaveButton);
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void resetView() {
		startDateField.setText("");
		endDateField.setText("");
		ButtonUtil.removeAllActionListeners(applyLeaveButton);
		ButtonUtil.removeAllActionListeners(cancelLeaveButton);
	}

	public void addApplyLeaveActionListener(ActionListener applyLeaveActionListener) {
		applyLeaveButton.addActionListener(applyLeaveActionListener);
	}

	public void addCancelLeaveActionListener(ActionListener cancelLeaveActionListener) {
		cancelLeaveButton.addActionListener(cancelLeaveActionListener);
	}

	public String getStartDateStr() {
		return startDateField.getText().trim();
	}

	public String getEndDateStr() {
		return endDateField.getText().trim();
	}

}
