package com.ge.hc.emrad.xer.repository;

import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Repository
public interface ReportingPhysicianRepository extends CrudRepository<ReportingPhysician, Integer> {

    @Query("SELECT r FROM  ReportingPhysician r  " +
            "WHERE  r.homeDomain = :homeDomain")
    List<ReportingPhysician> findAllReportingPhysicianbySite(@Param("homeDomain") String homeDomain );

}
