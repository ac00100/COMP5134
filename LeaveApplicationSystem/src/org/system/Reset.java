package org.system;

import org.system.base.dao.ObjectDAO;
import org.system.base.dao.ObjectDAOImpl;
import org.system.core.dto.Company;

public class Reset {

	public Reset() {

	}

	public static void main(String[] args) {
		Company company = new Company();
		ObjectDAO<Company> serializer = new ObjectDAOImpl<Company>(company);

		serializer.writeObjectToFile(company);

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
