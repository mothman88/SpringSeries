package net.mothman.persistence.service.impl;

import net.mothman.persistence.dao.FooDao;
import net.mothman.persistence.dao.common.Operations;
import net.mothman.persistence.model.Foo;
import net.mothman.persistence.service.FooService;
import net.mothman.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl extends AbstractService<Foo> implements FooService {

    @Autowired
    private FooDao dao;

    public FooServiceImpl() {
        super();
    }

    // API

    @Override
    protected Operations<Foo> getDao() {
        return dao;
    }

}
