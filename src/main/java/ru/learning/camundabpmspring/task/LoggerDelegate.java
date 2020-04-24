package ru.learning.camundabpmspring.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class LoggerDelegate implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("\n\n invoke LoggerDelegage"
                + " processDefinitionId = " + execution.getProcessDefinitionId()
                + ", activityid = " + execution.getCurrentActivityId()
                + ", activityname = " + execution.getCurrentActivityName()
                + ", processInstanceId = " + execution.getProcessInstanceId()
                + ", businessKey = " + execution.getProcessBusinessKey()
                + ", exequtionId = " + execution.getId()
                + "\n\n");

    }
}
