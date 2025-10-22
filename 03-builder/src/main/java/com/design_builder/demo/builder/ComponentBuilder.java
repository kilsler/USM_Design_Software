package com.design_builder.demo.builder;


import com.design_builder.demo.entity.Component;

public class ComponentBuilder {
    private String name;
    private String type;
    private double price;
    private double warrantyYears = 1.0;

    public ComponentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ComponentBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ComponentBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ComponentBuilder setWarrantyYears(double warrantyYears) {
        this.warrantyYears = warrantyYears;
        return this;
    }

    public Component build() {
        if (name == null || name.isBlank())
            throw new IllegalStateException("Имя компонента не указано");
        if (type == null || type.isBlank())
            throw new IllegalStateException("Тип компонента не указан");
        if (price <= 0)
            throw new IllegalStateException("Цена должна быть положительной");

        return new Component(null, name, type, price, warrantyYears);
    }
}

