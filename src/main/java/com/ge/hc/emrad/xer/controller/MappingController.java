package com.ge.hc.emrad.xer.controller;

import com.ge.hc.emrad.xer.client.CreateUserClient;
import com.ge.hc.emrad.xer.client.VersionClient;
import com.ge.hc.emrad.xer.domain.CpacsUser;
import com.ge.hc.emrad.xer.domain.Mapping;
import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.service.MappingService;
import com.ge.hc.emrad.xer.service.ReportingPhysicianService;
import com.ge.hc.emrad.xer.service.SiteService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import mypacs.wsdl.CreateImsUser;
import mypacs.wsdl.CreateImsUserResponse;
import mypacs.wsdl.GetVersionResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.expression.Lists;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by karstenspakowski on 04/04/17.
 */
@Controller
@RequestMapping("/mappings")
public class MappingController {


    private Logger log = Logger.getLogger(MappingController.class);


    private ReportingPhysicianService reportingPhysicianService;



    @Autowired
    public void setReportingPhysicianService(ReportingPhysicianService reportingPhysicianService) {
        this.reportingPhysicianService = reportingPhysicianService;
    }


    private VersionClient versionClient;

    @Autowired
    public void setVersionClient(VersionClient versionClient) {
        this.versionClient = versionClient;
    }

    private CreateUserClient createUserClient;

    @Autowired
    public void setCreateUserClient(CreateUserClient createUserClient) {
        this.createUserClient = createUserClient;
    }

    private SiteService siteService;

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    private MappingService mappingService;

    @Autowired
    public void setMappingService(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @RequestMapping(value = "/view/{id}")
    public String index(@PathVariable Integer id, Model model) {
        log.debug("mappings/index called...");
        model.addAttribute("activePage", "mappings");
        ReportingPhysician physician = reportingPhysicianService.getReportingPhysicianById(id);
        model.addAttribute("physician", physician);
        Collection<Site> sites = physician.getSites();
        model.addAttribute("sites", sites);
        return "mappings/index";
    }


    @RequestMapping(value = "/delete/userId/{userId}/siteId/{siteId}", method = RequestMethod.GET)
    public String deleteSite(@PathVariable Integer userId,
                             @PathVariable Integer siteId,
                             Model model) {

        ReportingPhysician physician = reportingPhysicianService.getReportingPhysicianById(userId);
        Site site = siteService.getSiteById(siteId);
        log.warn(" delete mapping : " + physician + " : "+ site);
        physician.getSites().remove(site);
        site.getReportingPhysician().remove(physician);
        log.warn(" delete mapping : " + physician + " : "+ site);
        siteService.saveSite(site);
        reportingPhysicianService.saveReportingPhysician(physician);
        model.addAttribute("activePage", "mappings");
        model.addAttribute("physician", physician);
        Collection<Site> sites = physician.getSites();
        model.addAttribute("sites", sites);
        return "mappings/index";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String addMapping(@PathVariable Integer id, Model model) {
        log.debug("add and get");
        ReportingPhysician physician = reportingPhysicianService.getReportingPhysicianById(id);
        Site homeSite = siteService.getStiteByName(physician.getHomeDomain());
        Collection<Site> mappedSites = physician.getSites();
        mappedSites.add(homeSite);
        List<Site> sites = siteService.findAllSites();
        sites.removeAll(mappedSites);
        model.addAttribute("physician", physician);
        model.addAttribute("sites", sites);
        Mapping mapping = new Mapping();
        mapping.setUserId(physician.getId());
        model.addAttribute("mapping", mapping);
        model.addAttribute("activePage", "mappings");
        return "mappings/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMapping(Mapping mapping, BindingResult bindingResult, Model model) {
        log.debug("add and get save ......");
        log.debug("Mapping: " + mapping);
        ReportingPhysician physician = reportingPhysicianService.getReportingPhysicianById(mapping.getUserId());
        Site site = siteService.getSiteById(mapping.getSiteId());
        Site homeSite = siteService.getStiteByName(physician.getHomeDomain());
        Collection<Site> mappedSites = physician.getSites();
        mappedSites.add(homeSite);
        List<Site> sites = siteService.findAllSites();
        sites.removeAll(mappedSites);
        model.addAttribute("physician", physician);
        model.addAttribute("sites", sites);
        model.addAttribute("mapping", mapping);
        model.addAttribute("activePage", "mappings");

        // the user should not exist on the remote site

        log.debug("does the user already exist on " + site.getName());

        try {

            if (mappingService.checkUser(physician.getUserId(), site.getWebservicePort())) {
                FieldError fieldError = new FieldError("mapping", "userId", "User " +  physician.getUserId() + " already exists on Remote Trust please contact the administrator!");
                bindingResult.addError(fieldError);
                return "mappings/add";

            }
        } catch (Exception e) {
            log.debug("Could not check whether the user exists on remote site pleae make sure the webservice is running");
            FieldError fieldError = new FieldError("reportingPhysician", "userId", "could not vaildate user againts remote trust ! please verify the web service is running at " + "http://localhost:" + site.getWebservicePort());
            bindingResult.addError(fieldError);
            return "mappings/add";
        }

        // now since the user does not exist on the Remote trust it needs to be created for tets i just call the version method of the Pacs SecurityWebService
        // we need the ip of the site
        log.debug(site.getImsAddress());

        //GetVersionResponse response = versionClient.getVersion("GE C-PACS", site.getImsAddress());
        //versionClient.logResonse(response);

        CreateImsUserResponse response = createUserClient.createUser(physician.getUserId(), physician.getLastName(), site.getUserGroup(), site.getImsAddress());

        // check agin if the user exist

        try {

            if (!mappingService.checkUser(physician.getUserId(), site.getWebservicePort())) {
                FieldError fieldError = new FieldError("mapping", "userId", "User " +  physician.getUserId() + " could not be created on Remote Trust, please contact the administrator!");
                bindingResult.addError(fieldError);
                return "mappings/add";

            } else {
                physician.addSite(site);
                site.addReportingPhysician(physician);
                siteService.saveSite(site);
            }
        } catch (Exception e) {
            log.debug("Could not check whether the user exists on remote site pleae make sure the webservice is running");
            FieldError fieldError = new FieldError("reportingPhysician", "userId", "could not vaildate user againts remote trust ! please verify the web service is running at " + "http://localhost:" + site.getWebservicePort());
            bindingResult.addError(fieldError);
            return "mappings/add";
        }

        //caheck again

        /*
        if (user == null) {
            // call PacsSecuritywebService
            String pacsUrl = "cpacs" + site.getImsAddress();
            //check if the SOPA-call created the user
            // otherwise redirect to the mapping page and state an error message
        }
        // now sync password

        String remotePassword = "remote_password"; //   user.getDecryptPassword();
        // now we need the password from the local domain
        url = "http://localhost:" + siteService.getStiteByName(physician.getHomeDomain()).getWebservicePort() + "/hello-cpacs/?name=" + physician.getUserId();
        CpacsUser localCpacsUser = null;
        String localPassword = "something"; //localCpacsUser.getDecryptPassword();
        if (localPassword.compareTo(remotePassword)== 0) {
            log.debug("no password sync neccesary");
        } else {
            log.debug("call change password");
            // now we need to call it once again, to see if axis service did waht we expected
        }
        physician.addSite(site);
        site.addReportingPhysician(physician);
        siteService.saveSite(site);
        reportingPhysicianService.saveReportingPhysician(physician);
        model.addAttribute("activePage", "mappings");
        model.addAttribute("physician", physician);
     */
        return "redirect:/mappings/view/" + mapping.getUserId();
    }
}
