package com.example.tmm022_fmb.model;

public class Line {
    private String lineId;
    private String lineDescription;

    public Line(String lineId, String lineDescription) {
        this.lineId = lineId;
        this.lineDescription = lineDescription;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }
}
