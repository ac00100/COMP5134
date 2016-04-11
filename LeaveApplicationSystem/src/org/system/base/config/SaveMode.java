package org.system.base.config;

public final class SaveMode {

	public static Mode mode = Mode.File;
	
	public static void setMode(Mode mode) {
		SaveMode.mode = mode;
	}
	
	public static enum Mode {
		File, Session
	}

}
