package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.MyDaoFactory;
import it.prova.gestionesmartphoneapp.service.app.AppService;
import it.prova.gestionesmartphoneapp.service.app.AppServiceImpl;
import it.prova.gestionesmartphoneapp.service.smartphone.SmartphoneService;
import it.prova.gestionesmartphoneapp.service.smartphone.SmartphoneServiceImpl;

public class MyServiceFactory {

	private static SmartphoneService smartphoneServiceInstance = null;
	private static AppService appServiceInstance = null;

	public static SmartphoneService getSmartphoneServiceInstance() {
		if (smartphoneServiceInstance == null)
			smartphoneServiceInstance = new SmartphoneServiceImpl();

		smartphoneServiceInstance.setSmartphoneDAO(MyDaoFactory.getSmartphoneDAOInstance());

		return smartphoneServiceInstance;
	}

	public static AppService getAppServiceInstance() {
		if (appServiceInstance == null)
			appServiceInstance = new AppServiceImpl();

		appServiceInstance.setAppDAO(MyDaoFactory.getAppDAOInstance());

		return appServiceInstance;
	}

}
