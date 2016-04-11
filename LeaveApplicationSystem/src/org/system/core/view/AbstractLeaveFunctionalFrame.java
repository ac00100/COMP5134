package org.system.core.view;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.system.core.model.Leave;
import org.system.core.view.model.LeaveTableModel;

@SuppressWarnings("serial")
public abstract class AbstractLeaveFunctionalFrame extends AbstractFunctionalFrame {
	protected JTable table;
	private List<Leave> viewLeaves;

	public AbstractLeaveFunctionalFrame(String title) {
		super(title);
	}

	protected abstract void initComponents();

	public abstract void resetView();

	public void setViewTableModels(List<Leave> leaves) {
		viewLeaves = leaves;
		table.setModel(new LeaveTableModel(leaves));
		resizeColumnWidth(table);
	}

	public Leave getSelectedLeave() {
		return (table.getSelectedRow() < 0) ? null : viewLeaves.get(table.getSelectedRow());
	}

	private void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
}
