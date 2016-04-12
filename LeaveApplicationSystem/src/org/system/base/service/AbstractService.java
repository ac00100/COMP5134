package org.system.base.service;

import org.system.base.config.SaveMode;
import org.system.base.dao.ObjectDAO;
import org.system.base.dao.ObjectDAOImpl;
import org.system.core.dto.Company;
import org.system.core.util.SessionUtil;

public abstract class AbstractService {

	private ObjectDAO<Company> companyDAO;

	public AbstractService() {
		companyDAO = new ObjectDAOImpl<Company>(new Company());
	}

	public Company loadCompany() {
		switch (SaveMode.mode) {
		case File:
			return companyDAO.readObjectsFromFile();
		case Session:
			return SessionUtil.getCompany();
		default:
			return SessionUtil.getCompany();
		}
	}

	public void saveCompany(Company company) {
		switch (SaveMode.mode) {
		case File:
			companyDAO.writeObjectToFile(company);
		case Session:
			SessionUtil.setCompany(company);
		default:
			SessionUtil.setCompany(company);
		}
	}
}
