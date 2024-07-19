package com.example.tmm022_fmb.service;

import com.example.tmm022_fmb.repository.PartRepository;
import com.example.tmm022_fmb.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public String saveOrUpdatePart(Part part) {
        // Validate required fields
        if (part.getPartId() == null || part.getPartId().isEmpty()) {
            return "Part ID is required";
        }
        if (part.getUnitName() == null || part.getUnitName().isEmpty()) {
            return "Unit Name is required";
        }
        if (part.getGroupName() == null || part.getGroupName().isEmpty()) {
            return "Group Name is required";
        }
        if (part.getLineDescription() == null || part.getLineDescription().isEmpty()) {
            return "Line Description is required";
        }
        if (part.getPartNumber() == null || part.getPartNumber().isEmpty()) {
            return "Part Number is required";
        }
        if (part.getPartDescription() == null || part.getPartDescription().isEmpty()) {
            return "Part Description is required";
        }
        if (part.getPartStatus() == null || part.getPartStatus().isEmpty()) {
            return "Part Status is required";
        }

        // Check if part number already exists
        Part existingPart = partRepository.findByPartNumber(part.getPartNumber(), part.getUnitId(), part.getGroupId(), part.getLineId());
        if (existingPart != null) {
            if (part.getPartId().equals(existingPart.getPartId())) {
                // Update existing part
                existingPart.setPartStatus(part.getPartStatus());
                existingPart.setPartDescription(part.getPartDescription());
                partRepository.save(existingPart);
                return "Part updated successfully";
            } else {
                return "Part number already exists";
            }
        } else {
            // Insert new part
            part.setPartId("P" + (partRepository.count() + 1));
            partRepository.save(part);
            return "Part saved successfully";
        }
    }
}
