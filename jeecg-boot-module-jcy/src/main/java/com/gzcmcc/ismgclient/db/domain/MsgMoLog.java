package com.gzcmcc.ismgclient.db.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.*;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class MsgMoLog implements Serializable {
	private static final long serialVersionUID = 1L;

	//protected Map<String, String> mapRunInfo = new HashMap<String, String>();

	//private List<String> listMsgFile = new ArrayList<String>();

	private Integer id = 0;    /*消息自增唯一流水号*/

	private String deliver_user_id = "0";//上行消息发送给的用户
	private String deliver_mobile_type = "cmcc";//发送消息的手机所属运营商,默认移动cmcc
	private String deliver_owner_id = "0";//这条短信对应的业务代码

	private Date expiredTime = null;//消息过期时间,用在短信状态报告找不到匹配短信时,多久后丢弃入库
	
	private String msg_type= "sms";    //消息类型短信sms彩信mms
	private String msg_subject = "msg_subject";    //彩信主题
	private String msg_id = null;    //mas自身唯一流水号
	private String ismg_msg_id = null;    //网关下发时的唯一流水号
	private Integer pk_total = 1;    //cmpp协议字段,默认为1,长短信时修改
	private Integer pk_number = 1;    //cmpp协议字段,默认为1,长短信时修改
	private Integer registered_delivery = 1;    //是状态报告1,或短信0
	private Integer msg_level = 0;   //消息等级,默认最低
	/*优先级,默认最低,因为数字越大,优先级越低*/
	private String service_id = "";    //对应的业务代码

	private Integer tp_pid = 0;//cmpp协议字段,特定消息类型时使用
	private Integer tp_udhi = 0; //cmpp协议字段,特定类型消息时使用

	private Integer msg_fmt = 8;    //cmpp协议字段,消息字符编码
	private String msg_src = "msgsrc";   
	/*cmpp协议字段,填写网关分配的用户名,注意msgsrc的长度只有6*/
	private String feetype = "00";    //cmpp协议字段,计费类型
	private String feecode = "00";    //cmpp协议字段,计费代码
	private Date valid_time;    //cmpp协议字段,消息存活时间,null,默认为1天
	private Date at_time;    //cmpp协议字段,消息定时发送时间,null立即发送
	private String src_terminal_id = "";    //cmpp协议字段,源号码字段,即发送上行消息的手机
	
	private Integer src_terminal_type = 0;//源号码类型

	private Integer destusr_tl= 1;    //发送手机个数
	private String dest_terminal_id= "";    //目的地址,例如95588,10086等
	private Integer msg_length = 0;    //消息长度
	private String msg_content = "";    ///消息类容
	
	private String linkid = "linkid";//cmpp协议字段,3.0使用
	
	private String reserve = "00000000";    //cmpp协议字段,保留
	private Date smsc_status_rep;    //状态报告返回时间
	private String msg_id_r = null;    //状态报告对应的网关唯一流水号
	private String stat = "";    //状态报告值,7位长,DELIVRD为已接收,EXPIRED过期等
	private Date submit_time;    //消息发送时间
	private Date done_time;    //消息被接收时间
	private String dest_ter_id = "dest_terminal_id";    //状态报告中的目的号码,即接收短信的手机号
	private Integer smsc_sequence = 0;    //短信中心对此条消息的唯一流水号
	private String icp_id = "";    //接收上行短信的mas用户代码
	
	private Date mo_time = new Date();    //用户发送上行消息时间
	private Date ismg_get_time = new Date();    //mas从网关获得上行消息时间
	private Date icp_recv_time = new Date();    //mas用户接收上行短信时间

	private Integer icp_receive_result = 0;    //用户接收状态代码
	private String icp_receive_desc = "";    //用户接受状态说明
	private String icp_memo = "处理说明"; //用户对此条上行消息的处理备注

	private Integer billend = 0;    //是否已出账
	private Date billend_time = new Date();    //出账时间
	
	private String queueName = "";//上行消息所属队列,java类型队列时使用

	private int processedCount = 0;

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
	//////////////////////////////
	/*public Map<String, String> getMapRunInfo() {
		return mapRunInfo;
	}
	public void setMapRunInfo(Map<String, String> mapRunInfo) {
		this.mapRunInfo = mapRunInfo;
	}*/
	/*public List<String> getListMsgFile() {
		return listMsgFile;
	}
	public void setListMsgFile(List<String> listMsgFile) {
		this.listMsgFile = listMsgFile;
	}*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeliver_user_id() {
		return deliver_user_id;
	}
	public void setDeliver_user_id(String deliver_user_id) {
		this.deliver_user_id = deliver_user_id;
	}
	public String getDeliver_mobile_type() {
		return deliver_mobile_type;
	}
	public void setDeliver_mobile_type(String deliver_mobile_type) {
		this.deliver_mobile_type = deliver_mobile_type;
	}
	public String getDeliver_owner_id() {
		return deliver_owner_id;
	}
	public void setDeliver_owner_id(String deliver_owner_id) {
		this.deliver_owner_id = deliver_owner_id;
	}
	public Date getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_subject() {
		return msg_subject;
	}
	public void setMsg_subject(String msg_subject) {
		this.msg_subject = msg_subject;
	}
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getIsmg_msg_id() {
		return ismg_msg_id;
	}
	public void setIsmg_msg_id(String ismg_msg_id) {
		this.ismg_msg_id = ismg_msg_id;
	}
	public Integer getPk_total() {
		return pk_total;
	}
	public void setPk_total(Integer pk_total) {
		this.pk_total = pk_total;
	}
	public Integer getPk_number() {
		return pk_number;
	}
	public void setPk_number(Integer pk_number) {
		this.pk_number = pk_number;
	}
	public Integer getRegistered_delivery() {
		return registered_delivery;
	}
	public void setRegistered_delivery(Integer registered_delivery) {
		this.registered_delivery = registered_delivery;
	}
	public Integer getMsg_level() {
		return msg_level;
	}
	public void setMsg_level(Integer msg_level) {
		this.msg_level = msg_level;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
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
	public Integer getMsg_fmt() {
		return msg_fmt;
	}
	public void setMsg_fmt(Integer msg_fmt) {
		this.msg_fmt = msg_fmt;
	}
	public String getMsg_src() {
		return msg_src;
	}
	public void setMsg_src(String msg_src) {
		this.msg_src = msg_src;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public String getFeecode() {
		return feecode;
	}
	public void setFeecode(String feecode) {
		this.feecode = feecode;
	}
	public Date getValid_time() {
		return valid_time;
	}
	public void setValid_time(Date valid_time) {
		this.valid_time = valid_time;
	}
	public Date getAt_time() {
		return at_time;
	}
	public void setAt_time(Date at_time) {
		this.at_time = at_time;
	}
	public String getSrc_terminal_id() {
		return src_terminal_id;
	}
	public void setSrc_terminal_id(String src_terminal_id) {
		this.src_terminal_id = src_terminal_id;
	}
	public Integer getSrc_terminal_type() {
		return src_terminal_type;
	}
	public void setSrc_terminal_type(Integer src_terminal_type) {
		this.src_terminal_type = src_terminal_type;
	}
	public Integer getDestusr_tl() {
		return destusr_tl;
	}
	public void setDestusr_tl(Integer destusr_tl) {
		this.destusr_tl = destusr_tl;
	}
	public String getDest_terminal_id() {
		return dest_terminal_id;
	}
	public void setDest_terminal_id(String dest_terminal_id) {
		this.dest_terminal_id = dest_terminal_id;
	}
	public Integer getMsg_length() {
		return msg_length;
	}
	public void setMsg_length(Integer msg_length) {
		this.msg_length = msg_length;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public Date getSmsc_status_rep() {
		return smsc_status_rep;
	}
	public void setSmsc_status_rep(Date smsc_status_rep) {
		this.smsc_status_rep = smsc_status_rep;
	}
	public String getMsg_id_r() {
		return msg_id_r;
	}
	public void setMsg_id_r(String msg_id_r) {
		this.msg_id_r = msg_id_r;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public Date getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}
	public Date getDone_time() {
		return done_time;
	}
	public void setDone_time(Date done_time) {
		this.done_time = done_time;
	}
	public String getDest_ter_id() {
		return dest_ter_id;
	}
	public void setDest_ter_id(String dest_ter_id) {
		this.dest_ter_id = dest_ter_id;
	}
	public Integer getSmsc_sequence() {
		return smsc_sequence;
	}
	public void setSmsc_sequence(Integer smsc_sequence) {
		this.smsc_sequence = smsc_sequence;
	}
	public String getIcp_id() {
		return icp_id;
	}
	public void setIcp_id(String icp_id) {
		this.icp_id = icp_id;
	}
	public Date getMo_time() {
		return mo_time;
	}
	public void setMo_time(Date mo_time) {
		this.mo_time = mo_time;
	}
	public Date getIsmg_get_time() {
		return ismg_get_time;
	}
	public void setIsmg_get_time(Date ismg_get_time) {
		this.ismg_get_time = ismg_get_time;
	}
	public Date getIcp_recv_time() {
		return icp_recv_time;
	}
	public void setIcp_recv_time(Date icp_recv_time) {
		this.icp_recv_time = icp_recv_time;
	}
	public Integer getIcp_receive_result() {
		return icp_receive_result;
	}
	public void setIcp_receive_result(Integer icp_receive_result) {
		this.icp_receive_result = icp_receive_result;
	}
	public String getIcp_receive_desc() {
		return icp_receive_desc;
	}
	public void setIcp_receive_desc(String icp_receive_desc) {
		this.icp_receive_desc = icp_receive_desc;
	}
	public String getIcp_memo() {
		return icp_memo;
	}
	public void setIcp_memo(String icp_memo) {
		this.icp_memo = icp_memo;
	}
	public Integer getBillend() {
		return billend;
	}
	public void setBillend(Integer billend) {
		this.billend = billend;
	}
	public Date getBillend_time() {
		return billend_time;
	}
	public void setBillend_time(Date billend_time) {
		this.billend_time = billend_time;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getProcessedCount() {
		return processedCount;
	}

	public void setProcessedCount(int processedCount) {
		this.processedCount = processedCount;
	}
}
