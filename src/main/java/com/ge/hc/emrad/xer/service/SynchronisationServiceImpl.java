package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.domain.Site;
import mypacs.wsdl.CreateImsUserResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by karstenspakowski on 18/05/17.
 */

@Service
public class SynchronisationServiceImpl implements SynchronisationService {


    private Logger log = Logger.getLogger(ReportingPhysicianServiceImpl.class);

    private MappingService mappingService;

    @Autowired
    public void setMappingService(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    private SiteService siteService;

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public void synchPassword(ReportingPhysician physician) {
        log.warn("password sync process .. starts for " + physician.getUserId());
        Collection<Site> sites = physician.getSites();
        Site localSite = siteService.getStiteByName(physician.getHomeDomain());
        for (Site site:sites) {
            log.warn("sync password for user from " +physician.getHomeDomain() + " -----> " + site.getName());
            // get current password from Local trust
            String localPassword= getPassword(physician, localSite);
            log.warn("current password on local trust: " + localPassword);
            // get current password from remot trust
            String remotePassword = getPassword(physician, site);
            log.warn("current password on remote trust: " + remotePassword);
            // compare and sync if neccessary
            if(remotePassword.compareTo(localPassword)==0) {
                log.info("password already in sync");
            } else {
                log.warn("we need to call the pacs security change password method");
                //CreateImsUserResponse response = createUserClient.createUser(physician.getUserId(), physician.getLastName(), site.getUserGroup(), site.getImsAddress());

                // check remote password after Sync
                log.info("remote passord after synchronisation: " + getPassword(physician, site));
            }


            // repeat compare

        }
        log.warn("sync process finished");

    }

    private String getPassword(ReportingPhysician physician, Site site) {
        String password = "unknown";
        try {
            password =  mappingService.getPassword(physician.getUserId(), site);
        } catch (Exception e) {
            log.error("could not get passowrd form restful web service running on port " + site.getName() + ":" + site.getWebservicePort());
            e.printStackTrace();
        }
        return password;
    }
}
