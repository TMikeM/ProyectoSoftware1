package com.project.lunchuis.Repository;

import com.project.lunchuis.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Report> findByDate(LocalDate date);

}
