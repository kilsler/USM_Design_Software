package com.design_builder.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String brand;
    private final String model;

    @ElementCollection
    @CollectionTable(name = "computer_components", joinColumns = @JoinColumn(name = "computer_id"))
    @Column(name = "component_id")
    private final List<Long> componentIds;

    public Computer(Long id, String brand, String model, List<Long> componentIds) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.componentIds = List.copyOf(componentIds);
    }

    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public List<Long> getComponentIds() { return componentIds; }

    @Override
    public String toString() {
        return "Computer{id=%d, brand='%s', model='%s', components=%s}".formatted(
                id, brand, model, componentIds);
    }
}

