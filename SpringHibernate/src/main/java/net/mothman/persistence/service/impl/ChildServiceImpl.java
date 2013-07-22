package net.mothman.persistence.service.impl;

import net.mothman.persistence.dao.ChildDao;
import net.mothman.persistence.dao.common.Operations;
import net.mothman.persistence.model.Child;
import net.mothman.persistence.service.ChildService;
import net.mothman.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildServiceImpl extends AbstractService<Child> implements ChildService {

    @Autowired
    private ChildDao dao;

    public ChildServiceImpl() {
        super();
    }

    @Override
    protected Operations<Child> getDao() {
        return dao;
    }

}
