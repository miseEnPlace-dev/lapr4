package isep.repository;

import isep.domain.model.SystemUser;
import java.util.List;

public interface IUserRepoJpa {
    /**
     * Inserts an entity and commits.
     *
     * @param entity
     * @return the persisted entity
     */
    public SystemUser add(SystemUser entity);

    /**
     * Reads an entity given its ID.
     *
     * @param id
     * @return
     */
    public SystemUser findById(Long id);

    /**
     * Returns the List of all entities in the persistence store.
     *
     * @return
     */
    public List<SystemUser> findAll();
}
