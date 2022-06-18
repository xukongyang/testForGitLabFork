package org.jeecg.modules.jcy.msgGroup.vo;

import java.util.List;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroup;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 短信群组管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Data
@ApiModel(value="common_custom_group_infoPage对象", description="短信群组管理")
public class MsgGroupPage {
	
	/**分组唯一流水号*/
	private java.lang.String id;
	/**分组名称*/
  	@Excel(name = "分组名称", width = 15)
	private java.lang.String groupName;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private java.lang.String remark;
	/**状态1在用0停用*/
  	@Excel(name = "状态1在用0停用", width = 15)
	private java.lang.Integer status;
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
	
	@ExcelCollection(name="短信群组明细")
	@ApiModelProperty(value = "短信群组明细")
	private List<MsgGroupMember> msgGroupMemberList;
	
}
