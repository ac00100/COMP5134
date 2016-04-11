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

		// List<Staff> staffs = new ArrayList<Staff>();
		// staffs.add(new Staff("1", "Benny"));
		// staffs.add(new Staff("2", "Candy"));
		// company.setStaffs(staffs);
		serializer.writeObjectToFile(company);

		try {
			// Company company2 = (Company) serializer.readObjectsFromFile();

			// System.out.println(company2.getStaffs().get(1).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
