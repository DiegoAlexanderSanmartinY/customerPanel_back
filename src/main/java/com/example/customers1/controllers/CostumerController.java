package com.example.customers1.controllers;

import com.example.customers1.entities.Customer;
import com.example.customers1.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")//avoid connect with the front
public class CostumerController {

    @Autowired
    private CustomerService service;


    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return service.getCustomer(id);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @DeleteMapping ("/customer/{id}")
    public void removeCustomer(@PathVariable Integer id) {
        service.removeCustomer(id);

    }

    @PostMapping ("/customer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return service.addCustomer(customer);
    }

    @PutMapping ("/customer/{id}")
    public void updateCustomer(@PathVariable Integer id,
                               @RequestBody Customer customer) {
        service.updateCustomer(id, customer);

    }

    @GetMapping("/customer/search")
    public List<Customer> searchCustomer(@RequestParam(name = "email", required = false) String email,
                                         @RequestParam(name = "address", required = false) String address) {
        return service.searchCustomer(email, address);
    }

}
