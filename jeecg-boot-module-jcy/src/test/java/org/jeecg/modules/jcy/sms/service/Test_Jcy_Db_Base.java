package org.jeecg.modules.jcy.sms.service;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {RuoYiApplication.class})
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Test_Jcy_Db_Base {

    //@Autowired
    public static JdbcTemplate jdbcTemplateMySql;
    public static JdbcTemplate jdbcTemplateSqlServer;

    public static boolean outputDebugInfo = false;//是否输出调试信息
    public static int processDataNumberMax = 0;//需要处理的数据条数
    public static int processDataNumberNow = 0;//已经处理的数据条数
    public static int processDataNumberOK1 = 0;//已经处理的数据条数
    public static int processDataNumberOK2 = 0;//已经处理的数据条数
    public static int processDataNumberNeedOutputRunInfo = 1000;//每处理多少条数据输出处理提示信息

    public static DataSource getDataSourceMySql() {
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String JDBC_URL = "jdbc:mysql://localhost:3306/jeecg-boot?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String USERNAME = "root";
        String PASSWORD = "root";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    public static DataSource getDataSourceSqlServer() {
        String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
        String JDBC_URL = "jdbc:jtds:sqlserver://127.0.0.1/CoCallS10";
        String USERNAME = "sa";
        String PASSWORD = "Root@Root";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    @BeforeClass
    public static void beforeClass() {
        //System.out.println("BeforeClass start");

        DataSource sourceMysql = getDataSourceMySql();
        jdbcTemplateMySql = new JdbcTemplate(sourceMysql);

        DataSource sourceSqlServer = getDataSourceSqlServer();
        jdbcTemplateSqlServer = new JdbcTemplate(sourceSqlServer);

        System.out.println("BeforeClass end");
    }

    public List<UserInfo> testMySql(JdbcTemplate jdbcTemplate) {
        List<UserInfo> listData = new ArrayList<>();

        String seprater = ",";
        String sql = "select * from sys_user limit 1";
        String data = "mysql ";

        listData = jdbcTemplate.query(sql, new Object[]{},
                new RowMapper<UserInfo>() {
                    @Override
                    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {

                        UserInfo data = new UserInfo();
                        data.setId(rs.getString("id"));
                        data.setLoginName(rs.getString("username"));
                        data.setRealName(rs.getString("realname"));

                        return data;
                    }
                });
        return listData;
    }

    public List<UserInfo> getUserInfoFromSqlServer(JdbcTemplate jdbcTemplate, int canProcessDataNumber) {
        List<UserInfo> listData = new ArrayList<>();

        String seprater = ",";
        String sql = "select top " + canProcessDataNumber + " * from common.common_user_info";
        String data = " ";

        listData = jdbcTemplate.query(sql, new Object[]{},
                new RowMapper<UserInfo>() {
                    @Override
                    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {

                        UserInfo data = new UserInfo();
                        data.setId(rs.getString("userid"));
                        data.setLoginName(rs.getString("loginname"));
                        data.setRealName(rs.getString("username"));

                        return data;
                    }
                });
        return listData;
    }

    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }

}
