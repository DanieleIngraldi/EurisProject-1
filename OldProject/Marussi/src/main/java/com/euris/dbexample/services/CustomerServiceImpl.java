package com.euris.dbexample.services;

import com.euris.dbexample.CustomerRepository;
import com.euris.dbexample.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

        @Autowired
        private CustomerRepository customerRepository;

        public void setCustomerRepository(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public List<Customer> getList() {
            List<Customer> customers = customerRepository.findAll();
            return customers;
        }

        public Customer getById(Long employeeId) {
            Optional<Customer> optEmp = customerRepository.findById(employeeId);
            return optEmp.get();
        }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void saveCustomer(String firstName, String lastName, String career) {
            Customer newCust = new Customer(firstName, lastName, career);
            customerRepository.save(newCust);
        }

        /*public void saveCustomer(Customer employee){customerRepository.save(employee);}*/

        public void deleteCustomer(Long employeeId){
            customerRepository.deleteById(employeeId);
        }

        public void updateCustomer(Customer customer) {
            customerRepository.save(customer);
        }

}
