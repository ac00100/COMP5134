package org.system.core.util;

import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

public class ButtonUtil {

	public static <T extends AbstractButton> void removeAllActionListeners(T t) {
		for (ActionListener actionListener : t.getActionListeners()) {
			t.removeActionListener(actionListener);
		}
	}
}
