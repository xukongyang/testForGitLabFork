package org.jeecg.modules.jcy.sms.service;

import org.jeecg.common.api.vo.Result;
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
public class Test_Jcy_Db_Desensitization extends Test_Jcy_Db_Base {

    @Test
    public void test_db_数据脱敏() {

        //更新测试sqlserver数据库中，注意：是测试用的sqlserver数据库中的 用户数据
        //将用户的姓名，性别，手机号全部更改为随机的，避免敏感数据泄漏

        //testMySql(jdbcTemplateMySql);
        List<UserInfo> listUserInfoFromSqlServer = getUserInfoFromSqlServer(jdbcTemplateSqlServer, 30);

        processDataNumberMax = listUserInfoFromSqlServer.size();

        for(UserInfo userInfo : listUserInfoFromSqlServer) {

            processDataNumberNow++;

            String loginName = userInfo.getLoginName();

            if (loginName.indexOf("admin") >= 0) {
                System.out.println(loginName + " 管理员用户不用更改，跳过!");
                continue;
            }

            String randomSex = (RandomName.getChineseName()).split(":")[0];
            String randomName = (RandomName.getChineseName()).split(":")[1];
            //System.out.println("randomName " + randomName);

            String nameFirst = getFirstSpell(randomName);
            //System.out.println("" + randomName + " 姓名首字母 " + nameFirst);

            String randomMobile = RandomPhoneNumber.createMobile(0);
            //System.out.println("randomMobile " + randomMobile);

            String randomEmail = RandomEmail.getEmail(3, 8);
            //System.out.println("randomEmail " + randomEmail);

            if (outputDebugInfo) {
                System.out.println("替换前 用户数据 " + userInfo);
            }

            userInfo.setRealName(randomName);
            userInfo.setLoginName(nameFirst);
            userInfo.setMobile(randomMobile);

            //userInfo.setEmail(randomEmail);
            userInfo.setEmail(randomMobile + "@139.com");

            if (outputDebugInfo) {
                System.out.println("替换后 用户数据 " + userInfo);
            }

            //int iDbCount = jdbcTemplate.update(

            String sqlUpdateName = "update common.common_user_info set "
                    + " username = '" + userInfo.getRealName() + "', "
                    + " gender = '" + randomSex + "', "
                    + " mobile = '" + userInfo.getMobile() + "' "

                    + " where userid = " + userInfo.getId() + " ";
            String sqlSelectName = "select * from common.common_user_info "
                    + " where userid = " + userInfo.getId() + " ";

            String sqlUpdateMobile = "update common.common_user_phonenum set "
                    + " phone_num = '" + userInfo.getMobile() + "' "

                    + " where userid = " + userInfo.getId() + " ";

            String sqlSelectMobile = "select * from common.common_user_phonenum "
                    + " where userid = " + userInfo.getId() + " ";

            int iDbCount1 = jdbcTemplateSqlServer.update(sqlUpdateName);
            int iDbCount2 = jdbcTemplateSqlServer.update(sqlUpdateMobile);

            processDataNumberOK1 = processDataNumberOK1 + iDbCount1;
            processDataNumberOK2 = processDataNumberOK1 + iDbCount2;

            if (outputDebugInfo) {
                System.out.println("更新 用户姓名的sql语句 \n" + sqlUpdateName);
                System.out.println("更新 用户手机号码的sql语句 \n" + sqlUpdateMobile);

                System.out.println("查询 用户信息的sql语句 \r\n" + sqlSelectName);
                System.out.println("\r\n" + sqlSelectMobile);
            }

            if (processDataNumberNow % processDataNumberNeedOutputRunInfo == 0) {
                System.out.println(new Date() + " 系统已处理 " + processDataNumberNow + "/" + processDataNumberMax);
            }
        }//for

        System.out.println(new Date() + " 系统结束运行，已处理 " + processDataNumberNow + "/" + processDataNumberMax);

        System.out.println(new Date() + " 更新用户姓名成功条数 " + processDataNumberOK1  + "/" + processDataNumberMax);
        System.out.println(new Date() + " 更新用户手机成功条数 " + processDataNumberOK2 + "/" + processDataNumberMax
                + " 因为一个用户可能有多个手机，所以这个成功条数可能会大于被更新的用户数！");
    }//end method

}
