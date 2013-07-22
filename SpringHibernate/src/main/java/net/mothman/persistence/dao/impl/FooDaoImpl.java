package net.mothman.persistence.dao.impl;

import net.mothman.persistence.dao.FooDao;
import net.mothman.persistence.dao.common.AbstractHibernateDao;
import net.mothman.persistence.model.Foo;

import org.springframework.stereotype.Repository;

@Repository
public class FooDaoImpl extends AbstractHibernateDao<Foo> implements FooDao {

//    @Autowired
//    private SessionFactory sessionFactory;

    public FooDaoImpl() {}

}
