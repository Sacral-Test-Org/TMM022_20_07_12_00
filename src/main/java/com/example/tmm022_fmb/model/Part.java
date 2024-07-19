package com.example.tmm022_fmb.model;

public class Part {
    private String partId;
    private String unitId;
    private String groupId;
    private String lineId;
    private String partNumber;
    private String partDescription;
    private String partStatus;

    // Constructor
    public Part(String partId, String unitId, String groupId, String lineId, String partNumber, String partDescription, String partStatus) {
        this.partId = partId;
        this.unitId = unitId;
        this.groupId = groupId;
        this.lineId = lineId;
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.partStatus = partStatus;
    }

    // Getters and Setters
    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(String partStatus) {
        this.partStatus = partStatus;
    }
}