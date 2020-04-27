package ru.learning.camundabpmspring.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.learning.camundabpmspring.ProcessConstants;

@RestController
@RequestMapping("/testAccount")
public class AccountController {

    @Autowired
    private ProcessEngine camunda;

    @RequestMapping(method= RequestMethod.POST)
    public void placeOrderPOST(String name) {
        placeAccount(name);
    }

    public ProcessInstance placeAccount(String name) {
        return camunda.getRuntimeService().startProcessInstanceByKey(
                ProcessConstants.PROCESS_KEY_ACCOUNT,
                Variables
                        .putValue(ProcessConstants.VAR_NAME_USERNAME, name));
                        //.putValue(ProcessConstants.VAR_NAME_amount, amount));
    }
}
