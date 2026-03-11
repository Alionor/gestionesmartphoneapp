package it.prova.gestionesmartphoneapp.dao.app;

import it.prova.gestionesmartphoneapp.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Override
    public List<App> findAll() throws Exception {
        return entityManager.createQuery("from App", App.class).getResultList();
    }

    @Override
    public App findById(Long id) throws Exception {
        if (id < 0) throw new Exception("Id non valido.");
        return entityManager.find(App.class, id);
    }

    @Override
    public void update(App app) throws Exception {
        if (app == null) throw new Exception("Oggetto inserito non valido.");
        entityManager.merge(app);
    }

    @Override
    public void insert(App app) throws Exception {
        if (app == null) throw new Exception("Oggetto inserito non valido.");
        entityManager.persist(app);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id < 0) throw new Exception("Id non valido.");
        entityManager.createQuery("DELETE FROM App where id = ?1")
                .setParameter(1, id).executeUpdate();
    }

    @Override
    public void unlinkAppFromSmartphone(Long idApp) throws Exception {
        if (idApp < 0) throw new Exception("Id non valido.");
        entityManager.createNativeQuery("DELETE from smarphone_app where id_app = ?").setParameter(1, idApp).executeUpdate();
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
