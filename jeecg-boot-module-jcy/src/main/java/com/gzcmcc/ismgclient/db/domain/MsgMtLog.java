package com.gzcmcc.ismgclient.db.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.*;

public class MsgMtLog implements Serializable
{
	private static final long serialVersionUID = 1L;

	//protected Map<String, String> mapRunInfo = new HashMap<String, String>();
	
	//private List<String> listMsgFile = new ArrayList<String>();

	private Integer id = 0;    //自增唯一流水号
	private String submit_user_id = "0";//发送用户代码,例如单位中的某个用户
	private String submit_mobile_type = "cmcc";//手机所属运营商,默认为移动cmcc
	private String submit_owner_id = "0";//发送相关业务号码,例如一个会议代码,一个宣传活动代码
	
	private String msg_type = "sms";    //消息类型短信sms彩信mms 默认需要设置为sms，否则短信发送模块默认会设置为静默短信
	private String submit_type = "";    //发送类型,网页发送,api发送等
	private String msg_subject = "msg_subject";    ///彩信主题
	private String msg_descript = "msg_descript";    //彩信描述
	private String msg_id = null;    //mas自身唯一流水号 uuid
	private String ismg_msg_id = null;    //发送给网关,网关返回的唯一流水号
	private Integer pk_total = 1;    //cmpp协议字段,默认为1,长短信时修改
	private Integer pk_number = 1;    //cmpp协议字段,默认为1,长短信时修改
	private Integer registered_delivery = 1;    //是否需要状态报告,默认都需要0不需要1需要
	private Integer msg_level = 0;   //消息等级,默认最低
	/*优先级,默认最低,因为数字越大,优先级越低*/
	private String service_id = "";    /*业务代码 必须有值，否则发送会报错*/
	
	private Integer fee_usertype= 0; //cmpp协议字段,计费用户类型
	private String fee_terminal_id = "0";//cmpp协议字段,计费号码
	private Integer fee_terminal_type = 0;//cmpp协议字段,计费号码类型
	
	private Integer tp_pid = 0;//cmpp协议字段,特定消息类型时使用
	private Integer tp_udhi = 0; //cmpp协议字段,特定类型消息时使用
	
	private Integer msg_fmt = 8;    //cmpp协议字段,消息字符编码
	private String msg_src = "msgsrc";   
	/*cmpp协议字段,填写网关分配的用户名,注意msgsrc的长度只有6，超过了发送不出去*/
	private String feetype = "01";    //cmpp协议字段,计费类型
	private String feecode = "00";    //cmpp协议字段,计费代码
	private Date valid_time;    //cmpp协议字段,消息存活时间,null,默认为1天
	private Date at_time;    //cmpp协议字段,消息定时发送时间,null立即发送
	private String src_terminal_id = "";    //cmpp协议字段,源号码字段,必须与网关分配号码开头
	private Integer destusr_tl = 1;    //cmpp协议字段,接收短信号码个数
	private String dest_terminal_id = "";    //cmpp协议字段,接收短信号码
	
	private Integer dest_terminal_type = 0;//cmpp协议字段,接收短信手机号码类型
	
	private Integer msg_length = 0;    //cmpp协议字段,消息长度
	private String msg_content = "";    //cmpp协议字段,消息内容
	
	private String linkid = "0";//cmpp协议字段,3.0使用
	
	private String reserve = "00000000";    //cmpp协议字段,保留
	private Date smsc_status_rep;    //状态报告返回时间
	private String msg_id_r = null;    //状态报告对应的网关唯一流水号
	private String stat = "";    //状态报告值,7位长,DELIVRD为已接收,EXPIRED过期等
	private Date submit_time;    //消息发送时间
	private Date done_time;    //消息被接收时间
	private String dest_ter_id = "dest_terminal_id";    //状态报告中的目的号码,即接收短信的手机号
	private Integer smsc_sequence = 0;    //短信中心对此条消息的唯一流水号

	private String icp_id = "";    //发送短信的mas用户代码

	private Date icp_submit_time;    //用户发送短信时间
	private Date ismg_submit_time;    //用户短信向网关发送时间
	private Date db_insert_time;    //用户消息入库时间
	private Integer icp_submit_result = 0;    //发送结果代码
	private String icp_submit_desc = "";    //发送结果描述
	private Integer billend = 0;    //是否已出账
	private Date billend_time;    //出账结束时间
	
	private Date expiredTime = null;//过期时间

	private byte[] msg_content_bytes;
	
	private String queueName;//此条消息属于哪个队列,使用java类型队列时使用
	
	private int debugCycleCount = 1;//调试用,用来发送一条消息后生成多条测试短信

	public MsgMtLog() {
		
	}
	
