package ru.learning.camundabpmspring.approve;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("sendAllDelegate")
public class SendAllDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
