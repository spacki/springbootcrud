package com.ge.hc.emrad.xer.configuration;

import com.ge.hc.emrad.xer.client.CreateUserClient;
import com.ge.hc.emrad.xer.client.UserInfoClient;
import com.ge.hc.emrad.xer.client.VersionClient;
import com.ge.hc.emrad.xer.service.GreetingClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by karstenspakowski on 23/03/17.
 */
@Configuration
public class GreetingsConfiguration {

    /*
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

    */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("mypacs.wsdl");
        return marshaller;
    }

    @Bean
    public VersionClient versionClientClient(Jaxb2Marshaller marshaller) {
        VersionClient versionClient = new VersionClient();
        versionClient.setDefaultUri("http://3.183.149.211/axis/services/PacsSecuritySvc");
        versionClient.setMarshaller(marshaller);
        versionClient.setUnmarshaller(marshaller);
        return versionClient;
    }

    @Bean
    public UserInfoClient userInfoClient(Jaxb2Marshaller marshaller) {
        UserInfoClient userInfoClient = new UserInfoClient();
        userInfoClient.setDefaultUri("http://3.183.149.211/axis/services/PacsSecuritySvc");
        userInfoClient.setMarshaller(marshaller);
        userInfoClient.setUnmarshaller(marshaller);
        return userInfoClient;
    }

    @Bean
    public CreateUserClient createUserClient(Jaxb2Marshaller marshaller) {
        CreateUserClient createUserClient = new CreateUserClient();
        createUserClient.setDefaultUri("http://3.183.149.211/axis/services/PacsSecuritySvc");
        createUserClient.setMarshaller(marshaller);
        createUserClient.setUnmarshaller(marshaller);
        return createUserClient;
    }
}
