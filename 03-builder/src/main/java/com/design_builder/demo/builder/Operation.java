package com.design_builder.demo.builder;
@FunctionalInterface
public interface Operation {
        ComponentBuilder execute(ComponentBuilder cb);
}
