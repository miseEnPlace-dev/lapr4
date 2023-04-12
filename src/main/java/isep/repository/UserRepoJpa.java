package isep.repository;

import isep.domain.model.SystemUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserRepoJpa implements IUserRepoJpa {
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LearningSystemsPU");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

    /**
     * Inserts an entity.
     *
     * @param user
     * @return the persisted entity
     */
    @Override
    public SystemUser add(SystemUser user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        em.close();

        return user;
    }

    /**
     * Reads an entity GrupoAutomovel given its ID.
     *
     * @param id
     * @return
     */
    @Override
    public SystemUser findById(Long id) {
        return getEntityManager().find(SystemUser.class, id);
    }

    /**
     * Returns the List of all entities in the persistence store.
     *
     * @return
     */
    @Override
    public List<SystemUser> findAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM User e");
        List<SystemUser> list = query.getResultList();
        return list;
    }
}
