package com.example.tmm022_fmb.controller;

import com.example.tmm022_fmb.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping("/validateLineIdAndDescription")
    public ResponseEntity<String> validateLineIdAndDescription(@RequestParam int globalParameter, 
                                                               @RequestParam String lineId, 
                                                               @RequestParam String lineDescription) {
        boolean isValid = lineService.validateLineIdAndDescription(globalParameter, lineId, lineDescription);
        if (isValid) {
            return ResponseEntity.ok("Validation successful");
        } else {
            return ResponseEntity.status(400).body("Validation failed");
        }
    }
}
