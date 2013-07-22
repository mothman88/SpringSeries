package net.mothman.persistence.controller;

import java.util.List;

import net.mothman.persistence.model.user.Account;
import net.mothman.persistence.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/accounts")
public class AccountController {
	
	public static final String ACCOUNT_HOME = "list";
	public static final String ACCOUNT_FORM = "account";
	
	@Autowired 
	private AccountService accountService;
	
	@RequestMapping(value = "/get/{username}", method = RequestMethod.GET)
	public String getAccountInfo(@PathVariable("username") String username, Model model) {
		Account account = accountService.getByUsername(username);
		
		model.addAttribute(account);
		return ACCOUNT_HOME;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listAccount(Model model) {
		List<Account> accounts = accountService.list();
		model.addAttribute("accounts", accounts);
		return ACCOUNT_HOME;
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addAccount(@ModelAttribute Account account, Model model) {
		accountService.insert(account);
		
		model.addAttribute("message", "Account was successfully added.");
		return ACCOUNT_HOME;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editTeamPage(@PathVariable Integer id, Model model) {
		Account account = accountService.get(id);
		
		model.addAttribute("account", account);
		return ACCOUNT_FORM;
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@ModelAttribute Account account, @PathVariable Integer id, Model model) {
		accountService.update(account);
		
		model.addAttribute("message", "Account was successfully updated.");
		return ACCOUNT_HOME;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteTeam(@PathVariable Integer id, Model model) {
		accountService.deleteById(id);

		model.addAttribute("message", "Account was successfully deleted.");
		return ACCOUNT_HOME;
	}
	
}
