package com.example.tmm022_fmb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.example.tmm022_fmb.model.Part;

public class PartRepository {
    private final DataSource dataSource;

    public PartRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean findByPartNumber(String partNumber, String unitId, String groupId, String lineId) throws SQLException {
        String query = "SELECT COUNT(*) FROM HPM_PART_MASTER WHERE PARTNO = ? AND UNIT_ID = ? AND GROUP_ID = ? AND LINE_ID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, partNumber);
            preparedStatement.setString(2, unitId);
            preparedStatement.setString(3, groupId);
            preparedStatement.setString(4, lineId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public Part save(Part part) throws SQLException {
        String query = "INSERT INTO HPM_PART_MASTER (UNIT_ID, GROUP_ID, PARTNO, PART_ID, PART_STATUS, PART_DESC, LINE_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, part.getUnitId());
            preparedStatement.setString(2, part.getGroupId());
            preparedStatement.setString(3, part.getPartNumber());
            preparedStatement.setString(4, part.getPartId());
            preparedStatement.setString(5, part.getPartStatus());
            preparedStatement.setString(6, part.getPartDescription());
            preparedStatement.setString(7, part.getLineId());
            preparedStatement.executeUpdate();
        }
        return part;
    }

    public Part update(Part part) throws SQLException {
        String query = "UPDATE HPM_PART_MASTER SET PART_STATUS = ?, PART_DESC = ? WHERE UNIT_ID = ? AND GROUP_ID = ? AND LINE_ID = ? AND PARTNO = ? AND PART_ID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, part.getPartStatus());
            preparedStatement.setString(2, part.getPartDescription());
            preparedStatement.setString(3, part.getUnitId());
            preparedStatement.setString(4, part.getGroupId());
            preparedStatement.setString(5, part.getLineId());
            preparedStatement.setString(6, part.getPartNumber());
            preparedStatement.setString(7, part.getPartId());
            preparedStatement.executeUpdate();
        }
        return part;
    }
}
