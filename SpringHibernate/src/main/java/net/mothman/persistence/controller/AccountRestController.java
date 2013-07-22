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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/accounts")
public class AccountRestController {
	
	@Autowired 
	private AccountService accountService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Account> listAccount(Model model) {
		return accountService.list();
	}
	
	@RequestMapping(value = "/get/{username}", method = RequestMethod.GET)
	public @ResponseBody Account getAccountInfo(@PathVariable("username") String username, Model model) {
		return accountService.getByUsername(username);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody String addAccount(@ModelAttribute Account account, Model model) {
		accountService.insert(account);
		return "Account was successfully added.";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public @ResponseBody Account editTeamPage(@PathVariable Integer id, Model model) {
		return accountService.get(id);
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public @ResponseBody String update(@ModelAttribute Account account, @PathVariable Integer id, Model model) {
		accountService.update(account);
		return "Account was successfully updated.";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public @ResponseBody String deleteTeam(@PathVariable Integer id, Model model) {
		accountService.deleteById(id);
		return "Account was successfully deleted.";
	}
	
}
