package net.mothman.persistence.dao;

import net.mothman.persistence.dao.common.Operations;
import net.mothman.persistence.model.user.Account;

public interface AccountDao extends Operations<Account> {
	
	Account getByUsername(String username);
}
