package net.mothman.persistence.dao.impl;

import net.mothman.persistence.dao.ChildDao;
import net.mothman.persistence.dao.common.AbstractHibernateDao;
import net.mothman.persistence.model.Child;

import org.springframework.stereotype.Repository;

@Repository
public class ChildDaoImpl extends AbstractHibernateDao<Child> implements ChildDao {

    public ChildDaoImpl() {}

}
