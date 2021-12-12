package com.webfejlesztes.kristofbeadando.controller;

import com.webfejlesztes.kristofbeadando.models.Customer;
import com.webfejlesztes.kristofbeadando.models.RequestCustomer;
import com.webfejlesztes.kristofbeadando.models.UpdatedCustomer;
import com.webfejlesztes.kristofbeadando.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @CrossOrigin(origins = "*")
    @GetMapping("/find/{id}")
    public Customer findCustomerById(@RequestParam Integer id) {
        if (id < 1)
        {return null;}

        return customerRepository.findCustomerById(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/findjson")
    public Customer findCustomerById(@RequestBody String id) {
        Integer Id = Integer.parseInt(id);

        if (Id < 1){
            return null;
        }

        return customerRepository.findCustomerById(Id);
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
    public Customer updateCustomerByIdJson(@RequestBody UpdatedCustomer updatedCustomer)
    {
        Integer Age = Integer.parseInt(updatedCustomer.getAge());
        Integer Id = Integer.parseInt(updatedCustomer.getId());
        if (updatedCustomer.getFirstName().isEmpty() ||
                updatedCustomer.getLastName().isEmpty() || Age < 6
                || Age > 99 || Id < 1){
            return null;
        }
        var customer = findCustomerById(Id);
        customers.remove(Id-1);

        customer.setId(Id);
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getFirstName());
        customer.setAge(Age);
        customerRepository.save(customer);

        customers.add(customer);
        return customer;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/delete/{id}")
    @Transactional
    public String deleteUser(@RequestParam("id") String id){
        Integer Id = Integer.parseInt(id);
        if (Id < 1) {
            return null;
        }
        customerRepository.deleteCustomerById(Id);
        return "entity removed!";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/deletejson")
    @Transactional
    public String deleteUserJson(@RequestBody String id){
        Integer Id = Integer.parseInt(id);
        if (Id < 1) {
            return null;
        }
        customerRepository.deleteCustomerById(Id);
        return "entity removed!";
    }

    @GetMapping("/deleteall")
    public String deleteUser(){
        customerRepository.deleteAll();
        return "All entities removed!";
    }
}