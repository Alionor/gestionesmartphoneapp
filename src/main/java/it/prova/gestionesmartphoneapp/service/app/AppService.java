package it.prova.gestionesmartphoneapp.service.app;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import java.util.List;

public interface AppService {
    public void setAppDAO(AppDAO appDAO);

    public List<App> listAll() throws Exception;

    public App trovaPerId(Long idApp) throws Exception;

    public void aggiorna(App app) throws Exception;

    public void inserisciNuova(App app) throws Exception;

    public void rimuovi(Long idApp) throws Exception;

    public void aggiornaVersione(Long idApp, String nuovaVersione) throws Exception;

    public void installaAppSuSmartphone(App appTransient, Smartphone smartphoneTransient) throws Exception;

    public void disinstallaAppDaSmartphone(App appTransient, Smartphone smartphoneTransient) throws Exception;

}
