package org.example.steps;

import org.example.AccountContext;

public interface IPipelineStep {
    void execute(AccountContext context);
    StringBuilder introspect(StringBuilder sb);
}
