package it.prova.gestionesmartphoneapp.service.smartphone;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneServiceImpl implements SmartphoneService {

    private SmartphoneDAO smartphoneDAO;

    @Override
    public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO) {
        this.smartphoneDAO = smartphoneDAO;
    }

    @Override
    public List<Smartphone> listAll() throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            smartphoneDAO.setEntityManager(entityManager);
            return smartphoneDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public Smartphone trovaPerId(Long idSmartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            smartphoneDAO.setEntityManager(entityManager);
            return smartphoneDAO.findById(idSmartphone);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void aggiorna(Smartphone smartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            smartphoneDAO.setEntityManager(entityManager);
            smartphoneDAO.update(smartphone);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void inserisciNuovo(Smartphone smartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            smartphoneDAO.setEntityManager(entityManager);
            smartphoneDAO.insert(smartphone);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void rimuovi(Long idSmartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            smartphoneDAO.setEntityManager(entityManager);
            smartphoneDAO.unlinkSmartphoneFromApp(idSmartphone);
            smartphoneDAO.delete(idSmartphone);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

}