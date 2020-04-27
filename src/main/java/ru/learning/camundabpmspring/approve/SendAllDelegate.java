package ru.learning.camundabpmspring.approve;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.learning.camundabpmspring.approve.model.AppData;

import java.util.List;

@Component("sendAllDelegate")
public class SendAllDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<AppData> listAppData = (List<AppData>) execution.getVariable("listAppData");

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        ProcessEngineServices processEngineServices = execution.getProcessEngineServices();

        listAppData.forEach(appData -> {
            //WaitAccept - диаграмма ждать сообещние AcceptApprove.bpmn для processInstanceId также установить результат
            runtimeService.createMessageCorrelation("WaitAccept")
                    .processInstanceId(appData.getProcessInstanceId())
                    .setVariable("acceptResult", appData.getAcceptResult())
                    .setVariable("Comment", appData.getComment())
                    .correlate(); //send
        });


    }
}
