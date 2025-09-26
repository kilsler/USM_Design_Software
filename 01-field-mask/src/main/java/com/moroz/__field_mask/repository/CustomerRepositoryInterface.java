package com.moroz.__field_mask.repository;

import com.moroz.__field_mask.entity.Customer;

import java.util.List;

public interface CustomerRepositoryInterface {
    void save(Customer customer);

    boolean delete(int customerId);

    Customer findById(int id);

    List<Customer> findAll();

    List<Customer> findByName(String name);

}
