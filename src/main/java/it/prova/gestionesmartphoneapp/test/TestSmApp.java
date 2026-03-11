package it.prova.gestionesmartphoneapp.test;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.app.AppService;
import it.prova.gestionesmartphoneapp.service.smartphone.SmartphoneService;

public class TestSmApp {
    public static void main(String[] args) {

        SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
        AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();

        EntityManagerUtil.getEntityManager();

    }

}
