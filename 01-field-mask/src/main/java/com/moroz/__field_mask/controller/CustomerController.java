package com.moroz.__field_mask.controller;

import com.moroz.__field_mask.entity.CopyRequestDTO;
import com.moroz.__field_mask.entity.Customer;
import com.moroz.__field_mask.entity.FieldMask;
import com.moroz.__field_mask.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    CustomerRepository repository = new CustomerRepository();

    @GetMapping("/customer")
    public List<Customer> getCustomer(@RequestBody(required = false) FieldMask mask) {
        for (Customer customer : repository.findAll()) {
            customer.printCustomer(mask);
        }
        return repository.findAll();
    }

    @GetMapping("/customer/id/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId) {
        return repository.findById(customerId);
    }

    @GetMapping("/customer/name/{customerName}")
    public List<Customer> getCustomerByName(@PathVariable String customerName) {
        return repository.findByName(customerName);
    }

    @PostMapping("customer")
    public void updateCustomers(@RequestBody Customer customer) {
        repository.save(customer);
        System.out.println("Customer created: " + customer.toString());
    }

    @DeleteMapping("customer/id/{customerId}")
    public boolean removeById(@PathVariable int customerId) {
        return repository.delete(customerId);
    }

    @PostMapping("customer/copy")
    public List<Customer> copyData(@RequestBody CopyRequestDTO requestDTO) {
        if (requestDTO == null || requestDTO.copyCustomer == null
                || requestDTO.compareMask == null || requestDTO.copyMask == null) {
            return null;
        }

        return repository.copyDataToMatching(requestDTO.copyCustomer, requestDTO.compareMask, requestDTO.copyMask);
    }

    @PostMapping("customer/merge")
    public List<Customer> mergeData(@RequestBody FieldMask mask) {
        return repository.merge(mask);
    }

}


// ! Сделайте domain model класс с хотя бы 5 полями, среди которых int, string, float, enum.
// ! Сделайте абстракцию под базу данных, хранящую эти объекты. Имплементация не принципиальна, можно хранить их просто в массиве под капотом.
// ! Создайте класс маски полей (сначала как bool).
// ! Реализуйте метод в абстракции базы данных, которая совершает поиск всех объектов по одному из полей (например, FindByName(string)).
// ! Реализуйте статическую функцию, которая печатает в консоль значения полей, согласно переданной маски.
//
// ! Реализуйте это в виде JSON REST API (возврат объекта не со всеми полями) (ASP.NET Core).
// ! Создайте метод для merge всех объектов в базе данных, с равными значениями полей, согласно переданной маске.
// ! Создайте метод копирования данных для всех объектов, которые равны, согласно переданной маске. Данные берите с другого объекта, переданным параметром. Какие именно поля нужно скопировать задается еще одной маской.
//Переделайте маску полей на основе битов.
//Сделайте 3 метода, работающие на основе масок, комбинирующих их тем или иным полезным образом.

