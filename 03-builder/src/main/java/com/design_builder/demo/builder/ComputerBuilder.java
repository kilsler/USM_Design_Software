package com.design_builder.demo.builder;

import com.design_builder.demo.entity.Component;
import com.design_builder.demo.entity.Computer;
import java.util.*;
import java.util.function.Consumer;

public class ComputerBuilder {
    private String brand;
    private String model;
    private final List<Component> builtComponents = new ArrayList<>();
    private double defaultWarranty = 1.0;

    public ComputerBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ComputerBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public ComputerBuilder withDefaultWarranty(double years) {
        this.defaultWarranty = years;
        return this;
    }


    public ComputerBuilder addComponent(Operation cb) {
        ComponentBuilder componentBuilder = new ComponentBuilder();
        componentBuilder = cb.execute(componentBuilder);
        builtComponents.add(componentBuilder.build());
        return this;
    }

    public Computer build() {
        if (brand == null || brand.isBlank())
            throw new IllegalStateException("Бренд не указан");
        if (model == null || model.isBlank())
            throw new IllegalStateException("Модель не указана");
        if (builtComponents.isEmpty())
            throw new IllegalStateException("Нет компонентов");

        List<Long> ids = builtComponents.stream().map(c -> c.getId() == null ? -1L : c.getId()).toList();
        return new Computer(null, brand, model, ids);
    }

    public List<Component> getComponents() {
        return builtComponents;
    }
}

