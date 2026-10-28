package com.klef.soa.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.exam.entity.ElectricalRepair;
import com.klef.soa.exam.repository.ElectricalRepairRepository;

@Service
public class ElectricalRepairServiceImpl implements ElectricalRepairService {

    @Autowired
    private ElectricalRepairRepository repository;

    @Override
    public ElectricalRepair add(ElectricalRepair repair) {
        return repository.save(repair);
    }

    @Override
    public List<ElectricalRepair> displayAll() {
        return repository.findAll();
    }

    @Override
    public String delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Electrical Repair deleted successfully";
        } else {
            return "Electrical Repair ID Not Found";
        }
    }
}
