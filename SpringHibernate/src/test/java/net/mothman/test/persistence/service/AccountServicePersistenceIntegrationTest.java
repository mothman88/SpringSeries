package net.mothman.test.persistence.service;

import java.util.List;

import net.mothman.persistence.model.user.Account;
import net.mothman.persistence.service.AccountService;
import net.mothman.test.PersistenceConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class AccountServicePersistenceIntegrationTest {

    @Autowired
    private AccountService service;

    // tests
    
//    @Test
    public final void createUser() {
    	Account a = new Account();
    	a.setUsername("username");
    	a.setPassword("password");
    	a.setEnabled(true);
    	a.setEmail("mail@gmail.com");
    	
    	service.insert(a);
    	Assert.assertNotNull(a.getId());
    }
    
    @Test
    public final void listUser() {
    	List<Account> accounts = service.list();
    	
    	for(Account a : accounts)
    		System.out.println(a.getId() + " " + a.getUsername());
    	
    	Assert.assertNotNull(accounts);
    }
    

}
