package com.euris.dbexample.controllers;

import com.euris.dbexample.domain.Customer;
import com.euris.dbexample.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public List<Customer> getAll() { return customerService.getList(); }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable(name="id")Long id) {
        return customerService.getById(id);
    }

    @PostMapping("/customer")
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        System.out.println("Customer saved");
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable(name="id")Long id) {
        customerService.deleteCustomer(id);
        System.out.println("Customer deleted successfully");
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@RequestBody Customer customer,
                               @PathVariable(name="id")Long id){
        Customer cust = customerService.getById(id);
        if(cust != null) {
            customerService.updateCustomer(customer);
        }
    }
}
