package it.prova.gestionesmartphoneapp.dao;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.app.AppDAOImpl;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAOImpl;

public class MyDaoFactory {

	private static SmartphoneDAO smartphoneDAOInstance = null;
	private static AppDAO appDAOInstance = null;

	public static SmartphoneDAO getSmartphoneDAOInstance() {
		if (smartphoneDAOInstance == null)
			smartphoneDAOInstance = new SmartphoneDAOImpl();
		return smartphoneDAOInstance;
	}

	public static AppDAO getGenereDAOInstance() {
		if (appDAOInstance == null)
			appDAOInstance = new AppDAOImpl();

		return appDAOInstance;
	}

}
