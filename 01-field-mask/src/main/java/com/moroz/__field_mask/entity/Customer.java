package com.moroz.__field_mask.entity;

public final class Customer {
    private int id;
    private String name;
    private EntityType entityType;
    private float balance;
    private boolean active;

    public Customer(int id, String name, EntityType entityType, float balance, boolean active) {
        this.id = id;
        this.name = name;
        this.entityType = entityType;
        this.balance = balance;
        this.active = active;
    }

    public void printCustomer(FieldMask fieldMask) {
        if (fieldMask == null) {
            {
                fieldMask = new FieldMask(true, true, true, true, true);
            }
        } else {
            System.out.println("Customer:{ ");
            if (fieldMask.id) {
                System.out.println("Customer id: " + id);
            }
            if (fieldMask.name) {
                System.out.println("Customer name: " + name);
            }
            if (fieldMask.entityType) {
                System.out.println("Customer entity type: " + entityType);
            }
            if (fieldMask.balance) {
                System.out.println("Customer balance: " + balance);
            }
            if (fieldMask.active) {
                System.out.println("Customer active: " + active);
            }
            System.out.println("} ");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entityType=" + entityType +
                ", balance=" + balance +
                ", active=" + active +
                '}';
    }

    public enum EntityType {
        NATURAL_PERSON,
        LEGAL_ENTITY
    }


}

