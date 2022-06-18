package org.jeecg.modules.jcy.sms.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.jcy.sms.entity.MsgMtLog;
import org.jeecg.modules.jcy.sms.mapper.MsgMtLogMapper;
import org.jeecg.modules.jcy.sms.service.IMsgMtLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 短信发送日志
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class TDEngineServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@DS("multi-datasource1")
    public List<Map<String, Object>> selectAll() {
        return  jdbcTemplate.queryForList("select * from demo.t");
    }

}
