package com.example.tmm022_fmb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitRepository {
    private Connection connection;

    public UnitRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean validateUnitID(String unitID, String unitName, int globalParameter) throws SQLException {
        String query;
        if (globalParameter == 0) {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE SEGMENT_CODE, SEGMENT_NAME FROM MES_UNIT_MASTER WHERE SEGMENT_CODE = ? AND SEGMENT_NAME = ?);";
        } else {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE B.UNIT_ID SEGMENT_CODE, A.SEGMENT_NAME FROM MES_UNIT_MASTER A, HPM_PART_MASTER B WHERE B.UNIT_ID = A.SEGMENT_CODE AND B.UNIT_ID = ? AND A.SEGMENT_NAME = ?);";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitID);
            stmt.setString(2, unitName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<String[]> getLovData(int globalParameter) throws SQLException {
        String query;
        if (globalParameter == 0) {
            query = "SELECT UNIQUE SEGMENT_CODE, SEGMENT_NAME FROM MES_UNIT_MASTER ORDER BY 1 ASC;";
        } else {
            query = "SELECT UNIQUE B.UNIT_ID SEGMENT_CODE, A.SEGMENT_NAME FROM MES_UNIT_MASTER A, HPM_PART_MASTER B WHERE B.UNIT_ID = A.SEGMENT_CODE ORDER BY 1 ASC;";
        }
        List<String[]> lovData = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lovData.add(new String[]{rs.getString(1), rs.getString(2)});
            }
        }
        return lovData;
    }

    public boolean validatePartNumber(String partNumber, String unitId, int globalParameter) throws SQLException {
        String query;
        if (globalParameter == 0) {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE PART_NO, DESCRIPTION FROM EIIS_PART_MASTER WHERE SEGMENTCODE = ? AND PART_STATUS = 'A' AND PART_NO = ?);";
        } else {
            query = "SELECT COUNT(*) FROM (SELECT PART_ID, PARTNO, PART_DESC, PART_STATUS FROM HPM_PART_MASTER WHERE UNIT_ID = ? AND PARTNO = ?);";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitId);
            stmt.setString(2, partNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<String[]> getPartNumberLOV(int globalParameter) throws SQLException {
        String query;
        if (globalParameter == 0) {
            query = "SELECT UNIQUE PART_NO, DESCRIPTION FROM EIIS_PART_MASTER ORDER BY 1 ASC;";
        } else {
            query = "SELECT UNIQUE PART_ID, PARTNO, PART_DESC FROM HPM_PART_MASTER ORDER BY 1 ASC;";
        }
        List<String[]> lovData = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lovData.add(new String[]{rs.getString(1), rs.getString(2)});
            }
        }
        return lovData;
    }

    public boolean validateLineIdAndDescription(int globalParameter, String lineId, String lineDescription) throws SQLException {
        String query;
        if (globalParameter == 0) {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE LINE_ID, LINE_DESC FROM HPM_LINE_MASTER WHERE LINE_ID = ? AND LINE_DESC = ?);";
        } else {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE B.LINE_ID, A.LINE_DESC FROM HPM_LINE_MASTER A, HPM_PART_MASTER B WHERE A.LINE_ID = B.LINE_ID AND B.LINE_ID = ? AND A.LINE_DESC = ?);";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lineId);
            stmt.setString(2, lineDescription);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<String[]> fetchGroupLovData(String unitId) throws SQLException {
        String query = "SELECT UNIQUE GROUP_ID, GROUP_NAME FROM MES_GROUP_MASTER WHERE GROUP_STATUS='O' AND GROUP_SECTION=? ORDER BY 1 ASC;";
        List<String[]> lovData = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lovData.add(new String[]{rs.getString(1), rs.getString(2)});
                }
            }
        }
        return lovData;
    }

    public List<String[]> fetchEditGroupLovData(String unitId) throws SQLException {
        String query = "SELECT UNIQUE B.GROUP_ID, A.GROUP_NAME FROM MES_GROUP_MASTER A, HPM_PART_MASTER B WHERE B.GROUP_ID=A.GROUP_ID AND B.UNIT_ID=? ORDER BY 1 ASC;";
        List<String[]> lovData = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lovData.add(new String[]{rs.getString(1), rs.getString(2)});
                }
            }
        }
        return lovData;
    }

    public boolean validateGroupIdAndName(String groupId, String groupName, String unitId) throws SQLException {
        String query = "SELECT COUNT(*) FROM (SELECT UNIQUE GROUP_ID, GROUP_NAME FROM MES_GROUP_MASTER WHERE GROUP_STATUS='O' AND GROUP_SECTION=? AND GROUP_ID=? AND GROUP_NAME=?);";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitId);
            stmt.setString(2, groupId);
            stmt.setString(3, groupName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
