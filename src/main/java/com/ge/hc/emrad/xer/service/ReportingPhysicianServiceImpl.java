package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.ReportingPhysician;
import com.ge.hc.emrad.xer.repository.ReportingPhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Service
public class ReportingPhysicianServiceImpl implements ReportingPhysicianService {

    private ReportingPhysicianRepository reportingPhysicianRepository;

    @Autowired
    public void setReportingPhysicianRepository(ReportingPhysicianRepository reportingPhysicianRepository) {
        this.reportingPhysicianRepository = reportingPhysicianRepository;
    }

    @Override
    public Iterable<ReportingPhysician> getAllReportingPhysicians() {
        return reportingPhysicianRepository.findAll();
    }

    @Override
    public ReportingPhysician getReportingPhysicianById(Integer id) {
        return reportingPhysicianRepository.findOne(id);
    }

    @Override
    public ReportingPhysician saveReportingPhysician(ReportingPhysician reportingPhysician) {
        return reportingPhysicianRepository.save(reportingPhysician);
    }

    @Override
    public void deleteReportingPhysician(Integer id) {
        reportingPhysicianRepository.delete(id);

    }
}
