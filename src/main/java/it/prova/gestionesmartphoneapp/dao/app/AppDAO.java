package it.prova.gestionesmartphoneapp.dao.app;

import it.prova.gestionesmartphoneapp.dao.IBaseDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface AppDAO extends IBaseDAO<App> {

    public void unlinkAppFromSmartphones(Long idApp) throws Exception;

    public void installAppOnSmartphone(App appTransient, Smartphone smartphoneTransient) throws Exception;

    public void unlinkAppFromOneSmartphone(Long idSmartphone, Long idApp) throws Exception;

}
