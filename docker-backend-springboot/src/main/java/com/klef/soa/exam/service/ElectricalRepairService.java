package com.klef.soa.exam.service;

import java.util.List;

import com.klef.soa.exam.entity.ElectricalRepair;

public interface ElectricalRepairService {

    ElectricalRepair add(ElectricalRepair repair);

    List<ElectricalRepair> displayAll();

    String delete(Long id);
}
