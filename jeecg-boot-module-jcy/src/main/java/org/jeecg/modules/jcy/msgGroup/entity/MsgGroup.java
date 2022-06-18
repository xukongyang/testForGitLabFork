package org.jeecg.modules.jcy.msgGroup.entity;

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
 * @Description: 短信群组管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Data
@TableName("common_custom_group_info")
@ApiModel(value="common_custom_group_info对象", description="短信群组管理")
public class MsgGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**分组唯一流水号*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "分组唯一流水号")
	private java.lang.String id;
	/**分组名称*/
	@ApiModelProperty(value = "分组名称")
	private java.lang.String groupName;
	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;
	/**状态1在用0停用*/
	@ApiModelProperty(value = "状态1在用0停用")
	private java.lang.Integer status;
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
