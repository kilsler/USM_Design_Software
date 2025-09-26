package com.moroz.__field_mask.repository;

import com.moroz.__field_mask.entity.Customer;
import com.moroz.__field_mask.entity.FieldMask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerRepository implements CustomerRepositoryInterface {
    private final List<Customer> customers = new ArrayList<>(List.of(
            new Customer(1, "Ivan", Customer.EntityType.NATURAL_PERSON, 100.5f, true),
            new Customer(2, "Ivan", Customer.EntityType.NATURAL_PERSON, 1400.5f, true),
            new Customer(3, "Gregor", Customer.EntityType.LEGAL_ENTITY, 1030.5f, false),
            new Customer(4, "Viktor", Customer.EntityType.LEGAL_ENTITY, 1200.5f, true),
            new Customer(5, "Ivan", Customer.EntityType.LEGAL_ENTITY, 1500.5f, true),
            new Customer(6, "Oleg", Customer.EntityType.LEGAL_ENTITY, 5000.0f, false)
    ));


    private static boolean isEqual(FieldMask mask, Customer base, Customer compare) {
        boolean isEqual = true;
        if (mask.id && base.getId() != compare.getId())
            isEqual = false;
        if (mask.name && !base.getName().equals(compare.getName()))
            isEqual = false;
        if (mask.entityType && base.getEntityType() != compare.getEntityType())
            isEqual = false;
        if (mask.balance && base.getBalance() != compare.getBalance())
            isEqual = false;
        if (mask.active && base.isActive() != compare.isActive())
            isEqual = false;
        return isEqual;
    }

    public List<Customer> copyDataToMatching(Customer copyCustomer, FieldMask compareMask, FieldMask copyMask) {
        if (copyCustomer == null || compareMask == null || copyMask == null) {
            return null;
        }
        for (Customer customer : customers) {
            if (isEqual(compareMask, customer, copyCustomer)) {
                if (copyMask.id) customer.setId(copyCustomer.getId());
                if (copyMask.name) customer.setName(copyCustomer.getName());
                if (copyMask.entityType) customer.setEntityType(copyCustomer.getEntityType());
                if (copyMask.balance) customer.setBalance(copyCustomer.getBalance());
                if (copyMask.active) customer.setActive(copyCustomer.isActive());
            }
        }

        return customers;
    }

    public List<Customer> merge(FieldMask mask) {
        if (mask == null) {
            return null;
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer base = customers.get(i);
            for (int j = i + 1; j < customers.size(); j++) {
                Customer compare = customers.get(j);
                if (isEqual(mask, base, compare)) {
                    base.setBalance(base.getBalance() + compare.getBalance());
                    customers.remove(j);
                    j--;
                }
            }
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean delete(int customerId) {
        return false;
    }

    @Override
    public Customer findById(int id) {
        return customers.stream().filter(customer -> {
            return customer.getId() == id;
        }).findFirst().orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customers.stream().toList();
    }

    @Override
    public List<Customer> findByName(String name) {
        return customers.stream().filter(customer -> customer.getName().equals(name)).collect(Collectors.toList());
    }
}
