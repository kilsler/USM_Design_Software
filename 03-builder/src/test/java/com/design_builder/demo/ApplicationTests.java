package com.design_builder.demo;

import com.design_builder.demo.builder.ComponentBuilder;
import com.design_builder.demo.builder.ComputerBuilder;
import com.design_builder.demo.entity.Component;
import com.design_builder.demo.entity.Computer;
import com.design_builder.demo.repository.ComponentRepository;
import com.design_builder.demo.repository.ComputerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private ComputerRepository computerRepo;

	@Autowired
	private ComponentRepository componentRepo;

	@Test
	void testBuilderSavesComputerAndComponents() {
		ComputerBuilder builder = new ComputerBuilder()
				.setBrand("ASUS")
				.setModel("ROG STRIX")
				.withDefaultWarranty(2)
				.addComponent(x -> x
						.setName("Intel Core i9")
						.setType("CPU")
						.setPrice(550))
				.addComponent(x->x
						.setName("RTX 4090")
						.setType("GPU")
						.setPrice(1900));

		List<Component> savedComponents = componentRepo.saveAll(builder.getComponents());
		Computer builtComputer = builder.build();
		Computer computer = new Computer(
				null,
				builtComputer.getBrand(),
				builtComputer.getModel(),
				savedComponents.stream()
						.map(Component::getId)
						.collect(Collectors.toList())
		);
		Computer savedComputer = computerRepo.save(computer);

		assertThat(savedComputer.getId()).isNotNull();
		assertThat(savedComputer.getBrand()).isEqualTo("ASUS");
		assertThat(savedComputer.getModel()).isEqualTo("ROG STRIX");

		List<Long> componentIds = savedComputer.getComponentIds();
		assertThat(componentIds).hasSize(2);

		Component cpu = componentRepo.findById(componentIds.getFirst()).orElseThrow();
		assertThat(cpu.getName()).isEqualTo("Intel Core i9");
		assertThat(cpu.getType()).isEqualTo("CPU");
	}
}
