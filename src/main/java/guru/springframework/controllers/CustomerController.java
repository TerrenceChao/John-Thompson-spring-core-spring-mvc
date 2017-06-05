package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;

@Controller
@RequestMapping(value = "/customer/v1.0")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	//read all
	@RequestMapping("/list")
	public String listAll(Model model) {
		model.addAttribute("customers", this.customerService.listAll());
		return "customer/list";
	}
	
	//read
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", this.customerService.getById(id));
		return "customer/show";
	}
	
	//create
	@RequestMapping("/new")
	public String create(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer/form";
	}
	
	//update
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", this.customerService.getById(id));
		return "customer/form";
	}
	
	//confirm create/update
	@RequestMapping(value = "/confirmForm", method = RequestMethod.POST)
	public String saveOrUpdate(Customer customer) {
		Customer savedCustomer = this.customerService.saveOrUpdate(customer);
		return "redirect:/customer/v1.0/show/" + savedCustomer.getId();
	}
	
	//delete
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.customerService.delete(id);
		return "redirect:/customer/v1.0/list";
	}
}
