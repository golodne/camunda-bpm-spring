package ru.learning.camundabpmspring.approve;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("approveDelegate")
public class ApproveDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
