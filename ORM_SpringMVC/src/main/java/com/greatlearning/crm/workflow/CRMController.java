package com.greatlearning.crm.workflow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.crm.dataservice.CustomerDataService;
import com.greatlearning.crm.domain.entities.Customer;

@Controller
@RequestMapping(value = {"/","/customers"})
public class CRMController {

	// Autowire Data Service class
	@Autowired
	private CustomerDataService customerDataService;
	
	@RequestMapping(value = {"/","/list"})
	public String listCustomers(Model model) {
		List<Customer> customers = customerDataService.getAllCustomers();
		model.addAttribute("customers", customers);
		return "CustomerList";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);

		
		return "CustomerEnrolment";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int customerId, Model theModel) {

		
		Customer customer = customerDataService.findCustomerById(customerId);

		
		theModel.addAttribute("customer", customer);

	
		return "CustomerEnrolment";
	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println("Input Customer Id is: " + id);
		Customer customer;
		
		
		if (id != 0) {
			customer = customerDataService.findCustomerById(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
		} else
			
			customer = new Customer(firstName, lastName, email);
		
		
		customerDataService.saveCustomerDetails(customer);

		
		return "redirect:/customers/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int customerId) {

		customerDataService.deleteCustomerById(customerId);

		return "redirect:/customers/list";
	}

}
