package com.webfejlesztes.kristofbeadando.controller;

import com.webfejlesztes.kristofbeadando.models.Customer;
import com.webfejlesztes.kristofbeadando.models.RequestCustomer;
import com.webfejlesztes.kristofbeadando.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
    List<Customer> customers = new ArrayList<>();

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestParam String first,
                              @RequestParam String last,
                              @RequestParam Integer age) {
        if (first.isEmpty() || last.isEmpty() || age < 6 || age > 99){
            return "Please enter valid informations!";
        }
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customer.setAge(age);
        customerRepository.save(customer);
        customers.add(customer);
        return "Added new customer to repo!";
    }

    @PostMapping("/addjson")
    public String addJsonCustomer(@RequestBody RequestCustomer requestCustomer)
    {
        Customer customer = new Customer(requestCustomer.getFirstName(),
                requestCustomer.getLastName(),
                requestCustomer.getAge());
        customerRepository.save(customer);
        customers.add(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@RequestParam Integer id) {

        return customerRepository.findCustomerById(id);
    }

    @PostMapping("/update")
    public Customer updateCustomerById(@RequestParam Integer id,
                                       @RequestParam String first,
                                       @RequestParam String last,
                                       @RequestParam String age) {
        Integer Age = Integer.parseInt(age);
        if (first.isEmpty() || last.isEmpty() || Age < 6 || Age > 99){
            return null;
        }
        var customer = findCustomerById(id);
        customers.remove(id-1);

        customer.setFirstName(first);
        customer.setLastName(last);
        customer.setAge(Age);
        customerRepository.save(customer);

        customers.add(customer);
        return customer;
    }
    @PostMapping("/updatejson")
    public Customer updateCustomerByIdJson(@RequestBody Integer id,
                                       @RequestBody String first,
                                       @RequestBody String last,
                                       @RequestBody String age)
    {
        Integer Age = Integer.parseInt(age);
        if (first.isEmpty() || last.isEmpty() || Age < 6 || Age > 99){
            return null;
        }
        var customer = findCustomerById(id);
        customers.remove(id-1);

        customer.setFirstName(first);
        customer.setLastName(last);
        customer.setAge(Age);
        customerRepository.save(customer);

        customers.add(customer);
        return customer;
    }
}