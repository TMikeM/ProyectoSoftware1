package com.project.lunchuis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.lunchuis.Model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {}
