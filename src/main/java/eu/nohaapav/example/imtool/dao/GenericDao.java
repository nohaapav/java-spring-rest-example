package eu.nohaapav.example.imtool.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.google.common.reflect.TypeToken;

/**
 * Generic DAO. Use this class for all repository classes.
 *
 * @param <T> - Entity.
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
abstract class GenericDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    protected GenericDao() {
        this.entityClass = (Class<T>) new TypeToken<T>(getClass()) {
            private static final long serialVersionUID = 1L;
        }.getType();
    }

    /**
     * Returns Session.
     *
     * @return {@link org.hibernate.Session}
     */
    protected Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    /**
     * Returns Entity name.
     *
     * @return Entity class name.
     */
    protected String getEntityName() {
        return entityClass.getSimpleName();
    }

    /**
     * Persists entity.
     *
     * @param entity - entity.
     */
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    /**
     * Merges entity.
     *
     * @param entity - entity.
     */
    public void merge(T entity) {
        entityManager.merge(entity);
    }

    /**
     * Flushes entityManager session.
     */
    public void flush() {
        entityManager.flush();
    }

    /**
     * Remove entity.
     *
     * @param entity - entity.
     */
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Find entity by it's id.
     *
     * @param id - identificator.
     */
    public T find(Long id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Find all entities.
     *
     * @return List<T>.
     */
    public List<T> findAll() {
        return getSession().createCriteria(entityClass).list();
    }

    protected T findUnique(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return (T) criteria.uniqueResult();
    }

    protected List<T> find(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return criteria.list();
    }
}
