package us.roff.springtutorial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.roff.springtutorial.domain.Order;
import us.roff.springtutorial.services.OrderService;

@RequestMapping("/order")
@Controller
public class OrderController {

	private OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@RequestMapping({"/list", "/"})
	public String listOrders(Model model) {
		
		model.addAttribute("orders", orderService.listAll());
		
		return "order/list";
	}
	
	@RequestMapping("/show/{id}")
	public String getProduct(@PathVariable Integer id, Model model) {
		
		model.addAttribute("order", orderService.getById(id));
		
		return "order/show";
		
	}
	
//	@RequestMapping("/edit/{id}")
//	public String edit(@PathVariable Integer id, Model model) {
//		
//		model.addAttribute("order", orderService.getById(id));
//		
//		return "order/orderform";
//	}
//	
//	@RequestMapping("/new")
//	public String newProduct(Model model) {
//		
//		model.addAttribute("order", new Order());
//		
//		return "order/orderform";
//	}
//	
//	@RequestMapping(method=RequestMethod.POST)
//	public String saveOrUpdateProduct(Order order) {
//		
//		Order savedOrder = orderService.saveOrUpdate(order);
//		
//		return "redirect:/order/show/" + savedOrder.getId();
//	}
	
	@RequestMapping("/delete/{id}")
	public String edit(@PathVariable Integer id) {
		
		orderService.deleteById(id);
		
		return "redirect:/order/list";
	}
}
