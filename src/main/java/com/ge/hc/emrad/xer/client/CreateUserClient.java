package com.ge.hc.emrad.xer.client;

import mypacs.wsdl.CreateImsUser;
import mypacs.wsdl.CreateImsUserResponse;
import org.apache.log4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Created by karstenspakowski on 17/05/17.
 */

public class CreateUserClient extends WebServiceGatewaySupport {

    private Logger log = Logger.getLogger(CreateUserClient.class);

    public CreateImsUserResponse createUser(String user, String name, String group, String pacsIp) {

        CreateImsUser createImsUser = new CreateImsUser();
        createImsUser.setIn0(user);
        createImsUser.setIn1(name);
        createImsUser.setIn2(group);

        String pacsSecurityUrl = "http://" + pacsIp + "/axis/services/PacsSecuritySvc";

        CreateImsUserResponse response = (CreateImsUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(pacsSecurityUrl, createImsUser);


        if (response != null) {
            log.debug("ok");
        } else {
            log.warn("error");
        }

        return response;

    }


}


