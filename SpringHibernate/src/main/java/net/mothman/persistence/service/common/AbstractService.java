package net.mothman.persistence.service.common;

import java.io.Serializable;
import java.util.List;

import net.mothman.persistence.dao.common.Operations;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService<T extends Serializable> implements Operations<T> {

    public T get(final long id) {
        return getDao().get(id);
    }

    public List<T> list() {
        return getDao().list();
    }

    public void insert(final T entity) {
        getDao().insert(entity);
    }

    public T update(final T entity) {
        return getDao().update(entity);
    }

    public void delete(final T entity) {
        getDao().delete(entity);
    }

    public void deleteById(final long entityId) {
        getDao().deleteById(entityId);
    }

    protected abstract Operations<T> getDao();

}
