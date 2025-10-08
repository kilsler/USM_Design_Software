package org.example;

import org.example.steps.IPipelineStep;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private List<IPipelineStep> steps = new ArrayList<>();

    public void addStep(IPipelineStep step) {
        steps.add(step);
    }

    public void execute(AccountContext context) {
        for (IPipelineStep step : steps) {
            if (context.isDone()) break;
            step.execute(context);
        }
    }

}
