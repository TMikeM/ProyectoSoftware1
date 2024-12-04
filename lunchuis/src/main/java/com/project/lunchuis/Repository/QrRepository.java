package com.project.lunchuis.Repository;

import com.project.lunchuis.Model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrRepository extends JpaRepository<QrCode, Long> {
}

