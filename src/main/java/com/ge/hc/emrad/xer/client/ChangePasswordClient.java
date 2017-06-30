package com.ge.hc.emrad.xer.client;

import mypacs.wsdl.ChangePassword;
import mypacs.wsdl.ChangePasswordResponse;
import mypacs.wsdl.CreateImsUser;
import mypacs.wsdl.CreateImsUserResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Created by karstenspakowski on 07/06/17.
 */

public class ChangePasswordClient extends WebServiceGatewaySupport {

    private Logger log = Logger.getLogger(CreateUserClient.class);


    public ChangePasswordResponse changePassword(String user, String remotePassword, String localPassword, String pacsIp) {

        ChangePassword changePassword = new ChangePassword();
        changePassword.setIn0(user);
        changePassword.setIn1(remotePassword);
        changePassword.setIn2(localPassword);

        String pacsSecurityUrl = "http://" + pacsIp + "/axis/services/PacsSecuritySvc";

        ChangePasswordResponse response = (ChangePasswordResponse) getWebServiceTemplate()
                .marshalSendAndReceive(pacsSecurityUrl, changePassword);


        if (response != null) {
            log.debug("ok");
        } else {
            log.warn("error");
        }

        return response;

    }


}
