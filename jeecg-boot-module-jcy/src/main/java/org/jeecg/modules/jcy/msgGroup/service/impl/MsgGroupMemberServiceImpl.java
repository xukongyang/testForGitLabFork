package org.jeecg.modules.jcy.msgGroup.service.impl;

import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import org.jeecg.modules.jcy.msgGroup.mapper.MsgGroupMemberMapper;
import org.jeecg.modules.jcy.msgGroup.service.IMsgGroupMemberService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 短信群组明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Service
public class MsgGroupMemberServiceImpl extends ServiceImpl<MsgGroupMemberMapper, MsgGroupMember> implements IMsgGroupMemberService {
	
	@Autowired
	private MsgGroupMemberMapper msgGroupMemberMapper;
	
	@Override
	public List<MsgGroupMember> selectByMainId(String mainId) {
		return msgGroupMemberMapper.selectByMainId(mainId);
	}
}
