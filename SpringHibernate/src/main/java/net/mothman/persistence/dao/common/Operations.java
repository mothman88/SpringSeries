package net.mothman.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

public interface Operations<T extends Serializable> {

    T get(final long id);
    List<T> list();
    void insert(final T entity);
    T update(final T entity);
    void delete(final T entity);
    void deleteById(final long entityId);

}
