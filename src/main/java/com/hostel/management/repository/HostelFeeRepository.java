package com.hostel.management.repository;

import com.hostel.management.entity.HostelFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 

public interface HostelFeeRepository extends JpaRepository<HostelFee, Long> {
    HostelFee findByYear(int year); // get fee for a particular year
    @Query(value = "SELECT f FROM HostelFee f ORDER BY f.year DESC LIMIT 1")
    HostelFee findLatestFee();
}
