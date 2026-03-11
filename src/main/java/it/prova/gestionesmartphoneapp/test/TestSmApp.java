package it.prova.gestionesmartphoneapp.test;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.app.AppService;
import it.prova.gestionesmartphoneapp.service.smartphone.SmartphoneService;

import java.time.LocalDate;
import java.util.List;

public class TestSmApp {
    public static void main(String[] args) {

        SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
        AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();

        try {
            testInserimentoEAggiornamentoSmartphone(smartphoneServiceInstance);

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            EntityManagerUtil.shutdown();
        }
    }

    public static void testInserimentoEAggiornamentoSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println("Inizio test inserimento e aggiornamento Smartphone");

        List<Smartphone> listaCellulari = smartphoneServiceInstance.listAll();

        Smartphone nuovoCell = new Smartphone("Huawei", "P50", 599.99f, "HarmonyOS");
        smartphoneServiceInstance.inserisciNuovo(nuovoCell);
        if (smartphoneServiceInstance.listAll().size() != (listaCellulari.size() + 1))
            throw new RuntimeException("Errore di inserimento smartphone");

        nuovoCell.setVersioneOS("Android 14");
        smartphoneServiceInstance.aggiorna(nuovoCell);
        if (!nuovoCell.getVersioneOS().equals("Android 14"))
            throw new RuntimeException("Errore di aggiornamento smartphone");

        smartphoneServiceInstance.rimuovi(nuovoCell.getId());

        if(smartphoneServiceInstance.listAll().size() != listaCellulari.size())
            throw new RuntimeException("Errore di eliminazione smartphone");

        System.out.println("FINE test inserimento e aggiornamento Smartphone - COMPLETATO CON SUCCESSO");
    }




}
