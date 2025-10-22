package com.design_builder.demo.repository;

import com.design_builder.demo.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {}
