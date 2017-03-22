package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.ReportingPhysician;

/**
 * Created by karstenspakowski on 21/03/17.
 */
public interface ReportingPhysicianService {

    Iterable<ReportingPhysician> getAllReportingPhysicians();
    ReportingPhysician getReportingPhysicianById(Integer id);
    ReportingPhysician saveReportingPhysician(ReportingPhysician reportingPhysician);
    void deleteReportingPhysician(Integer id);

}
