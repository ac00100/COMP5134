package org.system.core.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.system.core.model.Staff;
import org.system.core.util.ButtonUtil;

@SuppressWarnings("serial")
public class InternalFrameDeleteStaff extends AbstractFunctionalFrame {
	private JComboBox comboBoxStaff;
	private JButton btnDeleteStaff;

	public InternalFrameDeleteStaff() {
		super("Delete Staff");
	}

	@Override
	protected void initComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Select Staff to delete");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		comboBoxStaff = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		getContentPane().add(comboBoxStaff, gbc_comboBox);

		btnDeleteStaff = new JButton("Delete Staff");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		getContentPane().add(btnDeleteStaff, gbc_btnNewButton);
	}

	@Override
	public void resetView() {
		comboBoxStaff.removeAllItems();
		for (Staff staff : getViewStaffs()) {
			comboBoxStaff.addItem(staff);
		}
		ButtonUtil.removeAllActionListeners(btnDeleteStaff);
	}

	public String getDeleteStaffID() {
		Staff staff = (Staff) comboBoxStaff.getSelectedItem();
		return (staff != null) ? staff.getStaffID() : null;
	}

	public void addDeleteStaffActionListener(ActionListener createStaffActionListener) {
		btnDeleteStaff.addActionListener(createStaffActionListener);
	}

}
