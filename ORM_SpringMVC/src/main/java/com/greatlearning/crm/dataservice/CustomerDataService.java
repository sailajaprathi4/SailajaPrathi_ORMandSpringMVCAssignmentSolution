package com.greatlearning.crm.dataservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.crm.domain.entities.Customer;

@Service
public interface CustomerDataService {

	
	public List<Customer> getAllCustomers();

	
	public Customer findCustomerById(int id);

	
	public void saveCustomerDetails(Customer customer);

	public void deleteCustomerById(int id);

}
