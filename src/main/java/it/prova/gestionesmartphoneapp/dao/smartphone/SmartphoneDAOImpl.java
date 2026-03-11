package it.prova.gestionesmartphoneapp.dao.smartphone;

import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneDAOImpl implements SmartphoneDAO {

    private EntityManager entityManager;

    @Override
    public List<Smartphone> findAll() throws Exception {
        return entityManager.createQuery("from Smartphone", Smartphone.class).getResultList();
    }

    @Override
    public Smartphone findById(Long id) throws Exception {
        if (id < 0) throw new Exception("Id non valido.");
        return entityManager.find(Smartphone.class, id);
    }

    @Override
    public void update(Smartphone smartphone) throws Exception {
        if (smartphone == null) throw new Exception("Oggetto inserito non valido.");
        entityManager.merge(smartphone);
    }

    @Override
    public void insert(Smartphone smartphone) throws Exception {
        if (smartphone == null) throw new Exception("Oggetto inserito non valido.");
        entityManager.persist(smartphone);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id < 0) throw new Exception("Id non valido.");
        entityManager.createQuery("DELETE FROM Smartphone where id = ?1")
                .setParameter(1, id).executeUpdate();
    }

    @Override
    public void unlinkSmartphoneFromApp(Long idSmartphone) throws Exception {
        if (idSmartphone < 0) throw new Exception("Id non valido.");
        entityManager.createNativeQuery("DELETE from smarphone_app where id_smartphone = ?").setParameter(1, idSmartphone).executeUpdate();
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
