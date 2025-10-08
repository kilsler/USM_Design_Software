package org.example.steps;

import org.example.AccountContext;

public class WithdrawStep implements IPipelineStep {
    private final double amount;

    public WithdrawStep(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(AccountContext context) {
        if (context.isDone()) return;
        context.setBalance(context.getBalance() - amount);
        context.setHistory(context.getHistory().append(introspect(context.getHistory())));
    }

    @Override
    public StringBuilder introspect(StringBuilder sb) {
        return sb.append("Step: Withdrew ").append(amount).append("\n");
    }
}
