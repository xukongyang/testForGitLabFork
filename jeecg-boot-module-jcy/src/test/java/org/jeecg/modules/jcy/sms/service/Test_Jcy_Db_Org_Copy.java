package org.jeecg.modules.jcy.sms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {RuoYiApplication.class})
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Test_Jcy_Db_Org_Copy extends Test_Jcy_Db_Base {
    
    public List<OrgInfo> getOrgInfoFromSqlServer(JdbcTemplate jdbcTemplate, int canProcessDataNumber) {
        List<OrgInfo> listData = new ArrayList<>();

        String seprater = ",";
        String sql = "SELECT top " + canProcessDataNumber + " * FROM [CoCallS10].[common].[common_org_info] where orgidpath like '/1/%' and status = 1 order by createdt asc";
        String data = " ";

        listData = jdbcTemplate.query(sql, new Object[]{},
                new RowMapper<OrgInfo>() {
                    @Override
                    public OrgInfo mapRow(ResultSet rs, int i) throws SQLException {

                        OrgInfo data = new OrgInfo();
                        data.setOrgId("" + rs.getInt("orgid"));
                        data.setOrgName(rs.getString("orgname"));
                        data.setOrgType("" + rs.getInt("orgtype"));
                        data.setSuperOrgId("" + rs.getInt("superorgid"));
                        data.setStatus("" + rs.getInt("status"));
                        data.setOrgIdPath("" + rs.getInt("orgidpath"));
                        data.setOrgPath("" + rs.getInt("orgpath"));

                        return data;
                    }
                });
        return listData;
    }

}
