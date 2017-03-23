package com.ge.hc.emrad.xer.controller;

import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.service.ReportingPhysicianService;
import com.ge.hc.emrad.xer.service.SiteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Controller
@RequestMapping("/sites")
public class SiteController {


    private Logger log = Logger.getLogger(SiteController.class);


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

    @RequestMapping(value = { "", "/" })
    public String index(Model model) {
        log.debug("sites/index called...");
        model.addAttribute("activePage", "sites");
        model.addAttribute("sites", siteService.getAllSites());
        return "sites/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addSiteForm(Site site, Model model) {
        model.addAttribute("activePage", "sites");
        return "sites/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSite(@Valid Site site, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "sites");
            return "sites/add";
        }

        this.siteService.saveSite(site);
        return "redirect:/sites";
    }

    @RequestMapping(value = "/view/{id}")
    public String viewSite(@PathVariable Integer id, Model model) {
        model.addAttribute("site", this.siteService.getSiteById(id));
        model.addAttribute("activePage", "sites");
        return "sites/view";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editSite(@PathVariable Integer id, Model model) {
        model.addAttribute("site", this.siteService.getSiteById(id));
        model.addAttribute("activePage", "sites");
        return "sites/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSite(Site site) {
        log.debug("Site ID: " + site.getId());
        this.siteService.saveSite(site);
        return "redirect:/sites/view/" + site.getId();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSite(@PathVariable Integer id) {
        /* TODO delete sites only, if all users which are belonging tothat site are removed as well !!
        * */
        Site site = siteService.getSiteById(id);
        log.warn(" delete Site : " + site);
        log.warn("deactivate reporting physician from site " + site.getCity() + " : " + site.getName());

        List<ReportingPhysician> reportingPhysicianList = reportingPhysicianService.getAllReportingPysicanFromSite(site.getName());
        for (ReportingPhysician reportingPhysician : reportingPhysicianList) {
            log.warn("change active status for reporting physician " + reportingPhysician.getUserId());
            reportingPhysician.setActiveStatus(false);
            reportingPhysician.setLastSynchronized(new Date());
            reportingPhysicianService.saveReportingPhysician(reportingPhysician);
        }
        this.siteService.deleteSite(id);
        return "redirect:/sites";
    }

}
