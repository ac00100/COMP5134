package org.system.base.dao.support;

import org.system.base.config.FilePath;

public class ObjectDaoSupport {

	public String getObjectFilePath(Object obj) {
		return FilePath.rootPath + "/" + obj.getClass().getName() + ".txt";
	}
}
