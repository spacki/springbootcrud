package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Service
public class SiteServiceImpl implements SiteService {

    private SiteRepository siteRepository;

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public Iterable<Site> getAllSites() {
        return siteRepository.findAll();
    }

    @Override
    public Site getSiteById(Integer id) {
        return siteRepository.findOne(id);
    }

    @Override
    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public void deleteSite(Integer id) {
        siteRepository.delete(id);
    }
}
