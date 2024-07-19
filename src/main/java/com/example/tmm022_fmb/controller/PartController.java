package com.example.tmm022_fmb.controller;

import com.example.tmm022_fmb.service.PartService;
import com.example.tmm022_fmb.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<String> saveOrUpdatePart(@RequestBody Part part) {
        // Validate required fields
        if (part.getPartId() == null || part.getPartId().isEmpty()) {
            return ResponseEntity.badRequest().body("Part ID is required.");
        }
        if (part.getUnitName() == null || part.getUnitName().isEmpty()) {
            return ResponseEntity.badRequest().body("Unit Name is required.");
        }
        if (part.getGroupName() == null || part.getGroupName().isEmpty()) {
            return ResponseEntity.badRequest().body("Group Name is required.");
        }
        if (part.getLineDescription() == null || part.getLineDescription().isEmpty()) {
            return ResponseEntity.badRequest().body("Line Description is required.");
        }
        if (part.getPartNumber() == null || part.getPartNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("Part Number is required.");
        }
        if (part.getPartDescription() == null || part.getPartDescription().isEmpty()) {
            return ResponseEntity.badRequest().body("Part Description is required.");
        }
        if (part.getPartStatus() == null || part.getPartStatus().isEmpty()) {
            return ResponseEntity.badRequest().body("Part Status is required.");
        }

        // Call the service to save or update the part
        String result = partService.saveOrUpdatePart(part);

        // Return the result
        return ResponseEntity.ok(result);
    }
}
