package org.system.core.view.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.system.core.model.Leave;

public abstract class AbstractObjectTableModel<T> extends DefaultTableModel {
	private static final long serialVersionUID = 2317250569258741417L;

	public AbstractObjectTableModel(List<Leave> leaves) {
		super();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
