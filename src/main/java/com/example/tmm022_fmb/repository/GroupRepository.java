package com.example.tmm022_fmb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    private final Connection connection;

    public GroupRepository(Connection connection) {
        this.connection = connection;
    }

    public List<String[]> fetchGroupLovData(String unitId) throws SQLException {
        String query = "SELECT UNIQUE GROUP_ID, GROUP_NAME FROM MES_GROUP_MASTER WHERE GROUP_STATUS='O' AND GROUP_SECTION=? ORDER BY 1 ASC";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitId);
            try (ResultSet rs = stmt.executeQuery()) {
                List<String[]> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new String[]{rs.getString("GROUP_ID"), rs.getString("GROUP_NAME")});
                }
                return result;
            }
        }
    }

    public List<String[]> fetchEditGroupLovData(String unitId) throws SQLException {
        String query = "SELECT UNIQUE B.GROUP_ID, A.GROUP_NAME FROM MES_GROUP_MASTER A, HPM_PART_MASTER B WHERE B.GROUP_ID=A.GROUP_ID AND B.UNIT_ID=? ORDER BY 1 ASC";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitId);
            try (ResultSet rs = stmt.executeQuery()) {
                List<String[]> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new String[]{rs.getString("GROUP_ID"), rs.getString("GROUP_NAME")});
                }
                return result;
            }
        }
    }

    public boolean validateGroupIdAndName(String groupId, String groupName, String unitId, int globalParam) throws SQLException {
        String query;
        if (globalParam == 0) {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE GROUP_ID, GROUP_NAME FROM MES_GROUP_MASTER WHERE GROUP_STATUS='O' AND GROUP_SECTION=? AND GROUP_ID=? AND GROUP_NAME=?)";
        } else {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE B.GROUP_ID, A.GROUP_NAME FROM MES_GROUP_MASTER A, HPM_PART_MASTER B WHERE B.GROUP_ID=A.GROUP_ID AND B.UNIT_ID=? AND B.GROUP_ID=? AND A.GROUP_NAME=?)";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unitId);
            stmt.setString(2, groupId);
            stmt.setString(3, groupName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }
}