package com.ge.hc.emrad.xer.repository;

import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Repository
public interface ReportingPhysicianRepository extends CrudRepository<ReportingPhysician, Integer> {
}
