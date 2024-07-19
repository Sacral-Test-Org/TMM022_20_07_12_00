package com.example.tmm022_fmb.service;

import com.example.tmm022_fmb.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Map<String, Object>> getGroupLovData(String unitId) {
        return groupRepository.fetchGroupLovData(unitId);
    }

    public List<Map<String, Object>> getEditGroupLovData(String unitId) {
        return groupRepository.fetchEditGroupLovData(unitId);
    }

    public boolean validateGroupIdAndName(String groupId, String groupName, String unitId, int globalParam) {
        int count;
        if (globalParam == 0) {
            count = groupRepository.validateGroupIdAndNameForGlobalParam0(groupId, groupName, unitId);
        } else {
            count = groupRepository.validateGroupIdAndNameForGlobalParam1(groupId, groupName, unitId);
        }
        return count > 0;
    }
}
