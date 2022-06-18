package org.jeecg.modules.jcy.importantMsg.vo;

import java.util.List;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsg;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 重要信息通知管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Data
@ApiModel(value="common_meeting_infoPage对象", description="重要信息通知管理")
public class ImportantMsgPage {
	
	/**重要短信唯一流水号*/
	private java.lang.String id;
	/**meetingName*/
  	@Excel(name = "meetingName", width = 15)
	private java.lang.String meetingName;
	/**meetingDesc*/
  	@Excel(name = "meetingDesc", width = 15)
	private java.lang.String meetingDesc;
	/**meetingTime*/
  	@Excel(name = "meetingTime", width = 15)
	private java.lang.String meetingTime;
	/**meetingAddress*/
  	@Excel(name = "meetingAddress", width = 15)
	private java.lang.String meetingAddress;
	/**meetingSmsInfo*/
  	@Excel(name = "meetingSmsInfo", width = 15)
	private java.lang.String meetingSmsInfo;
	/**publishMeetingInfoUser*/
  	@Excel(name = "publishMeetingInfoUser", width = 15)
	private java.lang.String publishMeetingInfoUser;
	/**memo*/
  	@Excel(name = "memo", width = 15)
	private java.lang.String memo;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
  	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
  	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
  	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
  	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
	
	@ExcelCollection(name="重要信息通知明细")
	@ApiModelProperty(value = "重要信息通知明细")
	private List<ImportantMsgUserSms> importantMsgUserSmsList;
	
}
