package net.mothman.persistence.service;

import net.mothman.persistence.dao.common.Operations;
import net.mothman.persistence.model.user.Account;

public interface AccountService extends Operations<Account> {

	Account getByUsername(String username);
}
