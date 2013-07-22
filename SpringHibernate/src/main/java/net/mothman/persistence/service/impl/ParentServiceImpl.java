package net.mothman.persistence.service.impl;

import net.mothman.persistence.dao.ParentDao;
import net.mothman.persistence.dao.common.Operations;
import net.mothman.persistence.model.Parent;
import net.mothman.persistence.service.ParentService;
import net.mothman.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentServiceImpl extends AbstractService<Parent> implements ParentService {

    @Autowired
    private ParentDao dao;

    public ParentServiceImpl() {
        super();
    }

    // API

    @Override
    protected Operations<Parent> getDao() {
        return dao;
    }

}
