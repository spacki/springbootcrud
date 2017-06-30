package com.ge.hc.emrad.xer.bootstrap;

import com.ge.hc.emrad.xer.domain.CpacsUser;
import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.service.MappingService;
import com.ge.hc.emrad.xer.service.ReportingPhysicianService;
import com.ge.hc.emrad.xer.service.SiteService;
import com.ge.hc.emrad.xer.service.SynchronisationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Component
@Profile("dev")
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

    private SynchronisationService synchronisationService;

    @Autowired
    public void setSynchronisationService(SynchronisationService synchronisationService) {
        this.synchronisationService = synchronisationService;
    }

    private MappingService mappingService;

    @Autowired
    public void setMappingService(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        loadSites();
        loadReportingPhysicians();
        loadMappings();
      //  readCpacsUserRestService("WEB");
        synchronizePassword();
    }


    public void synchronizePassword() {
        log.info("start synchronisation process");
        List<Site> sites = siteService.findAllSites();
        for(Site site:sites) {
            log.info("sync site " + site.getName());
            List<ReportingPhysician> physicians = reportingPhysicianService.getAllReportingPysicanFromSite(site.getName());
            for (ReportingPhysician physician:physicians) {
                log.info("sync physician: " + physician.getUserId());
                synchronisationService.synchPassword(physician);
            }

        }
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


        ReportingPhysician reportingPhysician8 = new ReportingPhysician();
        reportingPhysician8.setUserId("abuazi");
        reportingPhysician8.setLastName("abuazi");
        reportingPhysician8.setFirstName("Scott");
        reportingPhysician8.setHomeDomain("CRH");
        reportingPhysician8.setEmail("abuazi.montgomery@nhu.co.uk");
        reportingPhysician8.setLastSynchronized(new Date());
        reportingPhysician8.setCreated(new Date());
        reportingPhysician8.setActiveStatus(true);

        reportingPhysicianService.saveReportingPhysician(reportingPhysician8);
        log.info("Saved reporting physician - id: " + reportingPhysician8.getId());

    }


    public void loadSites() {


        Site site1 = new Site();
        site1.setName("NHU");
        site1.setCity("Nottingham");
        site1.setUserGroup("Radiologists");
        site1.setWebservicePort(9100);
        site1.setImsAddress("3.183.149.211");

        siteService.saveSite(site1);
        log.info("Saved site - id: " + site1.getId());

        Site site2 = new Site();
        site2.setName("CRH");
        site2.setCity("Chesterfield");
        site2.setUserGroup("Radiologists");
        site2.setWebservicePort(9200);
        site2.setImsAddress("3.183.149.211");

        siteService.saveSite(site2);
        log.info("Saved site - id: " + site2.getId());

        Site site3 = new Site();
        site3.setName("SFH");
        site3.setCity("Sherwood");
        site3.setUserGroup("Radiologists");
        site3.setWebservicePort(9300);
        site3.setImsAddress("3.183.149.211");

        siteService.saveSite(site3);
        log.info("Saved site - id: " + site3.getId());

        Site site4 = new Site();
        site4.setName("ULH");
        site4.setCity("Lincoln");
        site4.setUserGroup("Radiologists");
        site4.setWebservicePort(9400);
        site4.setImsAddress("3.183.149.211");

        siteService.saveSite(site4);
        log.info("Saved site - id: " + site4.getId());

        Site site5 = new Site();
        site5.setName("UHL");
        site5.setCity("Leicester");
        site5.setUserGroup("Radiologists");
        site5.setWebservicePort(9500);
        site5.setImsAddress("3.183.149.211");

        siteService.saveSite(site5);
        log.info("Saved site - id: " + site5.getId());

        Site site6 = new Site();
        site6.setName("NGH");
        site6.setCity("Northhampton");
        site6.setUserGroup("Radiologists");
        site6.setWebservicePort(9600);
        site6.setImsAddress("3.183.149.211");

        siteService.saveSite(site6);
        log.info("Saved site - id: " + site6.getId());

        Site site7 = new Site();
        site7.setName("KGH");
        site7.setCity("Kettering");
        site7.setUserGroup("Radiologists");
        site7.setWebservicePort(9700);
        site7.setImsAddress("3.183.149.211");

        siteService.saveSite(site7);
        log.info("Saved site - id: " + site7.getId());

    }

    @Transactional
    public void loadMappings() {
        ReportingPhysician physician = reportingPhysicianService.getReportingPhysicianById(8);
        Site site = siteService.getSiteById(1);
        physician.addSite(site);
        site.addReportingPhysician(physician);
        siteService.saveSite(site);
        reportingPhysicianService.saveReportingPhysician(physician);


    }
}
