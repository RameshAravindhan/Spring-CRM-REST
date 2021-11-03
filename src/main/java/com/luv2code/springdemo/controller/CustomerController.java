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
        checkForValidCustomerID(customerID);
        return customerService.getCustomer(customerID);
    }

    @PostMapping("/customer")
    public List<Customer> addCustomer(@RequestBody Customer customer) {

        customer.setId(0);
        customerService.saveCustomer(customer);

        return customerService.getCustomers();
    }

    @DeleteMapping("/customers/{customerID}")
    public List<Customer> deleteCustomer(@PathVariable int customerID) {
        checkForValidCustomerID(customerID);
        customerService.deleteCustomer(customerID);
        return customerService.getCustomers();
    }

    private void checkForValidCustomerID(@PathVariable int customerID) {
        boolean ispresent = false;

        List<Customer> customerList = customerService.getCustomers();
        for (Customer theCustomer : customerList) {
            if (theCustomer.getId() == customerID) {
                ispresent = true;
                break;
            }
        }
        if (!ispresent) {
            throw new InvalidCustomerException("Invalid Customer ID, valid Customers are" + customerList);
        }
    }


}