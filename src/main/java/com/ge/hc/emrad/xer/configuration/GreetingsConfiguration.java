package com.ge.hc.emrad.xer.configuration;

import com.ge.hc.emrad.xer.service.GreetingClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by karstenspakowski on 23/03/17.
 */
@Configuration
public class GreetingsConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("greetings.wsdl");
        return marshaller;
    }

    @Bean
    public GreetingClient greetingClient(Jaxb2Marshaller marshaller) {
        GreetingClient greetingClient = new GreetingClient();
        greetingClient.setDefaultUri("http://localhost:8080/ws/");
        greetingClient.setMarshaller(marshaller);
        greetingClient.setUnmarshaller(marshaller);
        return greetingClient;
    }
}
