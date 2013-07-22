package net.mothman.persistence.dao.impl;

import net.mothman.persistence.dao.ParentDao;
import net.mothman.persistence.dao.common.AbstractHibernateDao;
import net.mothman.persistence.model.Parent;

import org.springframework.stereotype.Repository;

@Repository
public class ParentDaoImpl extends AbstractHibernateDao<Parent> implements ParentDao {

    public ParentDaoImpl() {}

}
