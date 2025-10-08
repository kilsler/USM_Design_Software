import org.example.*;
import org.example.steps.DeactivateIfLowBalanceStep;
import org.example.steps.DepositStep;
import org.example.steps.WithdrawStep;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PipelineTest {

    @Test
    void testPipelineNormalExecution() {
        AccountContext user = new AccountContext("Bob", 100, true);

        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new DepositStep(50));                     // 50 -> 150
        pipeline.addStep(new WithdrawStep(30));                    // -30 -> 120
        pipeline.addStep(DeactivateIfLowBalanceStep.getInstance()); // balance > 0 -> active

        pipeline.execute(user);

        assertEquals(120, user.getBalance());
        assertTrue(user.isActive());
        assertFalse(user.isDone());
        assertTrue(user.getHistory().toString().contains("Deposited"));
        assertTrue(user.getHistory().toString().contains("Withdrew"));
        assertTrue(user.getHistory().toString().contains("Account remains active"));
    }

    @Test
    void testPipelineStopsWhenBalanceNegative() {
        AccountContext user = new AccountContext("Alice", 100, true);

        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new DepositStep(50));  // +50 -> 150
        pipeline.addStep(new WithdrawStep(200));      // -200 -> -50
        pipeline.addStep(DeactivateIfLowBalanceStep.getInstance()); //isDone = true

        pipeline.execute(user);
        assertTrue(user.isDone());
        assertEquals(-50, user.getBalance());
        assertTrue(user.isActive());

        String history = user.getHistory().toString();
        assertTrue(history.contains("Withdrew"));
        assertTrue(history.contains("Balance below zero"));
        assertFalse(history.contains("Account deactivated"));
    }

    @Test
    void testSingletonInstances() {
        DeactivateIfLowBalanceStep step1 = DeactivateIfLowBalanceStep.getInstance();
        DeactivateIfLowBalanceStep step2 = DeactivateIfLowBalanceStep.getInstance();
        assertSame(step1, step2, "DeactivateIfLowBalanceStep должен быть Singleton");
    }

    @Test
    void testDeactivateStepDeactivates() {
        AccountContext user = new AccountContext("Charlie", -10, true);
        DeactivateIfLowBalanceStep deactivateStep = DeactivateIfLowBalanceStep.getInstance();

        deactivateStep.execute(user);

        assertFalse(user.isActive());
        assertTrue(user.getHistory().toString().contains("Account deactivated"));
    }

    @Test
    void testIsDoneBalanceTrigger() {
        AccountContext user = new AccountContext("Charlie", 10, true);
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new DepositStep(20));  // +50 -> 150
        pipeline.addStep(new WithdrawStep(40));      // -200 -> -50
        pipeline.addStep(DeactivateIfLowBalanceStep.getInstance()); //isDone = true
        pipeline.addStep(new DepositStep(20));
        pipeline.execute(user);
        assertEquals(user.getBalance(),-10);
        assertTrue(user.isDone());
    }
}

