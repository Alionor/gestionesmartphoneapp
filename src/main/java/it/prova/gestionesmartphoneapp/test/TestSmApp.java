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
            testInserimentoEAggiornamentoApp(appServiceInstance);

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

    public static void testInserimentoEAggiornamentoApp(AppService appServiceInstance) throws Exception {
        System.out.println("Inizio test inserimento e aggiornamento APP");

        List<App> listaApp = appServiceInstance.listAll();

        LocalDate data1 = LocalDate.parse("2024-03-20");
        LocalDate data2 = LocalDate.parse("2024-03-20");

        App nuovaApp = new App("Zoom", data1, data2, "6.0");
        appServiceInstance.inserisciNuova(nuovaApp);
        if (appServiceInstance.listAll().size() != (listaApp.size() + 1))
            throw new RuntimeException("Errore di inserimento app");

        appServiceInstance.aggiornaVersione(nuovaApp.getId(), "6.1");
        App appAggiornata = appServiceInstance.trovaPerId(nuovaApp.getId());
        if (!appAggiornata.getVersione().equals("6.1"))
            throw new RuntimeException("Errore di aggiornamento app");

        appServiceInstance.rimuovi(nuovaApp.getId());

        if(appServiceInstance.listAll().size() != listaApp.size())
            throw new RuntimeException("Errore di eliminazione app");

        System.out.println("FINE test inserimento e aggiornamento APP - COMPLETATO CON SUCCESSO");
    }


}
