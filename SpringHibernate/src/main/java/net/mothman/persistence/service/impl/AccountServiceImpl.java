package net.mothman.persistence.service.impl;

import net.mothman.persistence.dao.AccountDao;
import net.mothman.persistence.dao.common.Operations;
import net.mothman.persistence.model.user.Account;
import net.mothman.persistence.service.AccountService;
import net.mothman.persistence.service.common.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends AbstractService<Account> implements AccountService {

    @Autowired
    private AccountDao dao;

    public AccountServiceImpl() {
        super();
    }

    @Override
    protected Operations<Account> getDao() {
        return dao;
    }

	@Override
	public Account getByUsername(String username) {
		return dao.getByUsername(username);
	}

}
