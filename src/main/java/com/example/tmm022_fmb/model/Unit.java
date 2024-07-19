package com.example.tmm022_fmb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MES_UNIT_MASTER")
public class Unit {

    @Id
    private String segmentCode;
    private String segmentName;

    public Unit() {}

    public Unit(String segmentCode, String segmentName) {
        this.segmentCode = segmentCode;
        this.segmentName = segmentName;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
}
