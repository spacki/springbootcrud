package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.Site;

/**
 * Created by karstenspakowski on 07/04/17.
 */
public interface MappingService {

    public boolean checkUser(String userName, int port) throws Exception;

    public String getPassword(String userName, Site site) throws Exception;

}
