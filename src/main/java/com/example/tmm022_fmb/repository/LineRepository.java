package com.example.tmm022_fmb.repository;

import com.example.tmm022_fmb.model.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LineRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateLineIdAndDescription(int globalParameter, String unitId, String groupId, String lineId, String lineDescription) {
        String query;
        if (globalParameter == 0) {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE LINE_ID, LINE_DESC FROM HPM_LINE_MASTER WHERE UNIT_ID = ? AND GROUP_ID = ? AND LINE_ID = ? AND LINE_DESC = ?);";
        } else {
            query = "SELECT COUNT(*) FROM (SELECT UNIQUE B.LINE_ID, A.LINE_DESC FROM HPM_LINE_MASTER A, HPM_PART_MASTER B WHERE A.LINE_ID = B.LINE_ID AND B.UNIT_ID = ? AND B.GROUP_ID = ? AND B.LINE_ID = ? AND A.LINE_DESC = ?);";
        }
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{unitId, groupId, lineId, lineDescription}, Integer.class);
        return count != null && count > 0;
    }
}
