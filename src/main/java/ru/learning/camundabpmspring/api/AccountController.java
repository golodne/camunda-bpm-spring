package ru.learning.camundabpmspring.api;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.learning.camundabpmspring.ProcessConstants;

@Controller
@RequestMapping("/testAccount")
@Log4j2
public class AccountController {

    @Autowired
    private ProcessEngine camunda;

    @Autowired
    TaskService taskService;

    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public String placeOrderPOST(String name) {
        ProcessInstanceWithVariables instance = camunda.getRuntimeService()
                .createProcessInstanceByKey( ProcessConstants.PROCESS_KEY_ACCOUNT)
                .executeWithVariablesInReturn();
        return "teset";
    }
}
