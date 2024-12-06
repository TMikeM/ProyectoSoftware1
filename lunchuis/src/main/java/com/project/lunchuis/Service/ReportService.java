package com.project.lunchuis.Service;

import com.project.lunchuis.Model.Buy;
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

    public List<Report> getReportsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return reportRepository.findByDateBetween(startDate, endDate);
    }

    public Report saveReport(Report report) {
        if (report.getHistoryPurchases() != null && !report.getHistoryPurchases().isEmpty()) {
            // Validar que todas las compras tengan una fecha asignada manualmente
            for (Buy purchase : report.getHistoryPurchases()) {
                if (purchase.getDate() == null) {
                    throw new IllegalArgumentException("Todas las compras asociadas deben tener una fecha asignada.");
                }
            }

            // Usar la primera fecha de las compras asociadas como fecha del reporte (si las compras tienen fecha)
            LocalDate reportDate = report.getHistoryPurchases()
                                         .stream()
                                         .map(Buy::getDate)
                                         .min(LocalDate::compareTo)
                                         .orElse(LocalDate.now());

            // Se asegura de que si la compra tiene una fecha asignada, esta se use
            report.setDate(reportDate);
        } else {
            // Si no hay compras asociadas, lanza un error o usa una fecha predeterminada
            throw new IllegalArgumentException("El reporte debe tener al menos una compra asociada.");
        }

        return reportRepository.save(report);
    }
}
