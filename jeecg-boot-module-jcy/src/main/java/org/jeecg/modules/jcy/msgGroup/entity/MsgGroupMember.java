package org.jeecg.modules.jcy.msgGroup.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 短信群组明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Data
@TableName("common_custom_group_member")
@ApiModel(value="common_custom_group_info对象", description="短信群组管理")
public class MsgGroupMember implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**数据唯一流水号*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "数据唯一流水号")
	private java.lang.String id;
	/**分组唯一编号*/
	@ApiModelProperty(value = "分组唯一编号")
	private java.lang.String groupId;
	/**用户编号*/
    @Excel(name = "用户编号", width = 15)
	@ApiModelProperty(value = "用户编号")
	private java.lang.String userId;
	/**真实姓名*/
    @Excel(name = "真实姓名", width = 15)
	@ApiModelProperty(value = "真实姓名")
	private java.lang.String realname;
	/**电话*/
    @Excel(name = "电话", width = 15)
	@ApiModelProperty(value = "电话")
	private java.lang.String phone;
	/**职务,用户要求能够自行修改*/
    @Excel(name = "职务,用户要求能够自行修改", width = 15)
	@ApiModelProperty(value = "职务,用户要求能够自行修改")
	private java.lang.String duties;
	/**ID*/
    @Excel(name = "ID", width = 15)
	@ApiModelProperty(value = "ID")
	private java.lang.String depId;
	/**机构编码*/
    @Excel(name = "机构编码", width = 15)
	@ApiModelProperty(value = "机构编码")
	private java.lang.String orgCode;
	/**机构/部门名称*/
    @Excel(name = "机构/部门名称", width = 15)
	@ApiModelProperty(value = "机构/部门名称")
	private java.lang.String departName;
	/**分组id+手机号码，用来防止在同一个分组中多次加入同一个手机号码*/
    @Excel(name = "分组id+手机号码，用来防止在同一个分组中多次加入同一个手机号码", width = 15)
	@ApiModelProperty(value = "分组id+手机号码，用来防止在同一个分组中多次加入同一个手机号码")
	private java.lang.String uniqueId;
}
