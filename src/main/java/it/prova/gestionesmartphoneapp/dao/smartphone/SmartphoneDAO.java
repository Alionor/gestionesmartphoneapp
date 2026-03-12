package it.prova.gestionesmartphoneapp.dao.smartphone;

import it.prova.gestionesmartphoneapp.dao.IBaseDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneDAO extends IBaseDAO<Smartphone> {

    public void unlinkSmartphoneFromApp(Long idSmartphone) throws Exception;

    public Smartphone findEagerById(Long id) throws Exception;

}
