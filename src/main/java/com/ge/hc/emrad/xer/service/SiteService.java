package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.Site;

import java.util.List;

/**
 * Created by karstenspakowski on 21/03/17.
 */
public interface SiteService {

    Iterable<Site> getAllSites();
    Site getSiteById(Integer id);
    Site saveSite(Site site);
    void deleteSite(Integer id);
    Site getStiteByName(String name);
    List<Site> findAllSites();
}
