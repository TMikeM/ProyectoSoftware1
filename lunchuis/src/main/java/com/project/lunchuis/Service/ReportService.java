package com.project.lunchuis.Service;

import com.project.lunchuis.Model.Report;
import com.project.lunchuis.Repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getReportsByDate(LocalDate date) {
        return reportRepository.findByDate(date);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}
