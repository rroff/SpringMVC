package us.roff.springtutorial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.services.CustomerService;

@RequestMapping("/customer")
@Controller
public class CustomerController {

	private CustomerService customerService;
	
	@Autowired
	public void setProductService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping({"/list", "/"})
	public String listCustomers(Model model) {
		
		model.addAttribute("customers", customerService.listAll());
		
		return "customer/list";
	}
	
	@RequestMapping("/show/{id}")
	public String getProduct(@PathVariable Integer id, Model model) {
		
		model.addAttribute("customer", customerService.getById(id));
		
		return "customer/show";
		
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		model.addAttribute("customer", customerService.getById(id));
		
		return "customer/customerform";
	}
	
	@RequestMapping("/new")
	public String newProduct(Model model) {
		
		model.addAttribute("customer", new Customer());
		
		return "customer/customerform";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrUpdateProduct(Customer customer) {
		
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		
		return "redirect:/customer/show/" + savedCustomer.getId();
	}
	
	@RequestMapping("/delete/{id}")
	public String edit(@PathVariable Integer id) {
		
		customerService.deleteById(id);
		
		return "redirect:/customer/list";
	}
}
