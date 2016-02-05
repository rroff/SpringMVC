package us.roff.springtutorial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.services.CustomerService;

@Controller
public class CustomerController {

	private CustomerService customerService;
	
	@Autowired
	public void setProductService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping("/customers")
	public String listCustomers(Model model) {
		
		model.addAttribute("customers", customerService.listAllCustomers());
		
		return "customers";
	}
	
	@RequestMapping("/customer/{id}")
	public String getProduct(@PathVariable Integer id, Model model) {
		
		model.addAttribute("customer", customerService.getCustomerById(id));
		
		return "customer";
		
	}
	
	@RequestMapping("/customer/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		model.addAttribute("customer", customerService.getCustomerById(id));
		
		return "customerform";
	}
	
	@RequestMapping("/customer/new")
	public String newProduct(Model model) {
		
		model.addAttribute("customer", new Customer());
		
		return "customerform";
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public String saveOrUpdateProduct(Customer customer) {
		
		Customer savedCustomer = customerService.saveOrUpdateCustomer(customer);
		
		return "redirect:/customer/" + savedCustomer.getId();
	}
	
	@RequestMapping("/customer/delete/{id}")
	public String edit(@PathVariable Integer id) {
		
		customerService.deleteCustomerById(id);
		
		return "redirect:/customers";
	}
}
