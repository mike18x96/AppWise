package com.appwise.appwise.repository;

import com.appwise.appwise.model.CaseInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseInsuranceRepository extends JpaRepository<CaseInsurance, Long> {
}
