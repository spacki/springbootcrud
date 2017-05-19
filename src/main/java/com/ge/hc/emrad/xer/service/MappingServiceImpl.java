package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.CpacsUser;
import com.ge.hc.emrad.xer.domain.Site;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by karstenspakowski on 07/04/17.
 */
@Service
public class MappingServiceImpl implements MappingService {


    private Logger log = Logger.getLogger(MappingService.class);


    @Override
    public boolean checkUser(String userName, int port) {
        log.debug("check if user: " + userName + " exist" );
        String url = "http://localhost:" + port + "/hello-cpacs/?name=" + userName;
        log.debug("call restful web service at: " + url);
        RestTemplate restTemplate = new RestTemplate();
        CpacsUser user = restTemplate.getForObject(url, CpacsUser.class);
        log.info("user: " + user);
        if (user.getId() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getPassword(String userName, Site site) {
        log.debug(" get password for user: " + userName + " at Site: " + site.getName());
        String url = "http://localhost:" + site.getWebservicePort() + "/hello-cpacs/?name=" + userName;
        log.debug("call restful web service at: " + url);
        RestTemplate restTemplate = new RestTemplate();
        CpacsUser user = restTemplate.getForObject(url, CpacsUser.class);
        log.info("user: " + user);
        String password = user.getDecryptPassword();
        return password;
    }

}
