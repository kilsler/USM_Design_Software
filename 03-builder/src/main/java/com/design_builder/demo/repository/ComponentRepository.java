package com.design_builder.demo.repository;

import com.design_builder.demo.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Long> {}
