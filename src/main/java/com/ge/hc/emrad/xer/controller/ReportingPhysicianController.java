package com.ge.hc.emrad.xer.controller;

import com.ge.hc.emrad.xer.domain.CpacsUser;
import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.service.GreetingClient;
import com.ge.hc.emrad.xer.service.ReportingPhysicianService;
import com.ge.hc.emrad.xer.service.SiteService;
import greetings.wsdl.GetGreetingResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Controller
@RequestMapping("/reports")
public class ReportingPhysicianController {

    private Logger log = Logger.getLogger(ReportingPhysicianController.class);

    private ReportingPhysicianService reportingPhysicianService;
    private ObjectError error;

    @Autowired
    public void setReportingPhysicianService(ReportingPhysicianService reportingPhysicianService) {
        this.reportingPhysicianService = reportingPhysicianService;
    }

    private SiteService siteService;

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    private GreetingClient greetingClient;

    @Autowired
    public void setGreetingClient(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @RequestMapping(value = {"", "/"})
    public String index(Model model) {
        log.info(" load all reporting physicians ..");
        model.addAttribute("activePage", "reports");
        model.addAttribute("reportingPhysicians", this.reportingPhysicianService.getAllReportingPhysicians());
        return "reports/index";
    }

    @RequestMapping(value = "/view/{id}")
    public String viewReportingPhysician(@PathVariable Integer id, Model model) {
        log.info("get reporting physician info for " + id);
        model.addAttribute("reportingPhysician", this.reportingPhysicianService.getReportingPhysicianById(id));
        model.addAttribute("activePage", "reports");
        return "reports/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addReportingPhysician(ReportingPhysician reportingPhysician, Model model) {
        log.debug("add and get");
        model.addAttribute("sites", this.siteService.getAllSites());
        model.addAttribute("activePage", "reports");
        return "reports/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSite(@Valid ReportingPhysician reportingPhysician, BindingResult bindingResult, Model model) {
        log.info("add (post) new reporting physician");
        model.addAttribute("sites", this.siteService.getAllSites());
        model.addAttribute("activePage", "reports");
        // first we needd to check if the user exists on the selected site


        /* SOAP Pacs service simulator */

        /*
        GetGreetingResponse response = greetingClient.getGreeting("Karsten");
        greetingClient.logResponse(response);
        */

        if (bindingResult.hasErrors()) {
            log.debug("some errors ");

            return "reports/add";
        }
        /* now we must make sure the user is know on the hoem trust */

        RestTemplate restTemplate = new RestTemplate();
        log.info("make sure that a user exists: " + reportingPhysician.getUserId() + " on site " + siteService.getStiteByName(reportingPhysician.getHomeDomain()));

        String url = "http://localhost:" + siteService.getStiteByName(reportingPhysician.getHomeDomain()).getWebservicePort() + "/hello-cpacs/?name=" + reportingPhysician.getUserId();
        log.debug("URL: " + url);
        CpacsUser user = null;
        try {
            user = restTemplate.getForObject(
                    url, CpacsUser.class);
            log.info("user: " + user);
        } catch (Exception e) {
            log.debug("Could not vaildate user againts Home trust please verify the web service is running at  " + url);
            FieldError fieldError = new FieldError("reportingPhysician", "userId", "could not vaildate user " + reportingPhysician.getUserId() + "  againts Home trust. Please verify the web service is running at " + "http://localhost:" + siteService.getStiteByName(reportingPhysician.getHomeDomain()).getWebservicePort());
            bindingResult.addError(fieldError);
            return "reports/add";
        }

       //if (user == null) {
        if (user.getId()==0) {
            FieldError fieldError = new FieldError("reportingPhysician", "userId", "the user "  + user.getUserName() + " does not exist on the local trust!, Please create the user on AD of the local trust!");
            bindingResult.addError(fieldError);
            return "reports/add";
        }
        reportingPhysician.setLastSynchronized(new Date());
        reportingPhysician.setCreated(new Date());
        this.reportingPhysicianService.saveReportingPhysician(reportingPhysician);
        return "redirect:/reports";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editReportingPhysician(@PathVariable Integer id, Model model) {
        log.debug("edit reporting physicians " + id);
        ReportingPhysician reportingPhysician = this.reportingPhysicianService.getReportingPhysicianById(id);
        model.addAttribute("report", reportingPhysician);
        model.addAttribute("activePage", "reports");
        return "reports/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateReportingPhysician(ReportingPhysician reportingPhysician) {
        log.debug("ReportingPhysician ID: " + reportingPhysician.getUserId());
        reportingPhysician.setLastSynchronized(new Date());
        this.reportingPhysicianService.saveReportingPhysician(reportingPhysician);
        return "redirect:/reports/view/" + reportingPhysician.getId();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteReportingPhysician(@PathVariable Integer id) {
        /* TODO
           delete users only if they have no mapping, mapping must be disabeled
         */
        log.warn("delete user " + reportingPhysicianService.getReportingPhysicianById(id));
        this.reportingPhysicianService.deleteReportingPhysician(id);
        return "redirect:/reports";
    }

}
