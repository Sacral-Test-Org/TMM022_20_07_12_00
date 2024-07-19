package com.example.tmm022_fmb.controller;

import com.example.tmm022_fmb.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/group-lov")
    public ResponseEntity<?> getGroupLovData(@RequestParam String unitId) {
        return ResponseEntity.ok(groupService.getGroupLovData(unitId));
    }

    @GetMapping("/edit-group-lov")
    public ResponseEntity<?> getEditGroupLovData(@RequestParam String unitId) {
        return ResponseEntity.ok(groupService.getEditGroupLovData(unitId));
    }

    @GetMapping("/validate-group")
    public ResponseEntity<?> validateGroupIdAndName(@RequestParam String groupId, @RequestParam String groupName, @RequestParam String unitId) {
        return ResponseEntity.ok(groupService.validateGroupIdAndName(groupId, groupName, unitId));
    }
}
