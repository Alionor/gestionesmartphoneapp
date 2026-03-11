package it.prova.gestionesmartphoneapp.dao.app;

import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

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
    public void unlinkAppFromSmartphones(Long idApp) throws Exception {
        if (idApp < 0) throw new Exception("Id non valido.");
        entityManager.createNativeQuery("DELETE from smartphone_app where id_app = ?").setParameter(1, idApp).executeUpdate();
    }

    @Override
    public void unlinkAppFromOneSmartphone(Long idSmartphone, Long idApp) throws Exception {
        if (idApp < 0) throw new Exception("Id non valido.");
        entityManager.createNativeQuery("DELETE from smartphone_app where id_smartphone = ?1 and id_app = ?2")
                .setParameter(1, idSmartphone).setParameter(2, idApp).executeUpdate();
    }

    @Override
    public void installAppOnSmartphone(App appTransient, Smartphone smartphoneTransient) throws Exception {
        if (appTransient.getId() == null || smartphoneTransient.getId() == null)
            throw new Exception("Elementi non presenti sul database.");
        entityManager.createNativeQuery("INSERT into smartphone_app(id_smartphone, id_app) values (?1, ?2);")
                .setParameter(1, smartphoneTransient.getId()).setParameter(2, appTransient.getId()).executeUpdate();
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
