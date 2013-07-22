package net.mothman.persistence.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements Operations<T> {
    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

	private Class<T> getDomainClass() {
	    if (clazz == null) {
	    	ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
	        this.clazz = (Class<T>) thisType.getActualTypeArguments()[0];
	    }
	    return clazz;
	}

    public final T get(final long id) {
        return (T) getSession().get(getDomainClass(), id);
    }

    public final List<T> list() {
        return getSession().createQuery("from " + getDomainClass().getName()).list();
    }

    public final void insert(final T entity) {
        Preconditions.checkNotNull(entity);
        // getCurrentSession().persist(entity);
        getSession().saveOrUpdate(entity);
    }

    public final T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getSession().merge(entity);
    }

    public final void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getSession().delete(entity);
    }

    public final void deleteById(final long entityId) {
        final T entity = get(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}