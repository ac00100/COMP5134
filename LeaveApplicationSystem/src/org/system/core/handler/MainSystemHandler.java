package org.system.core.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.system.base.config.SaveMode;
import org.system.base.config.SaveMode.Mode;
import org.system.core.view.MainFrameLeaveApplicationSystem;
import org.system.core.view.MainFrameMainSystem;

public class MainSystemHandler extends AbstractMainHandler<MainFrameMainSystem> {

	public MainSystemHandler(MainFrameMainSystem view) {
		super(view);
	}

	public void init() {
		super.init();
		view.setLeaveApplicationBySessionActionListener(getShowAppActionListener(Mode.Session));
		view.setLeaveApplicationByFileActionListener(getShowAppActionListener(Mode.File));
	}

	private ActionListener getShowAppActionListener(final Mode mode) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				SaveMode.setMode(mode);
				LeaveApplicationSystemHandler appHandler = new LeaveApplicationSystemHandler(new MainFrameLeaveApplicationSystem());
				appHandler.init();
			}
		};
	}
}