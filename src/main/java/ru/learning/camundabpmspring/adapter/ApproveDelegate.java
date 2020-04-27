package ru.learning.camundabpmspring.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.learning.camundabpmspring.model.AppData;

import java.util.List;
import java.util.Random;

@Component("approveDelegate")
public class ApproveDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Random r = new Random();

        List<AppData> listAppData = (List<AppData>) execution.getVariable("listAppData");
        System.out.println("approveDelegate: listAppData" + listAppData.toString());

        listAppData.forEach(x -> {
            x.setComment("after Approve comment");
            x.setAcceptResult(r.nextBoolean());
        });

        //listAppData.stream().forEach(x -> System.out.println("amount: " + x.getAmount()));
        execution.setVariable("listAppData", listAppData);
    }
}
