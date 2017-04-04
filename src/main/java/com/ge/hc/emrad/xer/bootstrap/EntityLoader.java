package com.ge.hc.emrad.xer.bootstrap;

import com.ge.hc.emrad.xer.domain.CpacsUser;
import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.service.ReportingPhysicianService;
import com.ge.hc.emrad.xer.service.SiteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Component
public class EntityLoader implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = Logger.getLogger(EntityLoader.class);

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


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        loadSites();
        loadReportingPhysicians();
        readCpacsUserRestService("WEB");
    }


    public void readCpacsUserRestService(String name) {

        RestTemplate restTemplate = new RestTemplate();

        CpacsUser user = restTemplate.getForObject(
                "http://localhost:9100/hello-cpacs/?name=WEB", CpacsUser.class);
        log.info(user.toString());

    }

    public void loadReportingPhysicians() {

        ReportingPhysician reportingPhysician1 = new ReportingPhysician();
        reportingPhysician1.setUserId("Sulu");
        reportingPhysician1.setLastName("Sulu");
        reportingPhysician1.setFirstName("Hikaru");
        reportingPhysician1.setHomeDomain("NHU");
        reportingPhysician1.setEmail("hikaru.sulu@nhu.uk.co");
        reportingPhysician1.setLastSynchronized(new Date());
        reportingPhysician1.setCreated(new Date());
        reportingPhysician1.setActiveStatus(false);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician1);
        log.info("Saved reporting physician - id: " + reportingPhysician1.getId());

        ReportingPhysician reportingPhysician2 = new ReportingPhysician();
        reportingPhysician2.setUserId("chekov");
        reportingPhysician2.setLastName("Chekov");
        reportingPhysician2.setFirstName("Pavel");
        reportingPhysician2.setHomeDomain("NHU");
        reportingPhysician2.setEmail("pavel.chekov@nhu.co.uk");
        reportingPhysician2.setLastSynchronized(new Date());
        reportingPhysician2.setCreated(new Date());
        reportingPhysician2.setActiveStatus(true);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician2);
        log.info("Saved reporting physician - id: " + reportingPhysician2.getId());

        ReportingPhysician reportingPhysician3 = new ReportingPhysician();
        reportingPhysician3.setUserId("uhura");
        reportingPhysician3.setLastName("Uhura");
        reportingPhysician3.setFirstName("Penda");
        reportingPhysician3.setHomeDomain("NHU");
        reportingPhysician3.setEmail("penda.uhura@nhu.co.uk");
        reportingPhysician3.setLastSynchronized(new Date());
        reportingPhysician3.setCreated(new Date());
        reportingPhysician3.setActiveStatus(true);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician3);
        log.info("Saved reporting physician - id: " + reportingPhysician3.getId());

        ReportingPhysician reportingPhysician4 = new ReportingPhysician();
        reportingPhysician4.setUserId("kirk");
        reportingPhysician4.setLastName("Kirk");
        reportingPhysician4.setFirstName("James");
        reportingPhysician4.setHomeDomain("NHU");
        reportingPhysician4.setEmail("james.kirk@nhu.co.uk");
        reportingPhysician4.setLastSynchronized(new Date());
        reportingPhysician4.setCreated(new Date());
        reportingPhysician4.setActiveStatus(true);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician4);
        log.info("Saved reporting physician - id: " + reportingPhysician4.getId());

        ReportingPhysician reportingPhysician5 = new ReportingPhysician();
        reportingPhysician5.setUserId("mckoy");
        reportingPhysician5.setLastName("McKoy");
        reportingPhysician5.setFirstName("Pille");
        reportingPhysician5.setHomeDomain("NHU");
        reportingPhysician5.setEmail("pille.mckoy@nhu.co.uk");
        reportingPhysician5.setLastSynchronized(new Date());
        reportingPhysician5.setCreated(new Date());
        reportingPhysician5.setActiveStatus(true);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician5);
        log.info("Saved reporting physician - id: " + reportingPhysician5.getId());

        ReportingPhysician reportingPhysician6 = new ReportingPhysician();
        reportingPhysician6.setUserId("spock");
        reportingPhysician6.setLastName("Spock");
        reportingPhysician6.setFirstName("Mister");
        reportingPhysician6.setHomeDomain("NHU");
        reportingPhysician6.setEmail("mister.spock@nhu.co.uk");
        reportingPhysician6.setLastSynchronized(new Date());
        reportingPhysician6.setCreated(new Date());
        reportingPhysician6.setActiveStatus(true);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician6);
        log.info("Saved reporting physician - id: " + reportingPhysician6.getId());

        ReportingPhysician reportingPhysician7 = new ReportingPhysician();
        reportingPhysician7.setUserId("scott");
        reportingPhysician7.setLastName("Montgomery");
        reportingPhysician7.setFirstName("Scott");
        reportingPhysician7.setHomeDomain("NHU");
        reportingPhysician7.setEmail("scott.montgomery@nhu.co.uk");
        reportingPhysician7.setLastSynchronized(new Date());
        reportingPhysician7.setCreated(new Date());
        reportingPhysician7.setActiveStatus(true);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician7);
        log.info("Saved reporting physician - id: " + reportingPhysician7.getId());

    }


    public void loadSites() {


        Site site1 = new Site();
        site1.setName("NHU");
        site1.setCity("Nottingham");
        site1.setWebservicePort(9100);
        site1.setImsAddress("192.168.178.31");

        siteService.saveSite(site1);
        log.info("Saved site - id: " + site1.getId());

        Site site2 = new Site();
        site2.setName("CRH");
        site2.setCity("Chesterfield");
        site2.setWebservicePort(9200);
        site2.setImsAddress("192.168.178.32");

        siteService.saveSite(site2);
        log.info("Saved site - id: " + site2.getId());

        Site site3 = new Site();
        site3.setName("SFH");
        site3.setCity("Sherwood");
        site3.setWebservicePort(9300);
        site3.setImsAddress("192.168.178.33");

        siteService.saveSite(site3);
        log.info("Saved site - id: " + site3.getId());

        Site site4 = new Site();
        site4.setName("ULH");
        site4.setCity("Lincoln");
        site4.setWebservicePort(9400);
        site4.setImsAddress("192.168.178.34");

        siteService.saveSite(site4);
        log.info("Saved site - id: " + site4.getId());

        Site site5 = new Site();
        site5.setName("UHL");
        site5.setCity("Leicester");
        site5.setWebservicePort(9500);
        site5.setImsAddress("192.168.178.35");

        siteService.saveSite(site5);
        log.info("Saved site - id: " + site5.getId());

        Site site6 = new Site();
        site6.setName("NGH");
        site6.setCity("Northhampton");
        site6.setWebservicePort(9600);
        site6.setImsAddress("192.168.178.36");

        siteService.saveSite(site6);
        log.info("Saved site - id: " + site6.getId());

        Site site7 = new Site();
        site7.setName("KGH");
        site7.setCity("Kettering");
        site7.setWebservicePort(9700);
        site7.setImsAddress("192.168.178.37");

        siteService.saveSite(site7);
        log.info("Saved site - id: " + site7.getId());

    }
}
