package com.project.lunchuis.Controller;

import com.project.lunchuis.Model.Report;
import com.project.lunchuis.Service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Report>> getReportsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Report> reports = reportService.getReportsByDate(date);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/with-buys/between-dates")
public ResponseEntity<List<Report>> getReportsWithBuysBetweenDates(
        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    List<Report> reports = reportService.getReportsWithBuysBetweenDates(startDate, endDate);
    return ResponseEntity.ok(reports);
}


    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report savedReport = reportService.saveReport(report);
        return ResponseEntity.ok(savedReport);
    }
}