	public MsgMtLog(
			String dest_terminal_id,
			String msg_content,
			Date at_time
			) {
		this.dest_terminal_id = dest_terminal_id;
		this.msg_content = msg_content;
		this.at_time = at_time;
	}
	
	public MsgMtLog(
			String msg_type,
			String dest_terminal_id,
			String msg_content,
			String mmsFile,
			Date at_time			
			) {
		this.msg_type = msg_type;
		this.dest_terminal_id = dest_terminal_id;
		this.msg_content = msg_content;
		//this.listMsgFile.add(mmsFile);
		this.at_time = at_time;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	/*public String getRunInfo(String key) {
		if (mapRunInfo.get(key) != null)
			return (String) mapRunInfo.get(key).toString();
		else
			return "无错误信息";
	}*/
	public void clear() {
		this.msg_content_bytes = null;
		/*
		this.listMmsFile.clear();
		this.listMmsFile = null;
		this.mapReturnRunInfo.clear();
		this.mapReturnRunInfo = null;
		 */
	}

	/////////////////////////////////////////////////////////////////
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setMsg_type(String value){
		this.msg_type = value;
	}
	public String getMsg_type() {
		return this.msg_type;
	}
	public String getSubmit_type() {
		return submit_type;
	}
	public void setSubmit_type(String submit_type) {
		this.submit_type = submit_type;
	}
	
	public String getMsg_subject() {
		return msg_subject;
	}
	public void setMsg_subject(String msg_subject) {
		this.msg_subject = msg_subject;
	}
	public String getMsg_descript() {
		return msg_descript;
	}
	public void setMsg_descript(String msg_descript) {
		this.msg_descript = msg_descript;
	}
	public void setMsg_id(String value)  /**/  {
		this.msg_id = value;
}
	public String getMsg_id() {
		return this.msg_id;
	}
	public void setIsmg_msg_id(String value)  /**/  {
		this.ismg_msg_id = value;
}
	public String getIsmg_msg_id() {
		return this.ismg_msg_id;
	}

	public Integer getPk_total() {
		return this.pk_total;
	}
	public Integer getPk_number() {
		return this.pk_number;
	}

	public Integer getRegistered_delivery() {
		return this.registered_delivery;
	}

	public Integer getMsg_level() {
		return this.msg_level;
	}
	public void setService_id(String value)  /**/  {
		this.service_id = value;
}
	public String getService_id() {
		return this.service_id;
	}

	public Integer getMsg_fmt() {
		return this.msg_fmt;
	}
	public void setMsg_src(String value)  /**/  {
		this.msg_src = value;
}
	public String getMsg_src() {
		return this.msg_src;
	}
	public void setFeetype(String value)  /**/  {
		this.feetype = value;
}
	public String getFeetype() {
		return this.feetype;
	}
	public void setFeecode(String value)  /**/  {
		this.feecode = value;
}
	public String getFeecode() {
		return this.feecode;
	}
	public void setValid_time(Date value)  /**/  {
		this.valid_time = value;
}
	public Date getValid_time() {
		return this.valid_time;
	}
	public void setAt_time(Date value)  /**/  {
		this.at_time = value;
}
	public Date getAt_time() {
		return this.at_time;
	}
	public void setSrc_terminal_id(String value)  /**/  {
		this.src_terminal_id = value;
}
	public String getSrc_terminal_id() {
		return this.src_terminal_id;
	}

	public Integer getDestusr_tl() {
		return this.destusr_tl;
	}
	public void setDest_ter_id(String value)  /**/  {
		this.dest_ter_id = value;
}
	public String getDest_ter_id() {
		return this.dest_ter_id;
	}

	public Integer getMsg_length() {
		return this.msg_length;
	}
	public void setMsg_content(String value)  /**/  {
		this.msg_content = value;
}
	public String getMsg_content() {
		return this.msg_content;
	}
	public void setReserve(String value)  /**/  {
		this.reserve = value;
}
	public String getReserve() {
		return this.reserve;
	}
	public void setSmsc_status_rep(Date value)  /**/  {
		this.smsc_status_rep = value;
}
	public Date getSmsc_status_rep() {
		return this.smsc_status_rep;
	}
	public void setMsg_id_r(String value)  /**/  {
		this.msg_id_r = value;
}
	public String getMsg_id_r() {
		return this.msg_id_r;
	}
	public void setStat(String value)  /**/  {
		this.stat = value;
}
	public String getStat() {
		return this.stat;
	}
	public void setSubmit_time(Date value)  /**/  {
		this.submit_time = value;
}
	public Date getSubmit_time() {
		return this.submit_time;
	}
	public void setDone_time(Date value)  /**/  {
		this.done_time = value;
}
	public Date getDone_time() {
		return this.done_time;
	}
	public void setDest_terminal_id(String value)  /**/  {
		this.dest_terminal_id = value;
}
	public String getDest_terminal_id() {
		return this.dest_terminal_id;
	}

	public Integer getSmsc_sequence() {
		return this.smsc_sequence;
	}
	public void setIcp_id(String value)  /**/  {
		this.icp_id = value;
}
	public String getIcp_id() {
		return this.icp_id;
	}
	public void setIcp_submit_time(Date value)  /**/  {
		this.icp_submit_time = value;
}
	public Date getIcp_submit_time() {
		return this.icp_submit_time;
	}
	public void setIsmg_submit_time(Date value)  /**/  {
		this.ismg_submit_time = value;
}
	public Date getIsmg_submit_time() {
		return this.ismg_submit_time;
	}
	
	public void setDb_insert_time(Date value)  /**/  {
		this.db_insert_time = value;
}
	public Date getDb_insert_time() {
		return this.db_insert_time;
	}
	public Integer getIcp_submit_result() {
		return this.icp_submit_result;
	}

	public void setIcp_submit_desc(String value)  /**/  {
		this.icp_submit_desc = value;
}
	public String getIcp_submit_desc() {
		return this.icp_submit_desc;
	}

	public Integer getBillend() {
		return this.billend;
	}
	public void setBillend_time(Date value)  /**/  {
		this.billend_time = value;
}
	public Date getBillend_time() {
		return this.billend_time;
	}
	
	public String getSubmit_user_id() {
		return submit_user_id;
	}
	public void setSubmit_user_id(String submit_user_id) {
		this.submit_user_id = submit_user_id;
	}
	public String getSubmit_mobile_type() {
		return submit_mobile_type;
	}
	public void setSubmit_mobile_type(String submit_mobile_type) {
		this.submit_mobile_type = submit_mobile_type;
	}
	public String getSubmit_owner_id() {
		return submit_owner_id;
	}
	public void setSubmit_owner_id(String submit_owner_id) {
		this.submit_owner_id = submit_owner_id;
	}

	public Integer getFee_usertype() {
		return fee_usertype;
	}
	public void setFee_usertype(Integer fee_usertype) {
		this.fee_usertype = fee_usertype;
	}
	public String getFee_terminal_id() {
		return fee_terminal_id;
	}
	public void setFee_terminal_id(String fee_terminal_id) {
		this.fee_terminal_id = fee_terminal_id;
	}
	public Integer getFee_terminal_type() {
		return fee_terminal_type;
	}
	public void setFee_terminal_type(Integer fee_terminal_type) {
		this.fee_terminal_type = fee_terminal_type;
	}
	public Integer getTp_pid() {
		return tp_pid;
	}
	public void setTp_pid(Integer tp_pid) {
		this.tp_pid = tp_pid;
	}
	public Integer getTp_udhi() {
		return tp_udhi;
	}
	public void setTp_udhi(Integer tp_udhi) {
		this.tp_udhi = tp_udhi;
	}
	public Integer getDest_terminal_type() {
		return dest_terminal_type;
	}
	public void setDest_terminal_type(Integer dest_terminal_type) {
		this.dest_terminal_type = dest_terminal_type;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPk_total(Integer pk_total) {
		this.pk_total = pk_total;
	}
	public void setPk_number(Integer pk_number) {
		this.pk_number = pk_number;
	}
	public void setRegistered_delivery(Integer registered_delivery) {
		this.registered_delivery = registered_delivery;
	}
	public void setMsg_level(Integer msg_level) {
		this.msg_level = msg_level;
	}
	public void setMsg_fmt(Integer msg_fmt) {
		this.msg_fmt = msg_fmt;
	}
	public void setDestusr_tl(Integer destusr_tl) {
		this.destusr_tl = destusr_tl;
	}
	public void setMsg_length(Integer msg_length) {
		this.msg_length = msg_length;
	}
	public void setSmsc_sequence(Integer smsc_sequence) {
		this.smsc_sequence = smsc_sequence;
	}
	public void setIcp_submit_result(Integer icp_submit_result) {
		this.icp_submit_result = icp_submit_result;
	}
	public void setBillend(Integer billend) {
		this.billend = billend;
	}

	public byte[] getMsg_content_bytes() {
		return msg_content_bytes;
	}
	public void setMsg_content_bytes(byte[] msg_content_bytes) {
		this.msg_content_bytes = msg_content_bytes;
	}
	
	public int getDebugCycleCount() {
		return debugCycleCount;
	}

	public void setDebugCycleCount(int debugCycleCount) {
		this.debugCycleCount = debugCycleCount;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	

}
