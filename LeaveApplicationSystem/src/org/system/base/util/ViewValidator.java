package org.system.base.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.system.core.view.AbstractFunctionalFrame;

public abstract class ViewValidator<V extends AbstractFunctionalFrame> {

	protected V view;
	private boolean warning = false;
	private List<String> warningMessages = new ArrayList<String>();
	

	public ViewValidator(V view) {
		this.view = view;
	}

	public abstract void validate();
	
	protected void addWarningMessage(String warningMessage) {
		warningMessages.add(warningMessage);
		warning = true;
	}

	public boolean hasWarningMessage() {
		return warning;
	}

	public void popMessage() {
		StringBuilder sb = new StringBuilder();
		for (String warningMessage : warningMessages) {
			sb.append(warningMessage);
			sb.append("\n");
		}
		JOptionPane.showMessageDialog(view, sb, "Warning", JOptionPane.WARNING_MESSAGE);
	}
}
