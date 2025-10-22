package com.design_builder.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String name;
    private final String type;
    private final double price;
    private final double warrantyYears;

    protected Component() {
        this.id = null;
        this.name = null;
        this.type = null;
        this.price = 0;
        this.warrantyYears = 0;
    }

    public Component(Long id, String name, String type, double price, double warrantyYears) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.warrantyYears = warrantyYears;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public double getWarrantyYears() { return warrantyYears; }

    @Override
    public String toString() {
        return "Component{id=%d, name='%s', type='%s', price=%.2f, warranty=%.1f}".formatted(
                id, name, type, price, warrantyYears);
    }
}
