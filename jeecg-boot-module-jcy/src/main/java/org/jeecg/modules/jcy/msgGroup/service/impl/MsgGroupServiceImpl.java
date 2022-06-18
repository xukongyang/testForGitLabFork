package org.jeecg.modules.jcy.msgGroup.service.impl;

import org.jeecg.modules.jcy.msgGroup.entity.MsgGroup;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import org.jeecg.modules.jcy.msgGroup.mapper.MsgGroupMemberMapper;
import org.jeecg.modules.jcy.msgGroup.mapper.MsgGroupMapper;
import org.jeecg.modules.jcy.msgGroup.service.IMsgGroupService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 短信群组管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Service
public class MsgGroupServiceImpl extends ServiceImpl<MsgGroupMapper, MsgGroup> implements IMsgGroupService {

	@Autowired
	private MsgGroupMapper msgGroupMapper;
	@Autowired
	private MsgGroupMemberMapper msgGroupMemberMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(MsgGroup msgGroup, List<MsgGroupMember> msgGroupMemberList) {
		msgGroupMapper.insert(msgGroup);
		for(MsgGroupMember entity:msgGroupMemberList) {
			//外键设置
			entity.setGroupId(msgGroup.getId());
			msgGroupMemberMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(MsgGroup msgGroup,List<MsgGroupMember> msgGroupMemberList) {
		msgGroupMapper.updateById(msgGroup);
		
		//1.先删除子表数据
		msgGroupMemberMapper.deleteByMainId(msgGroup.getId());
		
		//2.子表数据重新插入
		for(MsgGroupMember entity:msgGroupMemberList) {
			//外键设置
			entity.setGroupId(msgGroup.getId());
			msgGroupMemberMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		msgGroupMemberMapper.deleteByMainId(id);
		msgGroupMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			msgGroupMemberMapper.deleteByMainId(id.toString());
			msgGroupMapper.deleteById(id);
		}
	}
	
}
