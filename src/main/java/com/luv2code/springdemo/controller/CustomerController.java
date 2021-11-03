package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.exceptionhandler.InvalidCustomerException;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerID}")
    public Customer getCustomer(@PathVariable int customerID) {

        boolean b = true;

        List<Customer> cList = customerService.getCustomers();
        for (Customer c : cList) {
            if (c.getId() == customerID) {
                b = false;
                break;
            }
        }
        if (b) {
            throw new InvalidCustomerException("Invalid Customer ID, valid Customers are" + cList);
        }
        return customerService.getCustomer(customerID);
    }

    @PostMapping("/customer")
    public List<Customer> addCustomer(@RequestBody Customer customer) {

        customer.setId(0);
        customerService.saveCustomer(customer);

        return customerService.getCustomers();
    }

    @GetMapping("/customers/delete/{customerID}")
    public List<Customer> deleteCustomer(@PathVariable int customerID) {

        if (customerID > customerService.getCustomers().size() || customerID < 1) {
            throw new InvalidCustomerException("Invalid Customer ID");
        }
        customerService.deleteCustomer(customerID);
        return customerService.getCustomers();
    }


}