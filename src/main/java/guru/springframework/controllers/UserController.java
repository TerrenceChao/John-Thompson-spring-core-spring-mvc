package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;

@Controller
@RequestMapping(value = "/user/v1.0")
public class UserController {

	@Autowired
	private UserService userService;
	
	//read all
	@RequestMapping("/list")
	public String listAll(Model model) {
		model.addAttribute("users", this.userService.listAll());
		return "user/list";
	}
	
	//read
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("user", this.userService.getById(id));
		return "user/show";
	}
	
	//create
	@RequestMapping("/new")
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "user/form";
	}
	
	//update
	@RequestMapping("/edit/{id}")
	public String update(@PathVariable Integer id, Model model) {
		model.addAttribute("user", this.userService.getById(id));
		return "user/form";
	}
	
	//confirm create/update
	@RequestMapping(value = "/confirmUser", method = RequestMethod.POST) 
	public String saveOrUpdate(User user) {
		User savedUser = this.userService.saveOrUpdate(user);
		return "redirect:/user/v1.0/show/" + savedUser.getId();
	}
	
	//delete
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.userService.delete(id);
		return "redirect:/user/v1.0/list";
	}
}
