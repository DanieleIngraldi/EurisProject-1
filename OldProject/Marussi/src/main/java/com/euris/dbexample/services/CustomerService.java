package com.euris.dbexample.services;

import com.euris.dbexample.domain.Customer;

import java.util.List;


public interface CustomerService {
    List<Customer> getList();

    Customer getById(Long id);

    void saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    void updateCustomer(Customer customer);

    void saveCustomer(String firstName, String lastName, String career);
}