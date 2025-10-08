package org.example.steps;

import org.example.AccountContext;

public class DepositStep implements IPipelineStep {
    private final double amount;

    public DepositStep(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(AccountContext context) {
        if (context.isDone()) return;
        context.setBalance(context.getBalance() + amount);
        context.setHistory(context.getHistory().append(introspect(context.getHistory())));
    }

    @Override
    public StringBuilder introspect(StringBuilder sb) {
        return sb.append("Step: Deposited ").append(amount).append("\n");
    }
}