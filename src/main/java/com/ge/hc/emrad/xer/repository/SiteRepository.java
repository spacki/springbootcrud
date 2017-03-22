package com.ge.hc.emrad.xer.repository;

import com.ge.hc.emrad.xer.domain.Site;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {


}
