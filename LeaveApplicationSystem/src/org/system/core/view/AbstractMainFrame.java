package org.system.core.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class AbstractMainFrame extends JFrame {

	public AbstractMainFrame(String title) {
		super(title);
		setVisible(false);
		initComponents();
	}

	protected abstract void initComponents();

	public abstract void resetView();

	public void displayView() {
		this.setVisible(true);
	}
}
