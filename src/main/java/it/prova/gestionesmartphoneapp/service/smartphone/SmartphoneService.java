package it.prova.gestionesmartphoneapp.service.smartphone;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import java.util.List;

public interface SmartphoneService {

    public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);

    public List<Smartphone> listAll() throws Exception;

    public Smartphone trovaPerId(Long idSmartphone) throws Exception;

    public void aggiorna(Smartphone smartphone) throws Exception;

    public void inserisciNuovo(Smartphone smartphone) throws Exception;

    public void rimuovi(Long idSmartphone) throws Exception;

    public Smartphone trovaPerIdEager(Long idSmartphone) throws Exception;

}
