package org.system.base.dao;

import java.io.Serializable;

public interface ObjectDAO<T extends Serializable> {

	boolean writeObjectToFile(T obj);

	T readObjectsFromFile();
}
