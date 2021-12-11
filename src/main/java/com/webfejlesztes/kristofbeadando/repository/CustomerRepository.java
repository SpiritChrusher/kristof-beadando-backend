package com.webfejlesztes.kristofbeadando.repository;

import com.webfejlesztes.kristofbeadando.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findCustomerById(Integer id);
    void deleteCustomerById(Integer id);
}
