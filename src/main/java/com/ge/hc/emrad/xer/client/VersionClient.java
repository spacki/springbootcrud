package com.ge.hc.emrad.xer.client;

import mypacs.wsdl.GetVersion;
import mypacs.wsdl.GetVersionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Created by karstenspakowski on 17/05/17.
 */
public class VersionClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(VersionClient.class);

    /* get Version */

    public GetVersionResponse getVersion(String prodcut, String pacsIp) {

		/* get Version */

        String pacsSecurityUrl = "http://" + pacsIp + "/axis/services/PacsSecuritySvc";

        GetVersion versionRequest = new GetVersion();
        versionRequest.setIn0(prodcut);

        log.info("Requesting Version for " + prodcut);

        GetVersionResponse versionResponse = (GetVersionResponse) getWebServiceTemplate()
                .marshalSendAndReceive(pacsSecurityUrl,
                        versionRequest);

        if (versionResponse != null) {
            //response.setGetVersionReturn("4.0");
            log.debug(versionResponse.getGetVersionReturn());
        }
        return versionResponse;
    }


    public void logResonse(GetVersionResponse response) {

        String version = response.getGetVersionReturn();
        if(version != null) {
            log.info("Prodcut version: " +version);
        } else {
            log.info("no version received");
        }
    }

}
