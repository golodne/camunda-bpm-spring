package ru.learning.camundabpmspring.adapter;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("changeSumDelegate")
@Slf4j
public class ChangeSumDelegate implements JavaDelegate {

    @Autowired
    private RestTemplate rest;

    private String restEndpoint1() {
        return "http://localhost:8090/account";
    }

    private String restEndpoint2() {
        return "http://localhost:8091/account";
    }

    public static class ChangeAccountBalanceRequest {
        public String account;
        public Double amount;
        public String operation;
    }

    public static class ChangeAccountBalanceResponse {
        public String code;
    }

    @Override
    @Synchronized
    public void execute(DelegateExecution ctx) throws Exception {

        String account = (String) ctx.getVariable("account");
        Double amount = (Double) ctx.getVariable("amount");
        String operation = (String) ctx.getVariable("operation");

        log.info(String.format("account = %s , amount = [%f], operation = [%s]",
                account,amount,operation));

        String baseUrl = ("+".equals(operation)) ? restEndpoint1() : restEndpoint2();

        ChangeAccountBalanceRequest request = new ChangeAccountBalanceRequest();
        request.account = account;
        request.amount = amount;
        request.operation = operation;

                ChangeAccountBalanceResponse response = rest.postForObject(baseUrl,
                        request, ChangeAccountBalanceResponse.class);

         log.info("code: = ",response.code);

    }
}
/*
Tue Apr 28 15:52:19 GMT+04:00 2020:INFO:8091  {"account":"1","amount":null,"operation":"-"}
Tue Apr 28 15:52:19 GMT+04:00 2020:INFO:8090  {"account":"2","amount":null,"operation":"+"}
Tue Apr 28 15:52:19 GMT+04:00 2020:INFO:8091  {"account":"2","amount":null,"operation":"-"}
Tue Apr 28 15:52:19 GMT+04:00 2020:INFO:8090  {"account":"1","amount":null,"operation":"+"}
* */






