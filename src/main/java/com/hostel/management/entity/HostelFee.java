package com.hostel.management.entity;

import jakarta.persistence.*;

@Entity
public class HostelFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;          // e.g., 2025
    private double feeAmount;  // e.g., 50000.0

    // Constructors
    public HostelFee() {}

    public HostelFee(int year, double feeAmount) {
        this.year = year;
        this.feeAmount = feeAmount;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getFeeAmount() { return feeAmount; }
    public void setFeeAmount(double feeAmount) { this.feeAmount = feeAmount; }
}
