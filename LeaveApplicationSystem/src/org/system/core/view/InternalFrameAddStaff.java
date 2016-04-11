package org.system.core.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.system.core.model.Staff;
import org.system.core.util.ButtonUtil;

@SuppressWarnings("serial")
public class InternalFrameAddStaff extends AbstractFunctionalFrame {

	private JTextField textFieldStaffID;
	private JTextField textFieldStaffName;
	private JComboBox comboBoxSuperisor;
	private JButton btnCreateNewStaff;

	public InternalFrameAddStaff() {
		super("Add New Staff");
	}

	@Override
	protected void initComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Staff ID:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		textFieldStaffID = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		getContentPane().add(textFieldStaffID, gbc_textField);
		textFieldStaffID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Staff Name:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		textFieldStaffName = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		getContentPane().add(textFieldStaffName, gbc_textField_1);
		textFieldStaffName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Supervisor");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		comboBoxSuperisor = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBoxSuperisor, gbc_comboBox);

		btnCreateNewStaff = new JButton("Add New Staff");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		getContentPane().add(btnCreateNewStaff, gbc_btnNewButton);
	}

	@Override
	public void resetView() {
		textFieldStaffID.setText("");
		textFieldStaffName.setText("");
		comboBoxSuperisor.removeAllItems();
		for (Staff supervisor : getViewStaffs()) {
			comboBoxSuperisor.addItem(supervisor);
		}
		ButtonUtil.removeAllActionListeners(btnCreateNewStaff);

		getMainFrame(this).repaint();
	}

	public Staff getNewStaff() {
		Staff supervisor = (Staff) comboBoxSuperisor.getSelectedItem();
		String supervisorID = (supervisor != null) ? supervisor.getStaffID() : null;
		return new Staff(textFieldStaffID.getText().trim(), textFieldStaffName.getText().trim(), supervisorID);
	}

	public void addCreateStaffActionListener(ActionListener createStaffActionListener) {
		btnCreateNewStaff.addActionListener(createStaffActionListener);
	}

	private Component getMainFrame(Component c) {
		Component applet = null;
		for (Component p = c; p != null; p = p.getParent()) {
			if (p instanceof Window) {
				return p;
			}
			if (p instanceof MainFrameLeaveApplicationSystem) {
				applet = p;
			}
		}
		return applet;
	}

}
