package com.project.lunchuis.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lunchuis.Model.Report;
import com.project.lunchuis.Repository.ReportRepository;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}

