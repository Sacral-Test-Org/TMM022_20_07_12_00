package com.example.tmm022_fmb.controller;

import com.example.tmm022_fmb.service.UnitService;
import com.example.tmm022_fmb.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;

    @Autowired
    private LineService lineService;

    @PostMapping("/validateUnitID")
    public ResponseEntity<String> validateUnitID(@RequestParam String unitID, @RequestParam String unitName, @RequestParam int globalParameter) {
        boolean isValid = unitService.validateUnitID(unitID, unitName, globalParameter);
        if (isValid) {
            return ResponseEntity.ok("Validation successful");
        } else {
            return ResponseEntity.badRequest().body("Validation failed");
        }
    }

    @GetMapping("/getLovData")
    public ResponseEntity<Object> getLovData(@RequestParam int globalParameter) {
        Object lovData = unitService.getLovData(globalParameter);
        return ResponseEntity.ok(lovData);
    }

    @PostMapping("/validatePartNumber")
    public ResponseEntity<String> validatePartNumber(@RequestParam String partNumber, @RequestParam String unitId, @RequestParam int globalParameter) {
        boolean isValid = unitService.validatePartNumber(partNumber, unitId, globalParameter);
        if (isValid) {
            return ResponseEntity.ok("Validation successful");
        } else {
            return ResponseEntity.badRequest().body("Validation failed");
        }
    }

    @GetMapping("/getPartNumberLOV")
    public ResponseEntity<Object> getPartNumberLOV(@RequestParam int globalParameter) {
        Object lovData = unitService.getPartNumberLOV(globalParameter);
        return ResponseEntity.ok(lovData);
    }

    @PostMapping("/validateLineIdAndDescription")
    public ResponseEntity<String> validateLineIdAndDescription(@RequestParam int globalParameter, @RequestParam String lineId, @RequestParam String lineDescription) {
        boolean isValid = lineService.validateLineIdAndDescription(globalParameter, lineId, lineDescription);
        if (isValid) {
            return ResponseEntity.ok("Validation successful");
        } else {
            return ResponseEntity.badRequest().body("Validation failed");
        }
    }

    @GetMapping("/getGroupLovData")
    public ResponseEntity<Object> getGroupLovData(@RequestParam String unitId) {
        Object lovData = unitService.getGroupLovData(unitId);
        return ResponseEntity.ok(lovData);
    }

    @GetMapping("/getEditGroupLovData")
    public ResponseEntity<Object> getEditGroupLovData(@RequestParam String unitId) {
        Object lovData = unitService.getEditGroupLovData(unitId);
        return ResponseEntity.ok(lovData);
    }

    @PostMapping("/validateGroupIdAndName")
    public ResponseEntity<String> validateGroupIdAndName(@RequestParam String groupId, @RequestParam String groupName, @RequestParam String unitId) {
        boolean isValid = unitService.validateGroupIdAndName(groupId, groupName, unitId);
        if (isValid) {
            return ResponseEntity.ok("Validation successful");
        } else {
            return ResponseEntity.badRequest().body("Validation failed");
        }
    }
}