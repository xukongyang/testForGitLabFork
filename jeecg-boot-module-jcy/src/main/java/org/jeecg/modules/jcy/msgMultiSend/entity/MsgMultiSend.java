package org.jeecg.modules.jcy.msgMultiSend.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 群发短信
 * @Author: jeecg-boot
 * @Date:   2022-05-25
 * @Version: V1.0
 */
@Data
@TableName("msg_mt_log")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="msg_mt_log对象", description="群发短信")
public class MsgMultiSend {
    
	/**自增唯一流水号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "自增唯一流水号")
	private Integer id;
	/**发送用户代码,例如单位中的某个用户*/
	@Excel(name = "发送用户代码,例如单位中的某个用户", width = 15)
    @ApiModelProperty(value = "发送用户代码,例如单位中的某个用户")
	private String submitUserId;
	/**手机所属运营商,默认为移动cmcc*/
	@Excel(name = "手机所属运营商,默认为移动cmcc", width = 15)
    @ApiModelProperty(value = "手机所属运营商,默认为移动cmcc")
	private String submitMobileType;
	/**发送相关业务号码,例如一个会议代码,一个宣传活动代码*/
	@Excel(name = "发送相关业务号码,例如一个会议代码,一个宣传活动代码", width = 15)
    @ApiModelProperty(value = "发送相关业务号码,例如一个会议代码,一个宣传活动代码")
	private String submitOwnerId;
	/**消息类型短信sms彩信mms*/
	@Excel(name = "消息类型短信sms彩信mms", width = 15)
    @ApiModelProperty(value = "消息类型短信sms彩信mms")
	private String msgType;
	/**发送类型,网页发送,api发送等*/
	@Excel(name = "发送类型,网页发送,api发送等", width = 15)
    @ApiModelProperty(value = "发送类型,网页发送,api发送等")
	private String submitType;
	/**彩信主题*/
	@Excel(name = "彩信主题", width = 15)
    @ApiModelProperty(value = "彩信主题")
	private String msgSubject;
	/**彩信描述*/
	@Excel(name = "彩信描述", width = 15)
    @ApiModelProperty(value = "彩信描述")
	private String msgDescript;
	/**mas自身唯一流水号uuid*/
	@Excel(name = "mas自身唯一流水号uuid", width = 15)
    @ApiModelProperty(value = "mas自身唯一流水号uuid")
	private String msgId;
	/**发送给网关,网关返回的唯一流水号*/
	@Excel(name = "发送给网关,网关返回的唯一流水号", width = 15)
    @ApiModelProperty(value = "发送给网关,网关返回的唯一流水号")
	private String ismgMsgId;
	/**cmpp协议字段,默认为1,长短信时修改*/
	@Excel(name = "cmpp协议字段,默认为1,长短信时修改", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,默认为1,长短信时修改")
	private Integer pkTotal;
	/**cmpp协议字段,默认为1,长短信时修改*/
	@Excel(name = "cmpp协议字段,默认为1,长短信时修改", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,默认为1,长短信时修改")
	private Integer pkNumber;
	/**是否需要状态报告,默认都需要0不需要1需要*/
	@Excel(name = "是否需要状态报告,默认都需要0不需要1需要", width = 15)
    @ApiModelProperty(value = "是否需要状态报告,默认都需要0不需要1需要")
	private Integer registeredDelivery;
	/**消息等级*/
	@Excel(name = "消息等级", width = 15)
    @ApiModelProperty(value = "消息等级")
	private Integer msgLevel;
	/**业务代码 必须有值，否则发送会报错*/
	@Excel(name = "业务代码 必须有值，否则发送会报错", width = 15)
    @ApiModelProperty(value = "业务代码 必须有值，否则发送会报错")
	private String serviceId;
	/**cmpp协议字段,计费用户类型*/
	@Excel(name = "cmpp协议字段,计费用户类型", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,计费用户类型")
	private Integer feeUsertype;
	/**cmpp协议字段,计费号码*/
	@Excel(name = "cmpp协议字段,计费号码", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,计费号码")
	private String feeTerminalId;
	/**cmpp协议字段,计费号码类型*/
	@Excel(name = "cmpp协议字段,计费号码类型", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,计费号码类型")
	private Integer feeTerminalType;
	/**cmpp协议字段,特定消息类型时使用*/
	@Excel(name = "cmpp协议字段,特定消息类型时使用", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,特定消息类型时使用")
	private Integer tpPid;
	/**cmpp协议字段,特定消息类型时使用*/
	@Excel(name = "cmpp协议字段,特定消息类型时使用", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,特定消息类型时使用")
	private Integer tpUdhi;
	/**cmpp协议字段,消息字符编码*/
	@Excel(name = "cmpp协议字段,消息字符编码", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,消息字符编码")
	private Integer msgFmt;
	/**cmpp协议字段,填写网关分配的用户名,注意msgsrc的长度只有6，超过了发送不出去*/
	@Excel(name = "cmpp协议字段,填写网关分配的用户名,注意msgsrc的长度只有6，超过了发送不出去", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,填写网关分配的用户名,注意msgsrc的长度只有6，超过了发送不出去")
	private String msgSrc;
	/**cmpp协议字段,计费类型*/
	@Excel(name = "cmpp协议字段,计费类型", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,计费类型")
	private String feetype;
	/**cmpp协议字段,计费代码*/
	@Excel(name = "cmpp协议字段,计费代码", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,计费代码")
	private String feecode;
	/**cmpp协议字段,消息存活时间,null,默认为1天*/
	@Excel(name = "cmpp协议字段,消息存活时间,null,默认为1天", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "cmpp协议字段,消息存活时间,null,默认为1天")
	private Date validTime;
	/**cmpp协议字段,消息定时发送时间,null立即发送*/
	@Excel(name = "cmpp协议字段,消息定时发送时间,null立即发送", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "cmpp协议字段,消息定时发送时间,null立即发送")
	private Date atTime;
	/**cmpp协议字段,源号码字段,必须与网关分配号码开头*/
	@Excel(name = "cmpp协议字段,源号码字段,必须与网关分配号码开头", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,源号码字段,必须与网关分配号码开头")
	private String srcTerminalId;
	/**cmpp协议字段,接收短信号码个数*/
	@Excel(name = "cmpp协议字段,接收短信号码个数", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,接收短信号码个数")
	private Integer destusrTl;
	/**cmpp协议字段,接收短信号码*/
	@Excel(name = "cmpp协议字段,接收短信号码", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,接收短信号码")
	private String destTerminalId;
	/**cmpp协议字段,接收短信手机号码类型*/
	@Excel(name = "cmpp协议字段,接收短信手机号码类型", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,接收短信手机号码类型")
	private Integer destTerminalType;
	/**cmpp协议字段,消息长度*/
	@Excel(name = "cmpp协议字段,消息长度", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,消息长度")
	private Integer msgLength;
	/**cmpp协议字段,消息内容*/
	@Excel(name = "cmpp协议字段,消息内容", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,消息内容")
	private String msgContent;
	/**cmpp协议字段,3.0使用*/
	@Excel(name = "cmpp协议字段,3.0使用", width = 15)
    @ApiModelProperty(value = "cmpp协议字段,3.0使用")
	private String linkid;
	/**状态报告返回时间*/
	@Excel(name = "状态报告返回时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "状态报告返回时间")
	private Date smscStatusRep;
	/**状态报告对应的网关唯一流水号*/
	@Excel(name = "状态报告对应的网关唯一流水号", width = 15)
    @ApiModelProperty(value = "状态报告对应的网关唯一流水号")
	private String msgIdR;
	/**状态报告值,7位长,DELIVRD为已接收,EXPIRED过期等*/
	@Excel(name = "状态报告值,7位长,DELIVRD为已接收,EXPIRED过期等", width = 15)
    @ApiModelProperty(value = "状态报告值,7位长,DELIVRD为已接收,EXPIRED过期等")
	@Dict(dicCode = "sms_stat")
	private String stat;
	/**消息发送时间*/
	@Excel(name = "消息发送时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "消息发送时间")
	private Date submitTime;
	/**消息被接收时间*/
	@Excel(name = "消息被接收时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "消息被接收时间")
	private Date doneTime;
	/**状态报告中的目的号码,即接收短信的手机号*/
	@Excel(name = "状态报告中的目的号码,即接收短信的手机号", width = 15)
    @ApiModelProperty(value = "状态报告中的目的号码,即接收短信的手机号")
	private String destTerId;
	/**短信中心对此条消息的唯一流水号*/
	@Excel(name = "短信中心对此条消息的唯一流水号", width = 15)
    @ApiModelProperty(value = "短信中心对此条消息的唯一流水号")
	private Integer smscSequence;
	/**发送短信的mas用户代码*/
	@Excel(name = "发送短信的mas用户代码", width = 15)
    @ApiModelProperty(value = "发送短信的mas用户代码")
	private String icpId;
	/**用户发送短信时间*/
	@Excel(name = "用户发送短信时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "用户发送短信时间")
	private Date icpSubmitTime;
	/**用户短信向网关发送时间*/
	@Excel(name = "用户短信向网关发送时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "用户短信向网关发送时间")
	private Date ismgSubmitTime;
	/**用户消息入库时间*/
	@Excel(name = "用户消息入库时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "用户消息入库时间")
	private Date dbInsertTime;
	/**发送结果代码*/
	@Excel(name = "发送结果代码", width = 15)
    @ApiModelProperty(value = "发送结果代码")
	private Integer icpSubmitResult;
	/**发送结果描述*/
	@Excel(name = "发送结果描述", width = 15)
    @ApiModelProperty(value = "发送结果描述")
	private String icpSubmitDesc;
	/**是否已出账*/
	@Excel(name = "是否已出账", width = 15)
    @ApiModelProperty(value = "是否已出账")
	private Integer billend;
	/**出账结束时间*/
	@Excel(name = "出账结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "出账结束时间")
	private Date billendTime;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
}
