package org.system.base.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.system.base.dao.support.ObjectDaoSupport;

public class ObjectDAOImpl<T extends Serializable> extends ObjectDaoSupport implements ObjectDAO<T> {

	private T t;

	public ObjectDAOImpl(T t) {
		this.t = t;
	}

	public boolean writeObjectToFile(T obj) {

		try {
			FileOutputStream fos = new FileOutputStream(getObjectFilePath(obj));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
			// System.out.println("File write to " + getObjectFilePath(t));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File write fialed to " + getObjectFilePath(t)  + ".");
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public T readObjectsFromFile() {
		FileInputStream fin;
		try {
			fin = new FileInputStream(getObjectFilePath(t));
			ObjectInputStream ois = new ObjectInputStream(fin);
			t = (T) ois.readObject();
			ois.close();
			// System.out.println("File read from " + getObjectFilePath(t));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File read fialed from " + getObjectFilePath(t)  + ". Return the new instance.");
			return t;
		}
		return t;

	}
}
