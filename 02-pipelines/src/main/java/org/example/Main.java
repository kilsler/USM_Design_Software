package org.example;

import org.example.steps.DeactivateIfLowBalanceStep;
import org.example.steps.DepositStep;
import org.example.steps.WithdrawStep;

public class Main {
    public static void main(String[] args) {
        AccountContext user = new AccountContext("Alice", 100, true);

        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new DepositStep((10)));
        pipeline.addStep(new WithdrawStep(200));
        pipeline.addStep(DeactivateIfLowBalanceStep.getInstance());

        pipeline.execute(user);

        System.out.println("=== Execution History ===");
        System.out.println(user.getHistory());

        System.out.println("Final balance: " + user.getBalance());
        System.out.println("Account active: " + user.isActive());
        System.out.println("Pipeline stopped: " + user.isDone());
    }

}

//Используйте паттерн Singleton для того, чтобы избежать повторного создания PipelineStep-ов, независящих от внешнего контекста. (Создается статическое поле, передаете поле вместо создания объекта.)
//Улучшите систему интроспекции. Например, пусть метод принимает StringBuilder, и добавляет свое описание туда.
//Сделайте unit test-ы для реализованного функционала.
//Сделайте возможным работу вашего pipeline-а как Responsibility Chain (отдельные шаги могут решить завершить выполнение последующих шагов). Для этого, можете хранить в контексте поле IsDone. Не выполняйте следующий этап, если это поле выставлено.