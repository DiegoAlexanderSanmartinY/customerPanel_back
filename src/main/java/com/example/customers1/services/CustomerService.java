package com.example.customers1.services;

import com.example.customers1.entities.Customer;
import com.example.customers1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;


    public Customer getCustomer( Integer id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) return customer.get();
        return null;
    }

    public List<Customer> getAllCustomers() {

        List<Customer> list = new ArrayList<>();
        Iterable<Customer> customers = repository.findAll();
        customers.forEach(customer -> list.add(customer));
        return list;
    }

    public void removeCustomer( Integer id) {
        repository.deleteById(id);


    }

    public Customer addCustomer(Customer customer) {

        repository.save(customer);

        return customer;
    }

    public void updateCustomer(Integer id, Customer customer) {

        customer.setId(id);
        repository.save(customer);


    }

    public List<Customer> searchCustomer( String email,
                                          String address) {

       return repository.findByEmailOrAddress(email, address);


    }

}
