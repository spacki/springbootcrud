package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.Site;

/**
 * Created by karstenspakowski on 21/03/17.
 */
public interface SiteService {

    Iterable<Site> getAllSites();
    Site getSiteById(Integer id);
    Site saveSite(Site site);
    void deleteSite(Integer id);
}
