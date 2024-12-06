package com.project.lunchuis.Repository;

import com.project.lunchuis.Model.PurchaseValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseValueRepository extends JpaRepository<PurchaseValue, Long> {
}
