package com.klef.soa.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "electrical_repair")
public class ElectricalRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "technician_name", nullable = false)
    private String technicianName;

    @Column(name = "appliance_type", nullable = false)
    private String applianceType;

    @Column(name = "repair_cost", nullable = false)
    private Double repairCost;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    public ElectricalRepair() {
    }

    public ElectricalRepair(Long id, String technicianName, String applianceType,
                            Double repairCost, Boolean completed) {
        this.id = id;
        this.technicianName = technicianName;
        this.applianceType = applianceType;
        this.repairCost = repairCost;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }

    public Double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(Double repairCost) {
        this.repairCost = repairCost;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "ElectricalRepair [id=" + id + ", technicianName=" + technicianName
                + ", applianceType=" + applianceType + ", repairCost=" + repairCost
                + ", completed=" + completed + "]";
    }
}
