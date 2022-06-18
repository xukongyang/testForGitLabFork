package org.jeecg.modules.jcy.msgGroup.service;

import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 短信群组管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
public interface IMsgGroupService extends IService<MsgGroup> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(MsgGroup msgGroup,List<MsgGroupMember> msgGroupMemberList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MsgGroup msgGroup,List<MsgGroupMember> msgGroupMemberList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
