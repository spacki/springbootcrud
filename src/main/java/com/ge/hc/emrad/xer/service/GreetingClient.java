package com.ge.hc.emrad.xer.service;

import greetings.wsdl.GetGreetingRequest;
import greetings.wsdl.GetGreetingResponse;
import greetings.wsdl.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
/**
 * Created by karstenspakowski on 23/03/17.
 */
@Service
public class GreetingClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(GreetingClient.class);

    public GetGreetingResponse getGreeting(String name) {

        GetGreetingRequest request = new GetGreetingRequest();
        request.setName(name);

        log.info("Requesting greeting for " + name);

        GetGreetingResponse response = (GetGreetingResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://localhost:8080/ws/greetings",
                        request,
                        new SoapActionCallback("http://localhost:8080/ws/greetings"));

        return response;
    }

    public void logResponse(GetGreetingResponse response) {

        Greeting greeting = response.getGreeting();

        if (!greeting.equals(null)) {
            log.info(String.format("Greeting [ date = %s, content = %s]",greeting.getDate(),greeting.getContent()));
        } else {
            log.info("No greeting received");
        }
    }

}
