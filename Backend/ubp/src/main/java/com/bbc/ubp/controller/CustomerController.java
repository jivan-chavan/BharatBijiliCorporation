package com.bbc.ubp.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbc.ubp.entity.Customer;
import com.bbc.ubp.service.CustomerService;
@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/upload")
    public String addCustomersInBulk(@RequestParam("file") MultipartFile file) throws IOException {
        return customerService.addCustomersInBulk(file);
    }

    @PostMapping("/addcustomer")
    public String addCustomer(@RequestBody Customer newCustomerRequest) throws Exception {

        return customerService.addCustomer(newCustomerRequest);
    }

    @GetMapping("/{customerid}")
    public Customer findCustomerByCustomerId(@PathVariable("customerid") long customerId) {
        return customerService.findCustomerByCustomerId(customerId);
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody Customer updatedCustomer) throws Exception {
        return customerService.updateCustomer(updatedCustomer);
    }

    @DeleteMapping("/delete/{customerid}")
    public String deleteCustomer(@PathVariable("customerid") long customerId) throws Exception {
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
