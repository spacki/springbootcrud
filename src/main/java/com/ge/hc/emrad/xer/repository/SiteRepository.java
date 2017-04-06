package com.ge.hc.emrad.xer.repository;

import com.ge.hc.emrad.xer.domain.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {

    @Query("SELECT s FROM  Site s  " +
            "WHERE  s.name = :homeDomain")
    Site findSitebyName(@Param("homeDomain") String homeDomain );

    @Query("SELECT s FROM  Site s" )
    List<Site> findAllSites();


}
