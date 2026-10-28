package com.klef.soa.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.exam.entity.ElectricalRepair;
import com.klef.soa.exam.service.ElectricalRepairService;

@CrossOrigin("*")
@RestController
@RequestMapping("/electricalrepair")
public class ElectricalRepairController {

    @Autowired
    private ElectricalRepairService service;

   
    @GetMapping("/")
    public String home() {
        return "CRUD Service is running";
    }

   
    @PostMapping("/Add")
    public ResponseEntity<ElectricalRepair> addElectricalRepair(
            @RequestBody ElectricalRepair repair) {

        ElectricalRepair savedRepair = service.add(repair);

        return new ResponseEntity<>(savedRepair, HttpStatus.CREATED);
    }

   
    @GetMapping("/DisplayAll")
    public ResponseEntity<List<ElectricalRepair>> displayAll() {

        List<ElectricalRepair> repairs = service.displayAll();

        return ResponseEntity.ok(repairs);
    }

   
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deleteElectricalRepair(
            @PathVariable Long id) {

        String message = service.delete(id);

        if (message.equals("Electrical Repair deleted successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
