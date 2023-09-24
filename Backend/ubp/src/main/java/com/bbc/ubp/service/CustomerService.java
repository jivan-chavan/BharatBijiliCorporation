package com.bbc.ubp.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bbc.ubp.dao.CustomerDao;
import com.bbc.ubp.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
    private CSVReaderService csvReaderService;

	public String addCustomer(Customer newCustomerRequest) {
		
		return customerDao.addCustomer(newCustomerRequest);
	}
	
	public Customer findCustomerByCustomerId(long customerId) {
		return customerDao.findCustomerByCustomerId(customerId);
	}
	
	public String addCustomersInBulk(MultipartFile file) throws IOException {
		List<Customer> customerList= new ArrayList<>();
    	if (!file.isEmpty()) {
            try (Reader reader = new InputStreamReader(file.getInputStream())) {
                customerList= csvReaderService.readCustomerCSVData(reader);
                
            }
        }
		return customerDao.addCustomersInBulk(customerList);
	}
	 public String updateCustomer(Customer updatedCustomer) {
	        return customerDao.updateCustomer(updatedCustomer);
	    }

	    public String deleteCustomer(long customerId) {
	        return customerDao.deleteCustomer(customerId);
	    }
	    public List<Customer> getAllCustomers() {
	        return customerDao.getAllCustomers();
	    }
}
