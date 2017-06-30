package com.ge.hc.emrad.xer.job;

import com.ge.hc.emrad.xer.client.ChangePasswordClient;
import com.ge.hc.emrad.xer.domain.Mapping;
import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.service.MappingService;
import com.ge.hc.emrad.xer.service.ReportingPhysicianService;
import com.ge.hc.emrad.xer.service.SiteService;
import mypacs.wsdl.ChangePasswordResponse;
import mypacs.wsdl.CreateImsUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by karstenspakowski on 07/06/17.
 */
@Component
public class SchedulerJob {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ChangePasswordClient changePasswordClient;

    @Autowired
    public void setChangePasswordClient(ChangePasswordClient changePasswordClient) {
        this.changePasswordClient = changePasswordClient;
    }

    private SiteService siteService;

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    private ReportingPhysicianService reportingPhysicianService;

    @Autowired
    public void setReportingPhysicianService(ReportingPhysicianService reportingPhysicianService) {
        this.reportingPhysicianService = reportingPhysicianService;
    }


    private MappingService mappingService;


    @Autowired
    public void setMappingService(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @Scheduled(fixedRateString = "${xer.password.sync.pollTime}")
    public void syncPassword() {
        logger.info("Password synchronisation is running");
        List<Site> sites = siteService.findAllSites();
        for (Site site : sites) {
            logger.info("working on site " + site.getCity());
            List<ReportingPhysician> physicians = reportingPhysicianService.getAllReportingPysicanFromSite(site.getName());
            for (ReportingPhysician physician : physicians) {
                logger.info("sync password for physician: " + physician.getUserId() );
                String localPassword = getPassword(physician, site);
                logger.info("local password " + localPassword);
                Collection<Site> remoteSites = physician.getSites();
                logger.info("how many mappings " + remoteSites.size());
                for (Site s : remoteSites) {
                    System.out.println("value= " + s.getCity());
                    String remotePassword = getPassword(physician, s);
                    logger.info("remote password: " + remotePassword);
                    if (remotePassword.contentEquals(localPassword)) {
                        logger.info("password for user " + physician.getUserId() +  " LocalTrust: " + site.getName() + " RemoteTrust; " + s.getName() + " already in sync" );
                    } else {
                        logger.info("password for user " + physician.getUserId() +  " LocalTrust: " + site.getName() + " RemoteTrust; " + s.getName() + " must synchronised" );
                        logger.warn (" call pacs security soap service " + physician.getUserId() + " : " + remotePassword + " : " +  localPassword + " : " + s.getImsAddress() );
                        ChangePasswordResponse response = changePasswordClient.changePassword(physician.getUserId(), remotePassword, localPassword, s.getImsAddress());
                    }
                }

            }
        }

    }

    private String getPassword(ReportingPhysician physician, Site site) {
        String password = "unknown";
        try {
            password =  mappingService.getPassword(physician.getUserId(), site);
        } catch (Exception e) {
            logger.error("could not get passowrd form restful web service running on port " + site.getName() + ":" + site.getWebservicePort());
            e.printStackTrace();
        }
        return password;
    }

}
