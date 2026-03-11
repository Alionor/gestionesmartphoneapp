package it.prova.gestionesmartphoneapp.service.app;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public class AppServiceImpl implements AppService {

    private AppDAO appDAO;

    @Override
    public void setAppDAO(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @Override
    public List<App> listAll() throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            appDAO.setEntityManager(entityManager);
            return appDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public App trovaPerId(Long idApp) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            appDAO.setEntityManager(entityManager);
            return appDAO.findById(idApp);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void aggiorna(App app) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            appDAO.setEntityManager(entityManager);
            appDAO.update(app);
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
    public void inserisciNuova(App app) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            appDAO.setEntityManager(entityManager);
            appDAO.insert(app);
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
    public void rimuovi(Long idApp) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            appDAO.setEntityManager(entityManager);
            appDAO.unlinkAppFromSmartphone(idApp);
            appDAO.delete(idApp);
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
