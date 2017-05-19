package com.ge.hc.emrad.xer.client;

import mypacs.wsdl.GetUserInfo;
import mypacs.wsdl.GetUserInfoResponse;
import org.apache.log4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Created by karstenspakowski on 17/05/17.
 */
public class UserInfoClient extends WebServiceGatewaySupport {

    private Logger log = Logger.getLogger(UserInfoClient.class);

    public GetUserInfoResponse getUserInfoResponse(String user, String pacsIp) {


        String pacsSecurityUrl = "http://" + pacsIp + "/axis/services/PacsSecuritySvc";

        GetUserInfo userInfoRequest = new GetUserInfo();
        userInfoRequest.setIn0("CLIN9");


        GetUserInfoResponse userInfoResponse = (GetUserInfoResponse) getWebServiceTemplate()
                .marshalSendAndReceive(pacsSecurityUrl, userInfoRequest);


        if (userInfoResponse != null) {

            log.debug(" we receivecd some userinofs");
        } else {
            log.warn("user info request unmarshalling faieled comnpletely");
        }

        return userInfoResponse;

    }




}
