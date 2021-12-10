package com.webfejlesztes.kristofbeadando;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    List<Customer> customers = new ArrayList<>();

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestParam String first,
                              @RequestParam String last,
                              @RequestParam Integer age) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customer.setAge(age);
        customerRepository.save(customer);
        customers.add(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        //return customerRepository.findAll();
        return customers;
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@RequestParam Integer id) {

        return customerRepository.findCustomerById(id);
    }

    @PostMapping("/update")
    public Customer updateCustomerById(@RequestParam Integer id,
                                       @RequestParam String first,
                                       @RequestParam String last,
                                       @RequestParam Integer age) {
        var customer = findCustomerById(id);
        var oldcustomer = customers.stream().filter(x -> x.getId() == id);
        customers.remove(id-1);

        customer.setFirstName(first);
        customer.setLastName(last);
        customer.setAge(age);
        customerRepository.save(customer);

        customers.add(customer);
        return customer;
    }
    @PostMapping("/remove/{id:\\d+}")
    public String deleteCustomerById(@PathVariable Integer id) {
        //ustomerRepository.deleteCustomerById(id);
        customers.remove(id-1);
        return "Removed item at index:" + id;
    }

    @PostMapping("/removeall")
    public List<Customer> deleteAll() {
        //customerRepository.deleteAll();
        customers.removeAll(this.customers);
        return customers;
    }
}