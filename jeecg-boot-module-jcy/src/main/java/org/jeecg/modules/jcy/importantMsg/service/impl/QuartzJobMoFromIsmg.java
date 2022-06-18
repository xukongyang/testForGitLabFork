package org.jeecg.modules.jcy.importantMsg.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzcmcc.ismgclient.db.domain.MsgMoLog;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import org.jeecg.modules.jcy.importantMsg.mapper.ImportantMsgUserSmsMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
public class QuartzJobMoFromIsmg implements Job {

    //@Autowired
    //private RedisUtil redisUtil;
    //@Autowired
    //private ImportantMsgUserSmsMapper importantMsgUserSmsMapper;
    //String listRedisMoStatFromIsmg = "list:jcy:mo_stat:from:ismg";
    @Autowired
    JdbcTemplate jdbcTemplate;

    String listRedisMoStatFromIsmg = "list_icp_wait_ismg_851010";
    MsgJsonUtils msgJsonUtils = new MsgJsonUtils();

    @Autowired
    private ISysBaseAPI sysBaseAPI;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplateObject;

    public String lLeftPop(String listKey) {
        //绑定操作
        BoundListOperations<String, String> boundValueOperations = redisTemplate.boundListOps(listKey);
        return boundValueOperations.leftPop();
    }
    public Object lLeftPopObject(String listKey) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplateObject.boundListOps(listKey);
        return boundValueOperations.leftPop();
    }

    /**
     * 若参数变量名修改 QuartzJobController中也需对应修改
     */
    private String parameter;
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //log.info(" Job Execution key："+jobExecutionContext.getJobDetail().getKey());

        log.info( String.format("Jeecg-Boot 带参数定时任务 %s 执行!   时间:" + DateUtils.now(), this.parameter));

        String json_str= "{\"name\":\"lily\",\"age\":12}";
        json_str = this.parameter;

        JSONObject jsonObject = JSON.parseObject(json_str);
        //System.out.println(jsonObject.getString("name")+":"+jsonObject.getInteger("age"));

        String dictCode = "user_status";
        /*List<DictModel> dictItemByCode = sysDictService.queryDictItemsByCode(dictCode);
        for(DictModel dictModel : dictItemByCode) {
            System.out.println(dictCode + " dictModel " + dictModel);
        }
        String text = sysDictService.queryDictTextByKey(dictCode, "1");
        System.out.println(dictCode + " 1 " + text);*/

        List<DictModel> dictItemByCode = sysBaseAPI.getDictItems(dictCode);
        //sysBaseAPI.loadDictItemByKeyword(dictCode, "1", 10);
        for(DictModel dictModel : dictItemByCode) {
            //log.info(dictCode + " dictModel " + dictModel);
        }

        //MsgMoLog msgMoLog = (MsgMoLog)lLeftPopObject(listRedisMoStatFromIsmg);
        String jsonOfObject = lLeftPop(listRedisMoStatFromIsmg);

        while (jsonOfObject != null) {
        //    while (msgMoLog != null) {

            MsgMoLog msgMoLog = (MsgMoLog)msgJsonUtils.json2Object(jsonOfObject, MsgMoLog.class);

            //if (importantMsgUserSms != null) {
			/*System.out.println("jsonObjectImportantMsgUserSms " + jsonObjectContent);
		 	JSONObject JSONObjectImportantMsgUserSms = JSONObject.parseObject(jsonObjectContent);
			ImportantMsgUserSms importantMsgUserSms = JSON.toJavaObject(JSONObjectImportantMsgUserSms, ImportantMsgUserSms.class);*/

                //if (params.equals("redis")) {
            //msgMoLog = null;
            //msgMoLog = (MsgMoLog)lLeftPopObject(listRedisMoStatFromIsmg);

            if (1 == msgMoLog.getRegistered_delivery() ) {
                //说明是状态报告
                log.info("接收到状态报告 msgMoLog " + msgMoLog);

                String sql = "update common_meeting_user_sms_info set sms_mt_stat = ? where id = ?";

                jdbcTemplate.update(sql,
                        msgMoLog.getStat(),
                        msgMoLog.getMsg_id());
            }
            if (0 == msgMoLog.getRegistered_delivery() ) {
                //说明是正常mo上行短消息
            }

            jsonOfObject = null;
            jsonOfObject = lLeftPop(listRedisMoStatFromIsmg);


        }


    }

}
