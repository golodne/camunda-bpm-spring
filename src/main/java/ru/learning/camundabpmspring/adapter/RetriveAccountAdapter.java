package ru.learning.camundabpmspring.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.learning.camundabpmspring.ProcessConstants;

import java.util.HashMap;
import java.util.Map;

@Component("retriveAccountAdapter")
public class RetriveAccountAdapter implements JavaDelegate {

    @Autowired
    private RestTemplate rest;

    private String restEndpoint() {
        return "http://localhost:8082/account";
    }

    public static class CreateAccountRequest {
        public String name;
    }

    public static class CreateAccountResponse {
        public int id;
        public String name;
    }

    @Override
    public void execute(DelegateExecution ctx) throws Exception {
        CreateAccountRequest request = new CreateAccountRequest();
        request.name = (String) ctx.getVariable(ProcessConstants.VAR_NAME_USERNAME);
        CreateAccountResponse response = rest.postForObject(restEndpoint(),
                request, CreateAccountResponse.class);

        Map<String, Object> mapValues = new HashMap<>();
        mapValues.put(ProcessConstants.VAR_NAME_USERNAME, response.name + " resp ");
        mapValues.put(ProcessConstants.VAR_NAME_ID, response.id);

        System.out.println("retriveAccountAdapter map: " + mapValues.toString());

        ctx.setVariables(mapValues);
    }
}
