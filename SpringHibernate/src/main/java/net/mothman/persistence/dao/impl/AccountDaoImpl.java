package net.mothman.persistence.dao.impl;

import static org.springframework.util.Assert.notNull;
import net.mothman.persistence.dao.AccountDao;
import net.mothman.persistence.dao.common.AbstractHibernateDao;
import net.mothman.persistence.model.user.Account;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends AbstractHibernateDao<Account> implements AccountDao {
	
	/** (non-Javadoc)
	 * @see AccountDao#getByUsername(String) 
	 */
	@Override
	public Account getByUsername(String username) {
		notNull(username, "username can't be null");
		return (Account) getSession()
			.getNamedQuery("account.byUsername")
			.setParameter("username", username)
			.uniqueResult();
	}
	
}
