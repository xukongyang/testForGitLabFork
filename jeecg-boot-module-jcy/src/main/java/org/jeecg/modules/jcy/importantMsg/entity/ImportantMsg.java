package org.jeecg.modules.jcy.importantMsg.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 重要信息通知管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Data
@TableName("common_meeting_info")
@ApiModel(value="common_meeting_info对象", description="重要信息通知管理")
public class ImportantMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**重要短信唯一流水号*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "重要短信唯一流水号")
	private java.lang.String id;
	/**meetingName*/
	@ApiModelProperty(value = "meetingName")
	private java.lang.String meetingName;
	/**meetingDesc*/
	@ApiModelProperty(value = "meetingDesc")
	private java.lang.String meetingDesc;
	/**meetingTime*/
	@ApiModelProperty(value = "meetingTime")
	private java.lang.String meetingTime;
	/**meetingAddress*/
	@ApiModelProperty(value = "meetingAddress")
	private java.lang.String meetingAddress;
	/**meetingSmsInfo*/
	@ApiModelProperty(value = "meetingSmsInfo")
	private java.lang.String meetingSmsInfo;
	/**publishMeetingInfoUser*/
	@ApiModelProperty(value = "publishMeetingInfoUser")
	private java.lang.String publishMeetingInfoUser;
	/**memo*/
	@ApiModelProperty(value = "memo")
	private java.lang.String memo;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
}
