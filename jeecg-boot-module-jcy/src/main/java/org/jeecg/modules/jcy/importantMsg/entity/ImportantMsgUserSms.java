package org.jeecg.modules.jcy.importantMsg.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 重要信息通知明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Data
@TableName("common_meeting_user_sms_info")
@ApiModel(value="common_meeting_info对象", description="重要信息通知管理")
public class ImportantMsgUserSms implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**唯一流水号*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "唯一流水号")
	private java.lang.String id;
	/**重要短信唯一编号*/
	@ApiModelProperty(value = "重要短信唯一编号")
	private java.lang.String meetingId;
	/**meetingTime*/
	@Excel(name = "meetingTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "meetingTime")
	private java.util.Date meetingTime;
	/**meetingContactType*/
    @Excel(name = "meetingContactType", width = 15)
	@ApiModelProperty(value = "meetingContactType")
	private java.lang.String meetingContactType;
	/**meetingContactResult*/
    @Excel(name = "meetingContactResult", width = 15)
	@ApiModelProperty(value = "meetingContactResult")
	private java.lang.String meetingContactResult;
	/**smsUserName*/
    @Excel(name = "smsUserName", width = 15)
	@ApiModelProperty(value = "smsUserName")
	private java.lang.String smsUserName;
	/**smsUserMobile*/
    @Excel(name = "smsUserMobile", width = 15)
	@ApiModelProperty(value = "smsUserMobile")
	private java.lang.String smsUserMobile;
	/**职务,用户要求能够自行修改*/
	@Excel(name = "职务,用户要求能够自行修改", width = 15)
	@ApiModelProperty(value = "职务,用户要求能够自行修改")
	private java.lang.String smsUserDuties;
	/**smsUserDeptName*/
    @Excel(name = "smsUserDeptName", width = 15)
	@ApiModelProperty(value = "smsUserDeptName")
	private java.lang.String smsUserDeptName;
	/**smsMtType*/
    @Excel(name = "smsMtType", width = 15)
	@ApiModelProperty(value = "smsMtType")
	private java.lang.String smsMtType;
	/**smsMtNeedReply*/
    @Excel(name = "smsMtNeedReply", width = 15)
	@ApiModelProperty(value = "smsMtNeedReply")
	private java.lang.String smsMtNeedReply;
	/**smsMtExpire*/
	@Excel(name = "smsMtExpire", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "smsMtExpire")
	private java.util.Date smsMtExpire;
	/**smsMtInfo*/
    @Excel(name = "smsMtInfo", width = 15)
	@ApiModelProperty(value = "smsMtInfo")
	private java.lang.String smsMtInfo;
	/**smsMtTime*/
	@Excel(name = "smsMtTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "smsMtTime")
	private java.util.Date smsMtTime;
	/**smsMtSendCount*/
    @Excel(name = "smsMtSendCount", width = 15)
	@ApiModelProperty(value = "smsMtSendCount")
	private java.lang.Integer smsMtSendCount;
	/**smsMtIsmgMsgId*/
    @Excel(name = "smsMtIsmgMsgId", width = 15)
	@ApiModelProperty(value = "smsMtIsmgMsgId")
	private java.math.BigDecimal smsMtIsmgMsgId;
	/**smsMtStat*/
    @Excel(name = "smsMtStat", width = 15)
	@ApiModelProperty(value = "smsMtStat")
	@Dict(dicCode = "sms_stat")
	private java.lang.String smsMtStat;
	/**smsMtStatDate*/
	@Excel(name = "smsMtStatDate", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "smsMtStatDate")
	private java.util.Date smsMtStatDate;
	/**smsMtSrcTerminalId*/
    @Excel(name = "smsMtSrcTerminalId", width = 15)
	@ApiModelProperty(value = "smsMtSrcTerminalId")
	private java.lang.String smsMtSrcTerminalId;
	/**smsMoId*/
    @Excel(name = "smsMoId", width = 15)
	@ApiModelProperty(value = "smsMoId")
	private java.math.BigDecimal smsMoId;
	/**smsMoInfo*/
    @Excel(name = "smsMoInfo", width = 15)
	@ApiModelProperty(value = "smsMoInfo")
	private java.lang.String smsMoInfo;
	/**smsMoStat*/
    @Excel(name = "smsMoStat", width = 15)
	@ApiModelProperty(value = "smsMoStat")
	private java.lang.String smsMoStat;
	/**smsMoStatDate*/
	@Excel(name = "smsMoStatDate", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "smsMoStatDate")
	private java.util.Date smsMoStatDate;
	/**smsMoSendCount*/
    @Excel(name = "smsMoSendCount", width = 15)
	@ApiModelProperty(value = "smsMoSendCount")
	private java.lang.Integer smsMoSendCount;
	/**memo*/
    @Excel(name = "memo", width = 15)
	@ApiModelProperty(value = "memo")
	private java.lang.String memo;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
    @Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
    @Excel(name = "所属部门", width = 15)
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
}
