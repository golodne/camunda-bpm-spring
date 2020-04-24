package ru.learning.camundabpmspring.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Random;

public class CheckWeatherDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Random r = new Random();
        execution.setVariable("name","Vova");
        execution.setVariable("weatherOk", r.nextBoolean());

    }
}
