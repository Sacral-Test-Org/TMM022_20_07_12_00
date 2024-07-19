package com.example.tmm022_fmb.service;

import com.example.tmm022_fmb.repository.UnitRepository;
import com.example.tmm022_fmb.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private LineRepository lineRepository;

    public boolean validateUnitID(String unitID, String unitName, int globalParameter) {
        if (globalParameter == 0) {
            return unitRepository.validateUnitIDInMesUnitMaster(unitID, unitName);
        } else if (globalParameter == 1) {
            return unitRepository.validateUnitIDInMesAndHpmPartMaster(unitID, unitName);
        }
        return false;
    }

    public Object getLovData(int globalParameter) {
        if (globalParameter == 0) {
            return unitRepository.getLovDataFromMesUnitMaster();
        } else if (globalParameter == 1) {
            return unitRepository.getLovDataFromMesAndHpmPartMaster();
        }
        return null;
    }

    public boolean validatePartNumber(String partNumber, String unitId, int globalParameter) {
        if (globalParameter == 0) {
            return unitRepository.validatePartNumberInEiisPartMaster(partNumber, unitId);
        } else if (globalParameter == 1) {
            return unitRepository.validatePartNumberInHpmPartMaster(partNumber, unitId);
        }
        return false;
    }

    public Object getPartNumberLOV(int globalParameter) {
        if (globalParameter == 0) {
            return unitRepository.getPartNumberLovFromEiisPartMaster();
        } else if (globalParameter == 1) {
            return unitRepository.getPartNumberLovFromHpmPartMaster();
        }
        return null;
    }

    public boolean validateLineIdAndDescription(int globalParameter, String lineId, String lineDescription) {
        if (globalParameter == 0) {
            return lineRepository.validateLineIdAndDescriptionInLineLov(lineId, lineDescription);
        } else if (globalParameter == 1) {
            return lineRepository.validateLineIdAndDescriptionInEditLineLov(lineId, lineDescription);
        }
        return false;
    }

    public Object getGroupLovData(String unitId) {
        return unitRepository.fetchGroupLovData(unitId);
    }

    public Object getEditGroupLovData(String unitId) {
        return unitRepository.fetchEditGroupLovData(unitId);
    }

    public boolean validateGroupIdAndName(String groupId, String groupName, String unitId) {
        return unitRepository.validateGroupIdAndName(groupId, groupName, unitId);
    }
}