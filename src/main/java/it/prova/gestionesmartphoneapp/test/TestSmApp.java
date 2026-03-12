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
            testInstallaEDisinstallaApp(appServiceInstance, smartphoneServiceInstance);
            testRimozioneCompletaDiUnoSmartphone(smartphoneServiceInstance, appServiceInstance);

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            EntityManagerUtil.shutdown();
        }
    }

    public static void testInserimentoEAggiornamentoSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println("Inizio test inserimento e aggiornamento Smartphone");

        List<Smartphone> listaCellulari = smartphoneServiceInstance.listAll();
        if (listaCellulari == null) throw new Exception("Non ci sono record su db.");

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
        if (listaApp == null) throw new Exception("Non ci sono record su db.");

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


    public static void testInstallaEDisinstallaApp(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println("Inizio test installazione e disinstallazione APP");

        List<App> listaApp = appServiceInstance.listAll();
        List<Smartphone> listaCellulari = smartphoneServiceInstance.listAll();
        if (listaApp == null || listaCellulari == null) throw new Exception("Non ci sono record su db.");

        Smartphone smartphonePerInstallazione = new Smartphone("Huawei", "P50", 599.99f, "HarmonyOS");
        smartphoneServiceInstance.inserisciNuovo(smartphonePerInstallazione);

        LocalDate data1 = LocalDate.parse("2024-03-20");
        LocalDate data2 = LocalDate.parse("2024-03-20");
        App appDaInstallare = new App("Zoom", data1, data2, "6.0");
        appServiceInstance.inserisciNuova(appDaInstallare);

        smartphonePerInstallazione = smartphoneServiceInstance.trovaPerIdEager(smartphonePerInstallazione.getId());

        if (appDaInstallare == null || smartphonePerInstallazione == null)
            throw new RuntimeException("Errore di findById");

        int numeroAppInstallate = smartphonePerInstallazione.getApps().size();

        appServiceInstance.installaAppSuSmartphone(appDaInstallare, smartphonePerInstallazione);

        Smartphone smartphonePerVerifica = smartphoneServiceInstance.trovaPerIdEager(smartphonePerInstallazione.getId());

        int counter = 0;
        for (App app : smartphonePerVerifica.getApps()) {
            if (app.getId() == appDaInstallare.getId())
                counter++;
        }
        if (counter == 0) throw new RuntimeException("Errore di installazione app");

        appServiceInstance.disinstallaAppDaSmartphone(appDaInstallare, smartphonePerVerifica);
        Smartphone smartphoneAggiornato = smartphoneServiceInstance.trovaPerIdEager(smartphonePerVerifica.getId());
        if (numeroAppInstallate != smartphoneAggiornato.getApps().size())
            throw new RuntimeException("Errore disinstallazione app");

        System.out.println("FINE test installazione e disinstallazione APP - COMPLETATO CON SUCCESSO");
    }


    public static void testRimozioneCompletaDiUnoSmartphone(SmartphoneService smartphoneServiceInstance, AppService appServiceInstance) throws Exception {
        System.out.println("Inizio testRimozioneCompletaDiUnoSmartphone");

        List<Smartphone> listaCellulari = smartphoneServiceInstance.listAll();
        if (listaCellulari == null) throw new Exception("Non ci sono record su db.");
        Smartphone nuovoCell = new Smartphone("Huawei", "P50", 599.99f, "HarmonyOS");
        smartphoneServiceInstance.inserisciNuovo(nuovoCell);
        if (smartphoneServiceInstance.listAll().size() != (listaCellulari.size() + 1))
            throw new RuntimeException("Errore di inserimento smartphone");

        List<App> listaApp = appServiceInstance.listAll();
        if (listaApp == null) throw new Exception("Non ci sono record su db.");
        LocalDate data1 = LocalDate.parse("2024-03-20");
        LocalDate data2 = LocalDate.parse("2024-03-20");
        App nuovaApp1 = new App("Zoom", data1, data2, "6.0");
        App nuovaApp2 = new App("Calcolatrice", data1, data2, "2.0");
        appServiceInstance.inserisciNuova(nuovaApp1);
        appServiceInstance.inserisciNuova(nuovaApp2);
        if (appServiceInstance.listAll().size() != (listaApp.size() + 2))
            throw new RuntimeException("Errore di inserimento app");

        appServiceInstance.installaAppSuSmartphone(nuovaApp1, nuovoCell);
        appServiceInstance.installaAppSuSmartphone(nuovaApp2, nuovoCell);

        Smartphone cellAggiornato = smartphoneServiceInstance.trovaPerIdEager(nuovoCell.getId());

        smartphoneServiceInstance.rimuovi(cellAggiornato.getId());

        if(smartphoneServiceInstance.listAll().size() != listaCellulari.size())
            throw new RuntimeException("Errore di eliminazione smartphone");

        System.out.println("FINE testRimozioneCompletaDiUnoSmartphone - COMPLETATO CON SUCCESSO");
    }



}
