package ru.learning.camundabpmspring.approve;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.VariableInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learning.camundabpmspring.approve.model.AppData;

import java.util.ArrayList;
import java.util.List;

@Component("selectAppDelegate")
public class SelectAppDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        //отобрать все активные процессы, у которых DefinitionKey (processId)
        List<ProcessInstance> listProcessInstance = runtimeService.createProcessInstanceQuery()
                                                                  .active() //все активные задачи
                                                                  .processDefinitionKey("ApplicationApprove") //по процессу
                                                                  .list();

        List<AppData> listAppData = new ArrayList<>();

        listProcessInstance.stream().forEach(currentProcess -> {

            Long amountForm = Long.valueOf(runtimeService.createVariableInstanceQuery()
                                                         .processInstanceIdIn(currentProcess.getId())
                                                         .variableName("Amount")
                                                         .singleResult()
                                                         .getValue().toString());

            String descriptionForm = runtimeService.createVariableInstanceQuery()
                                                   .processInstanceIdIn(currentProcess.getId())
                                                   .variableName("Description")
                                                   .singleResult()
                                                   .getValue().toString();

            String idForm = runtimeService.createVariableInstanceQuery()
                                                    .processInstanceIdIn(currentProcess.getId())
                                                    .variableName("ID")
                                                    .singleResult()
                                                    .getValue().toString();

            AppData appData = new AppData();
                    appData.setId(idForm);
                    appData.setDescription(descriptionForm);
                    appData.setAmount(amountForm);

            listAppData.add(appData);
        });

        System.out.println("selectAppDelegate: list data= " + listAppData.toString());

        //save result to camunda
        execution.setVariable("listAppData", listAppData);
    }
}
