package org.example.steps;

import org.example.AccountContext;

public class DeactivateIfLowBalanceStep implements IPipelineStep {
    private static DeactivateIfLowBalanceStep instance;
    private final double limit = 0;

    private DeactivateIfLowBalanceStep() {}

    public static DeactivateIfLowBalanceStep getInstance() {
        if (instance == null) {
            instance = new DeactivateIfLowBalanceStep();
        }
        return instance;
    }

    @Override
    public void execute(AccountContext context) {
        if (context.isDone()) return;
        if (context.getBalance() < limit) {
            context.setActive(false);
            context.setDone(true);
            context.getHistory().append("Account deactivated (balance below ").append(limit).append(").\n");
        } else {
            context.getHistory().append("Account remains active.\n");
        }
    }

    @Override
    public StringBuilder introspect(StringBuilder sb) {
        return sb.append("Step (Singleton): Deactivate if balance < ").append(limit).append("\n");
    }
}